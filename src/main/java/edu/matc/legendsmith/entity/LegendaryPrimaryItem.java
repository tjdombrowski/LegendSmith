package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Legendary primary item.
 * <p>
 * The join table between PrimaryItem and Legendary.
 * Id is the pk
 * <p>
 * One to many with Legendary
 * One to many with PrimaryItem
 */
@Entity(name = "LegendaryPrimaryItem")
@Table(name = "LegendaryPrimaryItem")
public class LegendaryPrimaryItem {
    @ManyToOne(fetch = FetchType.EAGER)
    private Legendary legendary;

    @ManyToOne(fetch = FetchType.EAGER)
    private PrimaryItem primaryItem;


    /**
     * Instantiates a new Legendary primary item.
     */
    public LegendaryPrimaryItem() {}

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
     * Gets primary item.
     *
     * @return the primary item
     */
    public PrimaryItem getPrimaryItem() {
        return primaryItem;
    }

    /**
     * Sets primary item.
     *
     * @param primaryItem the primary item
     */
    public void setPrimaryItem(PrimaryItem primaryItem) {
        this.primaryItem = primaryItem;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegendaryPrimaryItem that = (LegendaryPrimaryItem) o;

        if (id != that.id) return false;
        if (legendary != null ? !legendary.equals(that.legendary) : that.legendary != null) return false;
        return primaryItem != null ? primaryItem.equals(that.primaryItem) : that.primaryItem == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (legendary != null ? legendary.hashCode() : 0);
        result = 31 * result + (primaryItem != null ? primaryItem.hashCode() : 0);
        return result;
    }
}
