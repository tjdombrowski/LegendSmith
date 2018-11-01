package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The type Task.
 */
@Entity(name = "Task")
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private String name;

    private String description;

    private int quantity;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "legendaryPrimaryItemId",
        foreignKey = @ForeignKey(name = "Task_legendaryPrimaryItemId_fk"))
    private LegendaryPrimaryItem legendaryPrimaryItem;

    /**
     * Instantiates a new Task.
     */
    public Task() {}


    /**
     * Instantiates a new Task.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param quantity    the quantity
     */
    public Task(int id, String name, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
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
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    public LegendaryPrimaryItem getLegendaryPrimaryItem() {
        return legendaryPrimaryItem;
    }

    public void setLegendaryPrimaryItem(LegendaryPrimaryItem legendaryPrimaryItem) {
        this.legendaryPrimaryItem = legendaryPrimaryItem;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (quantity != task.quantity) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        return description != null ? description.equals(task.description) : task.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}
