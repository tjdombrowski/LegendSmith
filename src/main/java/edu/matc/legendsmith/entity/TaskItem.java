package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "TaskItem")
@Table(name = "TaskItem")
public class TaskItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "taskId",
        foreignKey = @ForeignKey(name = "TaskItem_taskId_fk")
    )
    private Task task;

    @ManyToOne
    @JoinColumn(name = "gw2ItemId",
        foreignKey = @ForeignKey(name = "TaskItem_gw2ItemId_fk")
    )
    private Item item;

    public TaskItem() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", task=" + task +
                ", item=" + item +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskItem taskItem = (TaskItem) o;

        if (id != taskItem.id) return false;
        return quantity == taskItem.quantity;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + quantity;
        return result;
    }
}
