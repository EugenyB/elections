package elections;

import elections.entities.BallotBox;
import elections.entities.Citizen;
import elections.exceptions.PassportNumberWrongException;

import java.util.Scanner;

public class Main {

    Management management;

    public static void main(String[] args) {
	    new Main().run();
    }

    public void run() {
        management = new Management();
        management.init();

        while (true) {
            switch (menu()) {
                case 1: management.addBallotBox();
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
                case 3: management.addParty();
                    break;
                case 5:
                    showBoxes(management.getBallotBoxes());
                    break;
                case 6: management.showCitizens();
                    break;
                case 7: management.showParties();
                    break;
                case 10: return;
            }
        }
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
