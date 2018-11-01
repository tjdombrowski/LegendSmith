package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "legendaryId",
        foreignKey = @ForeignKey(name = "LegendaryPrimaryItem_legendaryId_fk"))
    private Legendary legendary;

    @ManyToOne
    @JoinColumn(name = "primaryItemId",
        foreignKey = @ForeignKey(name = "LegendaryPrimaryItem_primaryItemId_fk"))
    private PrimaryItem primaryItem;


    @OneToMany(mappedBy = "legendaryPrimaryItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

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


    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setLegendaryPrimaryItem(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setLegendaryPrimaryItem(null);
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
