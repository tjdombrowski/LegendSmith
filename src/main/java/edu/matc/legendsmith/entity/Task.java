package edu.matc.legendsmith.entity;

import edu.matc.legendsmith.persistence.Gw2ApiUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @Transient
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private String name;

    private String description;

    private int quantity;

    //The task cost represents the total cost of the task in gold, silver, and copper
    @Transient
    ItemPrice taskCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "legendaryPrimaryItemId",
        foreignKey = @ForeignKey(name = "Task_legendaryPrimaryItemId_fk"))
    private LegendaryPrimaryItem legendaryPrimaryItem;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserLegendaryPrimaryItemTask> userTasks = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TaskItem> taskItems = new ArrayList<>();

    /**
     * Instantiates a new Task and sets the task cost.
     */
    public Task() {
        generateTaskCost();
    }

    /**
     * Instantiates a new Task.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param quantity    the quantity
     */
    public Task(int id, String name, String description, int quantity) {
        this();
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    /**
     * Used to set the total task cost as an ItemPrice object associated with the task. To accomplish this, the method
     * runs through each TaskItem associated with the Task and calculates the total price then multiplies it by the task
     * quantity for the final total. If there are no items, then there is no task cost and taskCost is left null.
     */
    public void generateTaskCost() {
        //Do nothing if there are no items to be priced with this Task
        if (taskItems.size() > 0) {
            int totalTaskCost = 0;
            Gw2ApiUser gw2ApiUser = new Gw2ApiUser();

            for (TaskItem taskItem : taskItems) {
                // Call API and retrieve the cost of the individual item
                int sellOrderPrice = gw2ApiUser.getSellOrderPrice(taskItem.getItem().getGw2ItemId());

                // Multiply the cost by the item quantity
                totalTaskCost += sellOrderPrice * taskItem.getQuantity();
            }
            // Multiply the total by the task quantity
            totalTaskCost = totalTaskCost * quantity;

            // Create the task cost
            taskCost = new ItemPrice();
            taskCost.setDenominationValues(totalTaskCost);
        }

        logger.debug("taskCost result: " + taskCost);

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
     * Gets task cost.
     *
     * @return the task cost
     */
    public ItemPrice getTaskCost() {
        return taskCost;
    }

    /**
     * Sets task cost.
     *
     * @param taskCost the task cost
     */
    public void setTaskCost(ItemPrice taskCost) {
        this.taskCost = taskCost;
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

    /**
     * Gets task items.
     *
     * @return the task items
     */
    public List<TaskItem> getTaskItems() {
        return taskItems;
    }

    /**
     * Sets task items.
     *
     * @param taskItems the task items
     */
    public void setTaskItems(List<TaskItem> taskItems) {
        this.taskItems = taskItems;
    }

    /**
     * Add task item.
     *
     * @param taskItem the task item
     */
    public void addTaskItem(TaskItem taskItem) {
        taskItems.add(taskItem);
        taskItem.setTask(this);
    }

    /**
     * Remove task item.
     *
     * @param taskItem the task item
     */
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
