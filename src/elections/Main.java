package elections;

import elections.entities.*;
import elections.exceptions.PassportNumberWrongException;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    Management management;

    public static void main(String[] args) {
	    new Main().run();
    }

    public void run() {
        management = new Management();
        management.init();

        init();

        while (true) {
            switch (menu()) {
                case 1:
                    BallotBox newBallotBox = readBallotBoxFromKeyboard();
                    management.addBallotBox(newBallotBox);
                    break;
                case 2:
                        try {
                            Citizen citizen = readCitizenFromKeyboard();
                            if (management.exists(citizen)) {
                                System.out.println("This citizen already exists");
                            } else {
                                BallotBox box = chooseBallotBox(citizen);
                                management.addCitizen(citizen, box);
                                System.out.println("Citizen was added!");
                            }
                        } catch (PassportNumberWrongException e) {
                            System.err.println(e.getMessage());
                        }
                    break;
                case 3:
                    Party newParty = readPartyFromKeyboard();
                    management.addParty(newParty);
                    break;
                case 5:
                    showBoxes(management.getBallotBoxes());
                    break;
                case 6:
                    printCitizens();
                    break;
                case 7:
                    printParties();
                    break;
                case 10: return;
            }
        }
    }

    private void printCitizens() {
        Citizen[] citizens = management.showCitizens();
        System.out.println("----- Citizens are: ------");
        printArray(citizens);
        System.out.println("-------------------------");
    }

    private void printArray(Object[] arr) {
        for (Object o : arr) {
            System.out.println(o);
        }
    }

    private void printParties() {
        Party[] parties = management.showParties();
        System.out.println("----- Parties are: ------");
        printArray(parties);
        System.out.println("-------------------------");
    }

    private Party readPartyFromKeyboard() {
        Scanner in = new Scanner(System.in);
        System.out.println("Name of party:");
        String name = in.nextLine();
        System.out.println("Date of creation (y m d): ");
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        LocalDate date = LocalDate.of(year, month, day);
        System.out.println("Fraction 1 - Left, 2 - Center, 3 - Right");
        int fraction;
        do {
            fraction = in.nextInt();
            if (fraction < 1 || fraction >3) System.out.println("Incorrect fraction. Repeat.");
        } while (fraction < 1 || fraction >3);
        switch(fraction) {
            case 1: return new Party(name, Fraction.LEFT, date);
            case 2: return new Party(name, Fraction.CENTER, date);
            case 3: return new Party(name, Fraction.RIGHT, date);
        }
        return null;
    }

    private BallotBox readBallotBoxFromKeyboard() {
        Scanner in = new Scanner(System.in);
        System.out.println("Address of BallotBox");
        String address = in.nextLine();
        System.out.println("1 - Normal, 2 - Carantine, 3 - Army");
        int type;
        do {
            type = in.nextInt();
            if (type < 1 || type >3) System.out.println("Incorrect type. Repeat.");
        } while (type < 1 || type >3);
        switch (type) {
            case 1: return new NormalBallotBox(address);
            case 2: return new CarantineBallotBox(address);
            case 3: return new ArmyBallotBox(address);
            default: return null;
        }
    }

    private void init() {
        management.addBallotBox(new NormalBallotBox("First Street, 1"));
        management.addBallotBox(new NormalBallotBox("First Street, 20"));
        management.addBallotBox(new ArmyBallotBox("Second Str, 2"));
        management.addBallotBox(new CarantineBallotBox("Another Str, 13"));

        try {
            Citizen c1 = Citizen.of("Ian Iza", "123456789", 1995);
            management.addCitizen(c1, management.getBallotBox(0));

            Citizen c2 = Citizen.of("Bet Bets", "111111111", 1990);
            management.addCitizen(c2, management.getBallotBox(0));

            Citizen c3 = Citizen.of("Ben Yan", "987654321", 2000);
            management.addCitizen(c3, management.getBallotBox(2));

            Citizen c4 = Citizen.of("Bin Bolnoy", "123123123", 1998);
            c4.setCarantine(true);
            management.addCitizen(c4, management.getBallotBox(3));

            Citizen c5 = Citizen.of("Ben Best", "212121212", 1990);
            management.addCitizen(c5, management.getBallotBox(1));

            Party p1 = new Party("Party 1", Fraction.LEFT, LocalDate.of(1990, 5, 1));
            management.addParty(p1);
            p1.addMember(c1);
            p1.addMember(c2);

            Party p2 = new Party("Party 2", Fraction.CENTER, LocalDate.of(1980, 1, 1));
            management.addParty(p2);
            p2.addMember(c3);

            Party p3 = new Party("Party 3", Fraction.RIGHT, LocalDate.of(1984, 10, 10));
            management.addParty(p3);
            p3.addMember(c5);

        } catch (Exception e) {}


    }

    private void showBoxes(BallotBox[] ballotBoxes) {
//        for (int i = 0; i < ballotBoxes.length; i++) {
//            BallotBox box = ballotBoxes[i];
//            System.out.println(box);
//        }
        for (BallotBox box : ballotBoxes) {
            System.out.println(box);
        }
        System.out.println("Choose BallotBox Number for full info:");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        Citizen[] citizens = management.findBallotBox(num).getCitizens();
        printCitizens(citizens);
    }

    private void printCitizens(Citizen[] citizens) {
        for (Citizen citizen : citizens) {
            System.out.println(citizen);
        }
    }

    private BallotBox chooseBallotBox(Citizen citizen) {
        BallotBox[] appropriated = management.findAppropriateBallotBoxes(citizen);
        System.out.println("----- Appropriated Ballot Boxes are: -----");
        for (int i = 0; i < appropriated.length; i++) {
            System.out.println(appropriated[i]);
        }
        Scanner in = new Scanner(System.in);
        System.out.print("Choose one: ");
        int num = in.nextInt();
        for (BallotBox box : appropriated) {
            if (box.getNumber() == num) return box;
        }
        return null;
    }


    private Citizen readCitizenFromKeyboard() throws PassportNumberWrongException {
        Scanner in = new Scanner(System.in);
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.print("Passport: ");
        String passport = in.nextLine();
        System.out.print("Birth year: ");
        int year = in.nextInt(); in.nextLine();
        Citizen res = Citizen.of(name, passport, year);
        System.out.print("Corona? (y/n):");
        boolean corona = in.nextLine().startsWith("y");
        res.setCarantine(corona);
        return res;
    }

    private int menu() {
        System.out.println(
                "1. Добавление стойки\n" +
                        "2. Добавление гражданина \n" +
                        "3. Добавление партии\n" +
                        "4. Добавление гражданина , который кандидат от определенной партии\n" +
                        "5. Показать стойки\n" +
                        "6. Показать граждан\n" +
                        "7. Показать партии\n"+
                        "8.    \n" +
                        "9.    \n" +
                        "10. Exit");
        return new Scanner(System.in).nextInt();
    }
}
