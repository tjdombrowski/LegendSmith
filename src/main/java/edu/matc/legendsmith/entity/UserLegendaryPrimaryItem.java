package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "UserLegendaryPrimaryItem")
@Table(name = "UserLegendaryPrimaryItem")
public class UserLegendaryPrimaryItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private double progress;

    @ManyToOne
    @JoinColumn(name = "legendaryPrimaryItemId",
        foreignKey = @ForeignKey(name = "UserLegendaryPrimaryItem_fk1"))
    private LegendaryPrimaryItem legendaryPrimaryItem;

    @ManyToOne
    @JoinColumn(name = "userId",
        foreignKey = @ForeignKey(name = "UserLegendaryPrimaryItem_userId_fk2"))
    private User user;

    //TODO Change all arrays arranged like this to be instantiated in the constructor
    @OneToMany(mappedBy = "userPrimaryItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserLegendaryPrimaryItemTask> userTasks = new ArrayList<>();

    public UserLegendaryPrimaryItem() {}

    public UserLegendaryPrimaryItem(User user, LegendaryPrimaryItem legendaryPrimaryItem, int progress) {
        this.user = user;
        this.legendaryPrimaryItem = legendaryPrimaryItem;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
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

    public List<UserLegendaryPrimaryItemTask> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<UserLegendaryPrimaryItemTask> userTasks) {
        this.userTasks = userTasks;
    }

    public void addUserTask(UserLegendaryPrimaryItemTask userTask) {
        userTasks.add(userTask);
        userTask.setUserPrimaryItem(this);
    }

    public void removeUserTask(UserLegendaryPrimaryItemTask userTask) {
        userTasks.remove(userTask);
        userTask.setUserPrimaryItem(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLegendaryPrimaryItem that = (UserLegendaryPrimaryItem) o;

        if (id != that.id) return false;
        return Double.compare(that.progress, progress) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(progress);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
