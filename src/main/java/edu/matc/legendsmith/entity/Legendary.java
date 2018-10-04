package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @Column(name = "name")
    private String name;

    @Column (name = "PictureReference") //uncapitalize
    private String pictureReference;

    @Column (name = "Type") //uncapitalize
    private String type; //uncapitalize

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
