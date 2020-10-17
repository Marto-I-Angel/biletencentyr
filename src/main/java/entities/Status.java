package entities;


import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusId", unique = true, updatable = false, nullable = false)
    private int statusId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Status() {

    }

    public Status(String name) {
        this.name = name;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
