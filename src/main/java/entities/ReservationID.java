package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ReservationID implements Serializable {
    @Column(name = "eventId")
    private int eventId;

    @Column(name = "seatsId")
    private int seatsId;

    ReservationID(){

    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSeatsId() {
        return seatsId;
    }

    public void setSeatsId(int seatsId) {
        this.seatsId = seatsId;
    }

    @Override
    public int hashCode() {
        return eventId + seatsId;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ReservationID) {
            ReservationID otherId = (ReservationID) object;
            return (otherId.eventId == this.eventId)
                    && (otherId.seatsId == this.seatsId);
        }
        return false;
    }
}