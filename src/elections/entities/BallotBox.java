package elections.entities;

import java.util.Arrays;

public abstract class BallotBox {

    protected Citizen[] citizens = new Citizen[10];
    protected int countCitizens = 0;

    private static int countBallotBoxes = 0;

    private int number;
    private String address;

    public BallotBox(String address) {
        this.address = address;
        number = ++countBallotBoxes;
    }

    public int getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public abstract boolean check(Citizen citizen);

    public Citizen[] getCitizens() {
        return Arrays.copyOf(citizens, countCitizens);
    }

    public void addCitizen(Citizen citizen) {
        ensureCapacity(countCitizens + 1);
        citizens[countCitizens++] = citizen;
    }

    public void ensureCapacity(int newCapacity){
        if (newCapacity >= citizens.length) {
            citizens = Arrays.copyOf(citizens, citizens.length*2);
        }
    }

    @Override
    public String toString() {
        return "BallotBox{" +
                "number=" + number +
                ", address='" + address + '\'' +
                '}';
    }
}
