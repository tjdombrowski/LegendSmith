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

    private List<TaskItem> taskItems = new ArrayList<>();

    public Item() {}

    public int getGw2ItemId() {
        return gw2ItemId;
    }

    public void setGw2ItemId(int gw2ItemId) {
        this.gw2ItemId = gw2ItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskItem> getTaskItems() {
        return taskItems;
    }

    public void setTaskItems(List<TaskItem> taskItems) {
        this.taskItems = taskItems;
    }

    @Override
    public String toString() {
        return "Item{" +
                "gw2ItemId=" + gw2ItemId +
                ", name='" + name + '\'' +
                ", taskItems=" + taskItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (gw2ItemId != item.gw2ItemId) return false;
        return name != null ? name.equals(item.name) : item.name == null;
    }

    @Override
    public int hashCode() {
        int result = gw2ItemId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
