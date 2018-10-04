package edu.matc.legendsmith.entity;

/**
 * The type Primary item.
 */
public class PrimaryItem {
    private int id;
    private String name;
    private String pictureReference;

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
