package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "distributor")
public class Distributor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distributorId", unique = true, updatable = false, nullable = false)
    private int distributorId;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "userId", unique = true)
    private User user;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Distribution> events = new ArrayList<>();

    @Column(name = "rating")
    private int rating;

    public Distributor(){

    }

    public Distributor(User user) {
        this.user = user;
        this.rating = 0;
    }

    public Distributor(User user, int rating) {
        this.user = user;
        this.rating = rating;
    }

    public boolean distributionEquals(Event event) {
        return distributorId == event.getEventId();
    }

    public int getDistributorId() {
        return distributorId;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public void setEvents(List<Distribution> events) {
        this.events = events;
    }

    public List<Distribution> getEvents() {
        return events;
    }

    public void addToRating(int tickets) {
        this.rating += tickets;
    }

//    public List<SoldTickets> getSoldTickets() {
//        return soldTickets;
//    }
//
//    public void setSoldTickets(List<SoldTickets> soldTickets) {
//        this.soldTickets = soldTickets;
//    }
}
