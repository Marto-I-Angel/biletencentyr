package models;

public class TicketView {
    private String eventName;
    private String typeSeats;
    private int numberOfSeats;
    private String dateBought;
    private float totalValue;
    private String personNames;
    private String typePayment;

    public TicketView(String eventName, String typeSeats, int numberOfSeats, String dateBought, float seatPrice, String typePayment, String firstName,String middleName, String lastName) {
        this.eventName = eventName;
        this.typeSeats = typeSeats;
        this.numberOfSeats = numberOfSeats;
        this.dateBought = dateBought;
        this.totalValue = seatPrice * numberOfSeats;
        this.typePayment = typePayment;
        this.personNames = firstName + " " + middleName + " " + lastName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTypeSeats() {
        return typeSeats;
    }

    public void setTypeSeats(String typeSeats) {
        this.typeSeats = typeSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getDateBought() {
        return dateBought;
    }

    public void setDateBought(String dateBought) {
        this.dateBought = dateBought;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    public String getPersonNames() {
        return personNames;
    }

    public void setPersonNames(String personNames) {
        this.personNames = personNames;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }
}
