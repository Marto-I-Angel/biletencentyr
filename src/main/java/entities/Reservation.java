package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @EmbeddedId
    private ReservationID id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "eventId")
    private Event event;

    @ManyToOne
    @MapsId("seatsId")
    @JoinColumn(name = "seatsId")
    private Seats seats;

    @Column(name = "beginDate")
    private Date beginDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "price")
    private float price;

    public Reservation(){

    }

    public Reservation(Event event, Seats seats, Date beginDate, Date endDate, float price) {
        this.event = event;
        this.seats = seats;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Seats getSeats() {
        return seats;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
