package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "userRole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userRoleId", unique = true, nullable = false)
    private int userRoleId;

    @Column(name = "role", nullable = false)
    private String role;

    public UserRole(){

    }

    public UserRole(String role){
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
