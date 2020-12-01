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
    @MapsId("seatsId")
    @JoinColumn(name = "seatsId")
    private Seats seats;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    @MapsId("distributorId")
    @JoinColumn(name = "distributorId")
    private Distributor distributor;

    @Column(name = "dateBought")
    private String dateBought;

    @Column(name = "numberOfTickets")
    private int numberOfTickets;

    public SoldTickets() {
    }

    public SoldTickets(Seats seats, Distributor distributor, String dateBought, int numberOfTickets) {
        this.seats = seats;
        this.distributor = distributor;
        this.dateBought = dateBought;
        this.numberOfTickets = numberOfTickets;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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

