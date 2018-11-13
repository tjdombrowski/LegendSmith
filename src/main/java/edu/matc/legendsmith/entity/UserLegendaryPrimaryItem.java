package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "UserLegendaryPrimaryItem")
@Table(name = "UserLegendaryPrimaryItem")
public class UserLegendaryPrimaryItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private int progress;

    public UserLegendaryPrimaryItem() {}

}
