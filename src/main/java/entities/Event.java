package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId", unique = true, updatable = false, nullable = false)
    private int eventId;

    @Column(name="eventTypeId", nullable=false)
    private String eventType;

    @ManyToOne
    @JoinColumn(name="hostId", nullable=false)
    private Host host;

    @Column(name = "beginDate")
    private Date beginDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name="statusId", nullable=false)
    private String status;

    @Column(name = "name")
    private String name;

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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventName(String input) {this.name = input;}

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
