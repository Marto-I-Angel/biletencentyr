package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class SoldTicketsID implements Serializable {
    private static final long serialVersionUID = 1L;

        @Column(name = "seatsId")
        private int seatsId;

        @Column(name = "distributorId")
        private int distributorId;

        public SoldTicketsID() {
            seatsId = -1;
            distributorId = -1;
        }

        public SoldTicketsID(int seatsId, int distributorId) {
            this.seatsId = seatsId;
            this.distributorId = distributorId;
        }

        public int getSeatsId() {
            return seatsId;
        }

        public void setSeatsId(int seatsId) {
            this.seatsId = seatsId;
        }

        public int getDistributorId() {
            return distributorId;
        }

        public void setDistributorId(int distributorId) {
            this.distributorId = distributorId;
        }

        @Override
        public int hashCode() {
            return seatsId + distributorId;
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof entities.SoldTicketsID) {
                entities.SoldTicketsID otherId = (entities.SoldTicketsID) object;
                return (otherId.seatsId == this.seatsId)
                        && (otherId.distributorId == this.distributorId);
            }
            return false;
        }

    
}
