package edu.matc.legendsmith.entity;

public class Legendary {
    private int id;
    private String name;
    private String pictureReference;
    private String type;
    private String Game;

    public Legendary() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureReference() {
        return pictureReference;
    }

    public void setPictureReference(String pictureReference) {
        this.pictureReference = pictureReference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGame() {
        return Game;
    }

    public void setGame(String game) {
        Game = game;
    }




}
