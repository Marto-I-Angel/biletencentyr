package entities;

import javax.persistence.*;

@Entity
@Table(name = "distribution")
public class Distribution {
    @EmbeddedId
    private DistributionID id = new DistributionID();

    @ManyToOne(fetch= FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.PERSIST})
    @MapsId("eventId")
    @JoinColumn(name = "eventId")
    private Event event;

    @ManyToOne(fetch= FetchType.EAGER ,cascade = {CascadeType.DETACH,CascadeType.PERSIST})
    @MapsId("distributorId")
    @JoinColumn(name = "distributorId")
    private Distributor distributor;

    @Column(name = "fee")
    private float fee;

    public Distribution(){

    }

    public Distribution(Event event, Distributor distributor, float price) {
        this.event = event;
        this.distributor = distributor;
        this.fee = price;
    }

    @Override
    public String toString() {
        return fee+"";
    }


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public void setId(DistributionID id) {
        this.id = id;
    }

    public DistributionID getId() {
        return id;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float price) {
        this.fee = price;
    }
}
