package edu.matc.legendsmith.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Task.
 */
@Entity(name = "Task")
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private String name;

    private String description;

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "legendaryPrimaryItemId",
        foreignKey = @ForeignKey(name = "Task_legendaryPrimaryItemId_fk"))
    private LegendaryPrimaryItem legendaryPrimaryItem;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserLegendaryPrimaryItemTask> userTasks = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TaskItem> taskItems = new ArrayList<>();

    /**
     * Instantiates a new Task.
     */
    public Task() {}


    /**
     * Instantiates a new Task.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param quantity    the quantity
     */
    public Task(int id, String name, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Add user primary item.
     *
     * @param userTask the user task
     */
    public void addUserPrimaryItem(UserLegendaryPrimaryItemTask userTask) {
        userTasks.add(userTask);
        userTask.setTask(this);
    }

    /**
     * Remove user primary item.
     *
     * @param userTask the user task
     */
    public void removeUserPrimaryItem(UserLegendaryPrimaryItemTask userTask) {
        userTasks.remove(userTask);
        userTask.setTask(null);
    }

    public List<TaskItem> getTaskItems() {
        return taskItems;
    }

    public void setTaskItems(List<TaskItem> taskItems) {
        this.taskItems = taskItems;
    }

    public void addTaskItem(TaskItem taskItem) {
        taskItems.add(taskItem);
        taskItem.setTask(this);
    }

    public void removeTaskItem(TaskItem taskItem) {
        taskItems.remove(taskItem);
        taskItem.setTask(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (quantity != task.quantity) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        return description != null ? description.equals(task.description) : task.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}
