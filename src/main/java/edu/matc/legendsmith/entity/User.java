package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The type User.
 */
@Entity(name = "User")
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    /**
     * Instantiates a new User.
     */
    public User() {

    }

    /**
     * Instantiates a new User with an id, username, and a password.
     *
     * @param id       the id
     * @param username the username
     * @param password the password
     */
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
