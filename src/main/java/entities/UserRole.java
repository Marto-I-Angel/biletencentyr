package entities;

import javax.persistence.*;

@Entity
@Table(name = "userRole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userRoleId", unique = true, updatable = false, nullable = false)
    private int userRoleId;

    @Column(name = "role", nullable = false)
    private String role;

    public UserRole(){

    }

    public UserRole(String role){
        this.role = role;
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
