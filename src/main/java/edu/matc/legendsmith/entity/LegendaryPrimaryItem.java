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
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @ManyToOne
    private Legendary legendary;

    @ManyToOne
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



}
