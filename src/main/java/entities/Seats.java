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

    @Column(name="seatsTypeId")
    private String seatsType;

    @Column(name = "numberOfSeats")
    int numberOfSeats;

    @Column(name = "price")
    float price;

    public Seats() {

    }

    public Seats(String text, String text1, String text2) {
        setSeatsType(text);
        setNumberOfSeats(Integer.parseInt(text1));
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
}
