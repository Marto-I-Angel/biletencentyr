package models;

public class EventView {

    private int eventId;

    private String name;
    private String type;
    private String beginDate;
    private String endDate;
    private String status;
    private float fee;
    private int totalTickets;
    private int soldTickets;
    private String location;
    private String tickets;

    public EventView(int eventId,String name, String type, String beginDate, String endDate, String status, float fee, int totalTickets, int soldTickets, String location) {
        this.eventId = eventId;
        this.name = name;
        this.type = type;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.status = status;
        this.fee = fee;
        this.totalTickets = totalTickets;
        this.soldTickets = soldTickets;
        this.tickets = this.getTickets();
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public void setSoldTickets(int soldTickets) {
        this.soldTickets = soldTickets;
    }

    public String getTickets()
    {
        tickets = calcTickets();
        return tickets;
    }

    private String calcTickets() {
        if(soldTickets == totalTickets ) return "FULL!";
        return this.soldTickets + "/" + this.totalTickets;
    }

    public int getEventId() {
        return this.eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
