package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * The type Primary item.
 */
@Entity(name = "PrimaryItem")
@Table(name = "PrimaryItem")
public class PrimaryItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "pictureReference")
    private String pictureReference;

    @OneToMany(mappedBy = "PrimaryItem", fetch = FetchType.EAGER)
    private List<LegendaryPrimaryItem> legendaries = new ArrayList<>();


    /**
     * Instantiates a new Primary item.
     */
    public PrimaryItem() {}

    /**
     * Instantiates a new Primary item.
     *
     * @param id   the id
     * @param name the name
     */
    public PrimaryItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets legendaries.
     *
     * @return the legendaries
     */
    public List<LegendaryPrimaryItem> getLegendaries() {
        return legendaries;
    }

    /**
     * Sets legendaries.
     *
     * @param legendaries the legendaries
     */
    public void setLegendaries(List<LegendaryPrimaryItem> legendaries) {
        this.legendaries = legendaries;
    }

    /**
     * Add legendary.
     *
     * @param legendary the legendary
     */
    public void addLegendary(LegendaryPrimaryItem legendary) {
        legendaries.add(legendary);
    }

    /**
     * Remove legendary.
     *
     * @param legendary the legendary
     */
    public void removeLegendary(LegendaryPrimaryItem legendary) {
        legendaries.remove(legendary);
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
}
