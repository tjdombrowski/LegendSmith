package edu.matc.legendsmith.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User legendary.
 */
@Entity(name = "UserLegendary")
@Table(name = "UserLegendary")
public class UserLegendary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private double progress;
    private int tracking;
    private int priority;

    @ManyToOne
    @JoinColumn(name = "legendaryId",
        foreignKey = @ForeignKey(name = "UserLegendary_legendaryId_fk"))
    private Legendary legendary;

    @ManyToOne
    @JoinColumn(name = "userId",
        foreignKey = @ForeignKey(name = "UserLegendary_userId_fk"))
    private User user;

    /**
     * Instantiates a new User legendary.
     */
    public UserLegendary() {}

    /**
     * Instantiates a new User legendary.
     *
     * @param user      the user
     * @param legendary the legendary
     * @param progress  the progress
     */
    public UserLegendary(User user, Legendary legendary, int progress) {
        this.user = user;
        this.legendary = legendary;
        this.progress = progress;
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
     * Gets progress.
     *
     * @return the progress
     */
    public double getProgress() {
        return progress;
    }

    /**
     * Sets progress.
     *
     * @param progress the progress
     */
    public void setProgress(double progress) {
        this.progress = progress;
    }

    /**
     * Gets tracking.
     *
     * @return the tracking
     */
    public int getTracking() {
        return tracking;
    }

    /**
     * Sets tracking.
     *
     * @param tracking the tracking
     */
    public void setTracking(int tracking) {
        this.tracking = tracking;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets priority.
     *
     * @param priority the priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Gets legendary.
     *
     * @return the legendary
     */
    public Legendary getLegendary() {
        return legendary;
    }

    /**
     * Sets legendary.
     *
     * @param legendary the legendary
     */
    public void setLegendary(Legendary legendary) {
        this.legendary = legendary;
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

        UserLegendary that = (UserLegendary) o;

        if (id != that.id) return false;
        if (Double.compare(that.progress, progress) != 0) return false;
        if (tracking != that.tracking) return false;
        return priority == that.priority;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(progress);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + tracking;
        result = 31 * result + priority;
        return result;
    }

    @Override
    public String toString() {
        return "UserLegendary{" +
                "id=" + id +
                ", progress=" + progress +
                ", tracking=" + tracking +
                ", priority=" + priority +
                '}';
    }
}
