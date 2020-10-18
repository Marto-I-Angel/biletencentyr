package entities;

import javax.persistence.*;

@Entity
@Table(name = "seatstype")
public class Seatstype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seatsTypeId", unique = true, updatable = false, nullable = false)
    private int seatsTypeId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Seatstype() {

    }

    public Seatstype(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getSeatsTypeId() {
        return seatsTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
