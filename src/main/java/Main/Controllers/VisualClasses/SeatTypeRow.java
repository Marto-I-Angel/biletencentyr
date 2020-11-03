package Main.Controllers.VisualClasses;

public class SeatTypeRow {
    private String numberOfSeats;
    private double price;
    private String seatType;

    public SeatTypeRow(String seatType, String numberOfSeats, String price) {
        setSeatType(seatType);
        setNumberOfSeats(numberOfSeats);
        setPrice(price);
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setPrice(String input) {
        if(input.contains("л"))
            this.price = Double.parseDouble(input.substring(0,input.indexOf("л")));
        else this.price = Double.parseDouble(input);
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getPrice() {

        return String.format("%.2f", price) + "лв.";
    }

    public String getSeatType() {
        return seatType;
    }
}
