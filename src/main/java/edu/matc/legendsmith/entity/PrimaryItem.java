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

    private String name;

    private String pictureReference;

    @OneToMany(mappedBy = "primaryItem")
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


    public List<LegendaryPrimaryItem> getLegendaries() {
        return legendaries;
    }

    public void setLegendaries(List<LegendaryPrimaryItem> legendaries) {
        this.legendaries = legendaries;
    }

    public void addLegendary(LegendaryPrimaryItem legendary) {
        legendaries.add(legendary);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrimaryItem that = (PrimaryItem) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return pictureReference != null ? pictureReference.equals(that.pictureReference) : that.pictureReference == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pictureReference != null ? pictureReference.hashCode() : 0);
        return result;
    }
}
