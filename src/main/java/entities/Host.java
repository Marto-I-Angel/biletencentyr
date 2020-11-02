package entities;

import javax.persistence.*;

@Entity
@Table(name = "host")
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hostId", unique = true, updatable = false, nullable = false)
    private int hostId;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "userId", nullable = true, unique = true)
    private User user;

    public Host() {

    }

    @Override
    public String toString() {
        return "Host: " + user;
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



