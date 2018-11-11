package edu.matc.legendsmith.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "UserLegendary")
@Table(name = "UserLegendary")
public class UserLegendary {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private int progress;
    private int tracking;
    private int priority;

    @ManyToOne
    @JoinColumn(name = "legendaryId",
        foreignKey = @ForeignKey(name = "UserLegendary_legendaryId_fk"))
    private Legendary legendary;

    @ManyToOne
    @JoinColumn(name = "userId",
        foreignKey = @ForeignKey(name = "UserLegendary_userId_fk"))
    private User user;

    public UserLegendary() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getTracking() {
        return tracking;
    }

    public void setTracking(int tracking) {
        this.tracking = tracking;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Legendary getLegendary() {
        return legendary;
    }

    public void setLegendary(Legendary legendary) {
        this.legendary = legendary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
