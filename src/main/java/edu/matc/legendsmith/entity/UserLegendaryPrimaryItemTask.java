package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "UserLegendaryPrimaryItemTask")
@Table(name = "UserLegendaryPrimaryItemTask")
public class UserLegendaryPrimaryItemTask {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private int completion;
    private Date dateCompleted;

    @ManyToOne
    @JoinColumn(name = "userLegendaryPrimaryItemId",
        foreignKey = @ForeignKey(name = "UserLegendPrimItem_fk1"))
    private UserLegendaryPrimaryItem userPrimaryItem;

    @ManyToOne
    @JoinColumn(name = "taskId",
        foreignKey = @ForeignKey(name = "UserLegendPrimItem_fk2"))
    private Task task;

    public UserLegendaryPrimaryItemTask() {}

    public UserLegendaryPrimaryItemTask(UserLegendaryPrimaryItem userPrimaryItem, Task task) {
        this.userPrimaryItem = userPrimaryItem;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompletion() {
        return completion;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public UserLegendaryPrimaryItem getUserPrimaryItem() {
        return userPrimaryItem;
    }

    public void setUserPrimaryItem(UserLegendaryPrimaryItem userPrimaryItem) {
        this.userPrimaryItem = userPrimaryItem;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLegendaryPrimaryItemTask that = (UserLegendaryPrimaryItemTask) o;

        if (id != that.id) return false;
        if (completion != that.completion) return false;
        return dateCompleted != null ? dateCompleted.equals(that.dateCompleted) : that.dateCompleted == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + completion;
        result = 31 * result + (dateCompleted != null ? dateCompleted.hashCode() : 0);
        return result;
    }
}
