package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seats")
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seatsId", unique = true, updatable = false, nullable = false)
    private int seatsId;

    @ManyToOne
    @JoinColumn(name="seatsTypeId", nullable=false)
    private Seatstype seatsType;

    @OneToMany(mappedBy = "seats")
    private List<Reservation> events = new ArrayList<>();

    public Seats() {

    }

    @Override
    public String toString() {
        return "SeatsID: " + seatsId + "\n" +
                "SeatsType: " + seatsType + "\n" +
                "Events: " + events + "\n\n";
    }

    public int getSeatsId() {
        return seatsId;
    }

    public Seatstype getSeatsType() {
        return seatsType;
    }

    public void setSeatsType(Seatstype seatsType) {
        this.seatsType = seatsType;
    }

    public List<Reservation> getEvents() {
        return events;
    }

    public void setEvents(List<Reservation> events) {
        this.events = events;
    }
}
