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
    private int id;

    private String role;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private User user;

    /**
     * Instantiates a new User role.
     */
    public UserRole() {}

    /**
     * Instantiates a new User role.
     *
     * @param user the user
     * @param role the role
     */
    public UserRole(User user, String role) {
        this.user = user;
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
}
