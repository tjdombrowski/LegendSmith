package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TaskTest {
    GenericDao taskDao;
    GenericDao legendaryPrimaryItemDao;


    /**
     * Sets up the tests by resetting the database and instantiating the necessary dao.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");



        taskDao = new GenericDao(Task.class);
        legendaryPrimaryItemDao =  new GenericDao(LegendaryPrimaryItem.class);

    }

    /**
     * Tests whether retrieving all tasks is successful.
     */
    @Test
    void getAllTasksSuccess() {
        List<Task> allTasks = taskDao.getAll();

        assertEquals(4, allTasks.size());
    }

    /**
       Tests whether getting a task by its id is successful.
     */
    @Test
    void getTaskByIdSuccess() {
        Task task = new Task(3, "Bloodstone Shard", "Purchased from Miyani or any Mystic Forge Attendant for 200 Spirit Shards.", 1);

        Task retrievedTask = (Task)taskDao.getById(3);

        assertEquals(task, retrievedTask);
    }

    /**
     * Tests whether inserting a task is successful.
     */
    @Test
    void insertTaskSuccess() {
        Task task = new Task(5, "name", "description", 2);
        int id = taskDao.insert(task);

        Task returnedTask = (Task)taskDao.getById(id);

        assertEquals(task, returnedTask);
    }

    /**
     * Tests whether updating a task is successful.
     */
    @Test
    void updateTaskSuccess() {
        Task newTask = new Task(4, "updated name", "updated description", 100);

        taskDao.saveOrUpdate(newTask);

        Task retrievedTask = (Task)taskDao.getById(4);

        assertEquals(newTask, retrievedTask);
    }

    /**
     * Tests whether deleting a task is successful.
     */
    @Test
    void deleteTaskSuccess() {
        Task returnedTask = (Task)taskDao.getById(1);
        assertNotNull(returnedTask);

        taskDao.delete(returnedTask);

        assertNull(taskDao.getById(1));
    }

    /**
     * Tests whether retrieving the task's items is a success.
     */
    @Test
    void retrieveTaskItemsSuccess() {
        Task task = (Task)taskDao.getById(4);

        List<TaskItem> taskItems = task.getTaskItems();

        assertEquals(1, taskItems.size());

        Item item = new Item();
        item.setGw2ItemId(68063);
        item.setName("Amalgamated Gemstone");

        assertEquals(item.getGw2ItemId(), taskItems.get(0).getItem().getGw2ItemId());
        assertEquals(item.getName(), taskItems.get(0).getItem().getName());

    }

    /**
     * Tests whether calculating and storing the the Task's cost is successful.
     */
    @Test
    void getTaskCostSuccess() {
        Task task = (Task)taskDao.getById(4);

        task.generateTaskCost();

        ItemPrice taskCost = task.getTaskCost();

        assertNotNull(taskCost);
        assertTrue(taskCost.getGoldPrice() >= 200 && taskCost.getGoldPrice() <= 600);
        assertTrue(taskCost.getSilverPrice() >= 0 && taskCost.getSilverPrice() <= 99);
        assertTrue(taskCost.getCopperPrice() >= 0 && taskCost.getCopperPrice() <= 99);
    }
}