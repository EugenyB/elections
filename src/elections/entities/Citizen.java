package elections.entities;

import elections.exceptions.PassportNumberWrongException;

import java.util.Objects;
import java.util.Scanner;

public class Citizen {
    private String name;
    private String passport;
    private int birthYear;
    private BallotBox ballotBox;
    private boolean carantine;

    private Citizen(String name, String passport, int birthYear) {
        this.name = name;
        this.passport = passport;
        this.birthYear = birthYear;
    }

    public static Citizen of(String name, String passport, int birthYear) throws PassportNumberWrongException {
        //Все проверки
        if (!correct(passport)) {
            throw new PassportNumberWrongException(passport);
        }
        return new Citizen(name, passport, birthYear);
    }

    private static boolean correct(String passport) {
        if (passport.length()!=9) return false;
        for (int i = 0; i < passport.length(); i++) {
            if (!Character.isDigit(passport.charAt(i))) return false;
        }
        return true;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public BallotBox getBallotBox() {
        return ballotBox;
    }

    public void setBallotBox(BallotBox ballotBox) {
        this.ballotBox = ballotBox;
    }

    public boolean isCarantine() {
        return carantine;
    }

    public void setCarantine(boolean carantine) {
        this.carantine = carantine;
    }

    public boolean checkIfCitizenWantToVote(Citizen c) {
        Scanner in = new Scanner(System.in);
        System.out.println("Do you want to vote?");
        System.out.println("1 - Yes , 2 - Not ");
        int voteChoice;
        do {
            voteChoice = in.nextInt();
            if (  1 < voteChoice || voteChoice > 2 ){
                System.out.println("Incorrect choice , please repeat");
            }
        }while (1 < voteChoice || voteChoice > 2);
        if (voteChoice == 1 ) {
            return true;
        }else return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Citizen citizen = (Citizen) o;
        return birthYear == citizen.birthYear && name.equals(citizen.name) && passport.equals(citizen.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport, birthYear);
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "name='" + name + '\'' +
                ", passport='" + passport + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
