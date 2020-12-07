package entities;

import javax.persistence.*;
import java.util.Objects;

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

    public Host(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Host: " + user;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return hostId == host.hostId &&
                Objects.equals(user, host.user);
    }
}



