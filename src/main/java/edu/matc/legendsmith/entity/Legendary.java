package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * The type Legendary.
 */
@Entity(name = "Legendary")
@Table(name = "Legendary")
public class Legendary {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private String name;

    private String pictureReference;

    private String type;

    @OneToMany(mappedBy = "Legendary")
    private List<LegendaryPrimaryItem> primaryItems = new ArrayList<>();

    /**
     * Instantiates a new Legendary.
     */
    public Legendary() {

    }

    /**
     * Instantiates a new Legendary.
     *
     * @param name the name
     * @param type the type
     */
    public Legendary(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Gets primary items.
     *
     * @return the primary items
     */
    public List<LegendaryPrimaryItem> getPrimaryItems() {
        return primaryItems;
    }

    /**
     * Sets primary items.
     *
     * @param primaryItems the primary items
     */
    public void setPrimaryItems(List<LegendaryPrimaryItem> primaryItems) {
        this.primaryItems = primaryItems;
    }

    /**
     * Add primary item.
     *
     * @param primaryItem the primary item
     */
    public void addPrimaryItem(LegendaryPrimaryItem primaryItem) {
        primaryItems.add(primaryItem);
    }

    /**
     * Remove primary item.
     *
     * @param primaryItem the primary item
     */
    public void removePrimaryItem(LegendaryPrimaryItem primaryItem) {
        primaryItems.remove(primaryItem);
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets picture reference.
     *
     * @return the picture reference
     */
    public String getPictureReference() {
        return pictureReference;
    }

    /**
     * Sets picture reference.
     *
     * @param pictureReference the picture reference
     */
    public void setPictureReference(String pictureReference) {
        this.pictureReference = pictureReference;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns a string of Legendary information.
     *
     * @return a string consisting of Legendary information
     */
    @Override
    public String toString() {
        return "Legendary{id = " + id + '\''
                + ", name = " + name + '\''
                + ", type = " + type + '\''
                + "}";
    }


}
