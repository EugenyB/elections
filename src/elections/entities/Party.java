package elections.entities;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Party {
    private String name;
    private Fraction fraction;
    private LocalDate dateOfCreation;
    private Citizen[] members;
    private int count;

    public Party(String name, Fraction fraction, LocalDate dateOfCreation) {
        this.name = name;
        this.fraction = fraction;
        this.dateOfCreation = dateOfCreation;
        members = new Citizen[100];
        count = 0;
    }

    public void addMember(Citizen citizen) {
        ensureCapacity(count+1);
        members[count++] = citizen;
    }

    private void ensureCapacity(int newCount) {
        if (newCount >= members.length) {
            members = Arrays.copyOf(members, members.length * 2);
        }
    }

    public boolean contains(Citizen citizen) {
        for (int i = 0; i < count; i++) {
             if (members[i].equals(citizen)) return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Fraction getFraction() {
        return fraction;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    @Override
    public String toString() {
        return "Party{" + name + ", fraction - " + fraction + ", created " + dateOfCreation + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return Objects.equals(name, party.name) && fraction == party.fraction && Objects.equals(dateOfCreation, party.dateOfCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fraction, dateOfCreation);
    }

}
