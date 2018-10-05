package edu.matc.legendsmith.entity;

import javax.persistence.*;

/**
 * The type Legendary primary item.
 */
@Entity(name = "LegendaryPrimaryItem")
@Table(name = "LegendaryPrimaryItem")
public class LegendaryPrimaryItem {
    @Id
    private int id;
    private int legendaryId;
    private int primaryItemId;

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
     * Gets legendary id.
     *
     * @return the legendary id
     */
    public int getLegendaryId() {
        return legendaryId;
    }

    /**
     * Sets legendary id.
     *
     * @param legendaryId the legendary id
     */
    public void setLegendaryId(int legendaryId) {
        this.legendaryId = legendaryId;
    }

    /**
     * Gets primary item id.
     *
     * @return the primary item id
     */
    public int getPrimaryItemId() {
        return primaryItemId;
    }

    /**
     * Sets primary item id.
     *
     * @param primaryItemId the primary item id
     */
    public void setPrimaryItemId(int primaryItemId) {
        this.primaryItemId = primaryItemId;
    }

    /**
     * Returns the value of LegendaryPrimaryItem fields.
     *
     * @return a string of LegendaryPrimaryItem info
     */
    @Override
    public String toString() {
        return "LegendaryPrimaryItem{" +
                "id=" + id +
                ", legendaryId=" + legendaryId +
                ", primaryItemId=" + primaryItemId +
                '}';
    }
}
