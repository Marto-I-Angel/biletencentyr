package entities;

import javax.persistence.*;

@Entity
@Table(name = "host")
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hostId", unique = true, updatable = false, nullable = false)
    private int hostId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Host() {

    }

    public Host(User user) {
        this.user = user;
    }

    public int getHostId() {
        return hostId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}



