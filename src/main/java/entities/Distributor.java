package entities;

import javax.persistence.*;

@Entity
@Table(name = "distributor")
public class Distributor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distributorId", unique = true, updatable = false, nullable = false)
    private int distributorId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "fee")
    private float fee;

    public Distributor(){

    }

    public Distributor(User user, float fee) {
        this.user = user;
        this.fee = fee;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }
}
