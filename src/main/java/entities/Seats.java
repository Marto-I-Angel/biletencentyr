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

    @ManyToOne
    @JoinColumn(name="seatsTypeId", nullable=false)
    private Seatstype seatsTypeId;

    @OneToMany(mappedBy = "seats")
    private List<Reservation> event = new ArrayList<>();

    public Seats() {

    }

    public int getSeatsId() {
        return seatsId;
    }

    public Seatstype getSeatsTypeId() {
        return seatsTypeId;
    }

    public void setSeatsTypeId(Seatstype seatsTypeId) {
        this.seatsTypeId = seatsTypeId;
    }
}
