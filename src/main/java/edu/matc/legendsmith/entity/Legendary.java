package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @Column (name = "Game")
    private String game;

    public Legendary() {

    }

    public Legendary(String name, String type, String game) {
        this.name = name;
        this.type = type;
        this.game = game;
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
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Legendary{id = " + id + '\''
                + ", name = " + name + '\''
                + ", type = " + type + '\''
                + ", game = " + game + '\''
                + "}";
    }


}
