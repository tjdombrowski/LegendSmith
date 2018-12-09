package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User legendary primary item.
 */
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

    /**
     * Instantiates a new User legendary primary item.
     */
    public UserLegendaryPrimaryItem() {}

    /**
     * Instantiates a new User legendary primary item.
     *
     * @param user                 the user
     * @param legendaryPrimaryItem the legendary primary item
     * @param progress             the progress
     */
    public UserLegendaryPrimaryItem(User user, LegendaryPrimaryItem legendaryPrimaryItem, int progress) {
        this.user = user;
        this.legendaryPrimaryItem = legendaryPrimaryItem;
        this.progress = progress;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets progress.
     *
     * @return the progress
     */
    public double getProgress() {
        return progress;
    }

    /**
     * Sets progress.
     *
     * @param progress the progress
     */
    public void setProgress(double progress) {
        this.progress = progress;
    }

    /**
     * Gets legendary primary item.
     *
     * @return the legendary primary item
     */
    public LegendaryPrimaryItem getLegendaryPrimaryItem() {
        return legendaryPrimaryItem;
    }

    /**
     * Sets legendary primary item.
     *
     * @param legendaryPrimaryItem the legendary primary item
     */
    public void setLegendaryPrimaryItem(LegendaryPrimaryItem legendaryPrimaryItem) {
        this.legendaryPrimaryItem = legendaryPrimaryItem;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets user tasks.
     *
     * @return the user tasks
     */
    public List<UserLegendaryPrimaryItemTask> getUserTasks() {
        return userTasks;
    }

    /**
     * Sets user tasks.
     *
     * @param userTasks the user tasks
     */
    public void setUserTasks(List<UserLegendaryPrimaryItemTask> userTasks) {
        this.userTasks = userTasks;
    }

    /**
     * Add user task.
     *
     * @param userTask the user task
     */
    public void addUserTask(UserLegendaryPrimaryItemTask userTask) {
        userTasks.add(userTask);
        userTask.setUserPrimaryItem(this);
    }

    /**
     * Remove user task.
     *
     * @param userTask the user task
     */
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
