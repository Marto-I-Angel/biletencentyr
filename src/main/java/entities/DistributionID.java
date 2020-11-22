package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DistributionID implements Serializable {
    @Column(name = "eventId")
    private int eventId;

    @Column(name = "distributorId")
    private int distributorId;

    public DistributionID() {
        eventId = -1;
        distributorId = -1;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    @Override
    public int hashCode() {
        return eventId + distributorId;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof DistributionID) {
            DistributionID otherId = (DistributionID) object;
            return (otherId.eventId == this.eventId)
                    && (otherId.distributorId == this.distributorId);
        }
        return false;
    }

}