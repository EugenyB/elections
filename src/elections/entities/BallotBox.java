package elections.entities;

import elections.Management;

import java.util.Arrays;
import java.util.Objects;

public abstract class BallotBox {

    protected Citizen[] citizens = new Citizen[10];
    protected int countCitizens = 0;

    private static int countBallotBoxes = 0;

    private int number;
    private String address;
    private Management management;

    public BallotBox(String address, Management management) {
        this.address = address;
        number = ++countBallotBoxes;
        this.management = management;
    }

    public Management getManagement() {
        return management;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BallotBox)) return false;
        BallotBox ballotBox = (BallotBox) o;
        return number == ballotBox.number && Objects.equals(address, ballotBox.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, address);
    }

    @Override
    public String toString() {
        return "BallotBox{" +
                "number=" + number +
                ", address='" + address + '\'' +
                '}';
    }
}
