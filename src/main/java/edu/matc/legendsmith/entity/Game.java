package edu.matc.legendsmith.entity;

/**
 * The type Game.
 */
public class Game {

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
