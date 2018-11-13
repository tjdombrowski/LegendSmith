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

    @ManyToOne
    @JoinColumn(name = "legendaryPrimaryItemId",
        foreignKey = @ForeignKey(name = "UserLegendaryPrimaryItem_fk1"))
    private LegendaryPrimaryItem legendaryPrimaryItem;

    @ManyToOne
    @JoinColumn(name = "userId",
        foreignKey = @ForeignKey(name = "UserLegendaryPrimaryItem_userId_fk2"))
    private User user;

    public UserLegendaryPrimaryItem() {}

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

    public LegendaryPrimaryItem getLegendaryPrimaryItem() {
        return legendaryPrimaryItem;
    }

    public void setLegendaryPrimaryItem(LegendaryPrimaryItem legendaryPrimaryItem) {
        this.legendaryPrimaryItem = legendaryPrimaryItem;
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

        UserLegendaryPrimaryItem that = (UserLegendaryPrimaryItem) o;

        if (id != that.id) return false;
        return progress == that.progress;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + progress;
        return result;
    }
}
