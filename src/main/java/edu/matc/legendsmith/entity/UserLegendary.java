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

    private Integer progress;
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

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLegendary that = (UserLegendary) o;

        if (id != that.id) return false;
        if (tracking != that.tracking) return false;
        if (priority != that.priority) return false;
        return progress != null ? progress.equals(that.progress) : that.progress == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (progress != null ? progress.hashCode() : 0);
        result = 31 * result + tracking;
        result = 31 * result + priority;
        return result;
    }
}
