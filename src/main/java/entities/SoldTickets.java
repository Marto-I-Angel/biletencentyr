package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "soldtickets")
public class SoldTickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soldTicketsId", unique = true, updatable = false, nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "seatsId")
    private Seats seats;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "distributorId")
    private Distributor distributor;

    @Column(name = "dateBought")
    private String dateBought;

    @Column(name = "numberOfTickets")
    private int numberOfTickets;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "typePayment")
    private String typePayment;

    public SoldTickets() {
    }

    public SoldTickets(Seats seats, Distributor distributor, String dateBought, int numberOfTickets,String typePayment) {
        this.seats = seats;
        this.distributor = distributor;
        this.dateBought = dateBought;
        this.numberOfTickets = numberOfTickets;
        this.typePayment = typePayment;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public Seats getSeats() {
        return seats;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public String getDateBought() {
        return dateBought;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public void setDateBought(String dateBought) {
        this.dateBought = dateBought;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public String toString() {
        return "SoldTickets{" +
                "seats=" + seats +
                ", distributor=" + distributor +
                ", dateBought='" + dateBought + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SoldTickets)) return false;
        SoldTickets that = (SoldTickets) o;
        return getNumberOfTickets() == that.getNumberOfTickets() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getSeats(), that.getSeats()) &&
                Objects.equals(getDistributor(), that.getDistributor()) &&
                Objects.equals(getDateBought(), that.getDateBought());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSeats(), getDistributor(), getDateBought(), getNumberOfTickets());
    }
}

