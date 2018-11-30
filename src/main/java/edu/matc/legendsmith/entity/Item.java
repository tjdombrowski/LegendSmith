package edu.matc.legendsmith.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Item")
@Entity(name = "Item")
public class Item {
    @Id
    private int gw2ItemId;
    private String name;



}
