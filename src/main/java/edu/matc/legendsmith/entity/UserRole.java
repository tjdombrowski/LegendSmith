package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The type User role.
 */
@Entity(name = "UserRole")
@Table(name = "UserRole")
public class UserRole {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private String role;

    @ManyToOne
    @JoinColumn(name = "userId",
            foreignKey = @ForeignKey(name = "UserRole_fk")
    )
    private User user;


    /**
     * Instantiates a new User role.
     */
    public UserRole() {}

    /**
     * Instantiates a new User role.
     *
     * @param role the role
     */
    public UserRole(String role) {
        this.role = role;
    }


    /**
     * Instantiates a new User role.
     *
     * @param id   the id
     * @param role the role
     */
    public UserRole(int id, String role) {
        this.id = id;
        this.role = role;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }


    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (id != userRole.id) return false;
        return role != null ? role.equals(userRole.role) : userRole.role == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
