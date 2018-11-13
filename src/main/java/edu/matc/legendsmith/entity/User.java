package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<UserLegendary> userLegendaries = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<UserLegendaryPrimaryItem> userPrimaryItems = new ArrayList<>();

    /**
     * Instantiates a new User.
     */
    public User() {

    }

    /**
     * Instantiates a new User with an username and password.
     *
     * @param username the username
     * @param password the password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
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


    public List<UserLegendary> getUserLegendaries() {
        return userLegendaries;
    }

    public void setUserLegendaries(List<UserLegendary> userLegendaries) {
        this.userLegendaries = userLegendaries;
    }

    public void addUserLegendary(UserLegendary userLegendary) {
        userLegendaries.add(userLegendary);
        userLegendary.setUser(this);
    }

    public void removeUserLegendary(UserLegendary userLegendary) {
        userLegendaries.remove(userLegendary);
        userLegendary.setUser(null);
    }

    /**
     * Gets the user's primary items.
     *
     * @return userPrimaryItems the user legendary primary items
     */
    public List<UserLegendaryPrimaryItem> getUserPrimaryItems() {
        return userPrimaryItems;
    }

    public void setUserPrimaryItems(List<UserLegendaryPrimaryItem> userPrimaryItems) {
        this.userPrimaryItems = userPrimaryItems;
    }

    public void addUserPrimaryItem(UserLegendaryPrimaryItem userLegendaryPrimaryItem) {
        userPrimaryItems.add(userLegendaryPrimaryItem);
        userLegendaryPrimaryItem.setUser(this);
    }

    public void removeUserPrimaryItem(UserLegendaryPrimaryItem userLegendaryPrimaryItem) {
        userPrimaryItems.remove(userLegendaryPrimaryItem);
        userLegendaryPrimaryItem.setUser(null);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
