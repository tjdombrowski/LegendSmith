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

    @EmbeddedId
    private UserLegendaryId id;

    private int progress;
    private int tracking;
    private int priority;

    /**
     * Instantiates a new User legendary.
     */
    public UserLegendary() {}

    /**
     * Instantiates a new User legendary.
     *
     * @param tracking the tracking
     */
    public UserLegendary(int tracking) {
        this.tracking = tracking;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public UserLegendaryId getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(UserLegendaryId id) {
        this.id = id;
    }

    /**
     * Gets progress.
     *
     * @return the progress
     */
    public int getProgress() {
        return progress;
    }

    /**
     * Sets progress.
     *
     * @param progress the progress
     */
    public void setProgress(int progress) {
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
}
