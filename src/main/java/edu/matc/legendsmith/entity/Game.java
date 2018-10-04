package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The type Game.
 */
@Entity(name = "Game")
@Table(name = "Game")
public class Game {
    //TODO Determine what kind of annotations are needed here
    @Id
    @GenericGenerator(name = "native",strategy = "native")
    private String name;

    /**
     * Instantiates a new Game.
     */
    public Game() {}

    /**
     * Instantiates a new Game.
     *
     * @param name the name
     */
    public Game(String name) {
        this.name = name;
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
}
