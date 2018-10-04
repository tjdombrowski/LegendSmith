package edu.matc.legendsmith.entity;

/**
 * The type Task.
 */
public class Task {
    private int id;
    private String name;
    private int primaryItemId;
    private int quantity;

    /**
     * Instantiates a new Task.
     */
    public Task() {}

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
}
