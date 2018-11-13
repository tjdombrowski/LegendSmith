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
    private Date date;

    @ManyToOne
    @JoinColumn(name = "userLegendaryPrimaryItemId",
        foreignKey = @ForeignKey(name = "UserLegendPrimItem_fk1"))
    private UserLegendaryPrimaryItem userPrimaryItem;

    @ManyToOne
    @JoinColumn(name = "taskId",
        foreignKey = @ForeignKey(name = "UserLegendPrimItem_fk2"))
    private Task task;


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLegendaryPrimaryItemTask that = (UserLegendaryPrimaryItemTask) o;

        if (id != that.id) return false;
        if (completion != that.completion) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + completion;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
