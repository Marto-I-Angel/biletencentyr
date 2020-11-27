package entities;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seatsId", unique = true, updatable = false, nullable = false)
    private int seatsId;

    @Column(name="seatsTypeId")
    private String seatsType;

    @Column(name = "numberOfSeats")
    private int numberOfSeats;

    @Column(name = "numberOfReservedSeats")
    private int numberOfReservedSeats;

    @Column(name = "price")
    private float price;

    @ManyToOne(targetEntity = Event.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId", referencedColumnName = "eventId")
    private Event event;

    public Seats() {

    }

    public Seats(String text, String text1, String text2) {
        setSeatsType(text);
        setNumberOfSeats(Integer.parseInt(text1));
        setNumberOfReservedSeats(0);
        setPrice(text2);
    }

    @Override
    public String toString() {
        return "SeatsID: " + seatsId + "\n" +
                "SeatsType: " + seatsType + "\n";
    }
    public String getPrice() {
        return String.format("%.2f", price) + "лв.";
    }
    public void setPrice(String input) {
        if(input.contains("л"))
            this.price = Float.parseFloat(input.substring(0,input.indexOf("л")));
        else this.price = Float.parseFloat(input);
    }
    public int getSeatsId() {
        return seatsId;
    }
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getSeatsType() {
        return seatsType;
    }

    public void setSeatsType(String seatsType) {
        this.seatsType = seatsType;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getNumberOfReservedSeats() {
        return this.numberOfReservedSeats;
    }

    public void setNumberOfReservedSeats(int numberOfReservedSeats) {
        this.numberOfReservedSeats = numberOfReservedSeats;
    }
}
