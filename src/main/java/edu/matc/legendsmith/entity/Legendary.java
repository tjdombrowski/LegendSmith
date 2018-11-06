package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * The type Legendary.
 */
@Entity(name = "Legendary")
@Table(name = "Legendary")
@XmlRootElement
public class Legendary {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private String name;

    private String pictureReference;

    private String type;

    /*@OneToMany(mappedBy = "legendary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLegendary> userLegendaries = new ArrayList<>();*/

    @OneToMany(mappedBy = "legendary", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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
     * Instantiates a new Legendary.
     *
     * @param id   the id
     * @param name the name
     * @param type the type
     */
    public Legendary(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }



    public List<LegendaryPrimaryItem> getPrimaryItems() {
        return primaryItems;
    }


    public void setPrimaryItems(List<LegendaryPrimaryItem> primaryItems) {
        this.primaryItems = primaryItems;
    }


    public void addPrimaryItem(LegendaryPrimaryItem primaryItem) {

        primaryItems.add(primaryItem);
        primaryItem.setLegendary(this);
    }

    public void removePrimaryItem(LegendaryPrimaryItem primaryItem) {

        primaryItems.remove(primaryItem);
        primaryItem.setLegendary(null);
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

    /* For connecting to the User entity

    public List<UserLegendary> getUserLegendaries() {
        return userLegendaries;
    }
    public void setUserLegendaries(List<UserLegendary> userLegendaries) {
        this.userLegendaries = userLegendaries;
    }
    public void addUserLegendary(UserLegendary userLegendary) {
        userLegendaries.add(userLegendary);
        userLegendary.setLegendary(this);
    }
    public void removeUserLegendary(UserLegendary userLegendary) {
        userLegendaries.remove(userLegendary);
        userLegendary.setLegendary(null);
    }
    (*/

    @Override
    public String toString() {
        return "Legendary{id = " + id + '\''
                + ", name = " + name + '\''
                + ", type = " + type + '\''
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Legendary legendary = (Legendary) o;

        if (id != legendary.id) return false;
        if (name != null ? !name.equals(legendary.name) : legendary.name != null) return false;
        if (pictureReference != null ? !pictureReference.equals(legendary.pictureReference) : legendary.pictureReference != null)
            return false;
        return type != null ? type.equals(legendary.type) : legendary.type == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pictureReference != null ? pictureReference.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
