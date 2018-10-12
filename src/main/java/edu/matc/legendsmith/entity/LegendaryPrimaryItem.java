package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Legendary primary item.
 *
 * The join table between PrimaryItem and Legendary.
 * Id is the pk
 *
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


    /**
     * Instantiates a new Legendary primary item.
     */
    public LegendaryPrimaryItem() {}

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
