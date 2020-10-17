package entities;

import javax.persistence.*;

@Entity
@Table(name = "eventType")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventTypeId", unique = true, updatable = false, nullable = false)
    private int eventTypeId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public EventType() {

    }

    public EventType(String name) {
        this.name = name;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
