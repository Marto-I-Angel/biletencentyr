package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId", unique = true, updatable = false, nullable = false)
    private int eventId;

    @ManyToOne
    @JoinColumn(name="eventTypeId", nullable=false)
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name="hostId", nullable=false)
    private Host host;

    @ManyToOne
    @JoinColumn(name="statusId", nullable=false)
    private Status status;

    @Column(name = "rating")
    private int rating;

    @ManyToMany(targetEntity = Distributor.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "distribution",
            joinColumns = @JoinColumn(name = "eventId"),
            inverseJoinColumns = @JoinColumn(name = "distributorId")
    )
    private List<Distributor> listDist = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<Reservation> seats = new ArrayList<>();

    public Event() {

    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Distributor> getListDist() {
        return listDist;
    }

    public void setListDist(List<Distributor> listDist) {
        this.listDist = listDist;
    }

    public List<Reservation> getSeats() {
        return seats;
    }

    public void setSeats(List<Reservation> seats) {
        this.seats = seats;
    }
}
