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

    @Column(name="eventTypeId", nullable=false)
    private String eventType;

    @ManyToOne
    @JoinColumn(name="hostId", nullable=false)
    private Host host;

    @Column(name = "beginDate")
    private String beginDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name="statusId", nullable=false)
    private String status;

    @Column(name = "name")
    private String name;

    @Column(name = "ticketLimit")
    private int ticketLimit;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<Distribution> listDist = new ArrayList<>();

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER,cascade = CascadeType.ALL )
    private List<Seats> seats = new ArrayList<>();

    public Event() {

    }

    @Override
    public String toString() {
        return  " Name: " + name +
                "  Type: " + eventType + "\n" +
                " BeginDate: " + beginDate +
                "  EndDate: " + endDate + "\n" +
                " Status: " + status +
                "  Location: " + location ;
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

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Distribution> getListDist() {
        return listDist;
    }

    public int getTicketLimit() {
        return ticketLimit;
    }

    public void setTicketLimit(int ticketLimit) {
        this.ticketLimit = ticketLimit;
    }

    public void setListDist(List<Distribution> listDist) {
        this.listDist = listDist;
    }

    public List<Seats> getSeats() {
        return seats;
    }

    public void setSeats(List<Seats> seats) {
        this.seats = seats;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
