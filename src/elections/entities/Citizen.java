package elections.entities;

import elections.exceptions.PassportNumberWrongException;

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
}
