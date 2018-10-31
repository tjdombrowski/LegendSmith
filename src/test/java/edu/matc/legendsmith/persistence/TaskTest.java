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

    @Test
    void getAllTasksSuccess() {
        List<Task> allTasks = taskDao.getAll();

        assertEquals(4, allTasks.size());
    }

    /**
            * Tests whether getting a legendary by its id is successful.
            */
    @Test
    void getTaskByIdSuccess() {
        Task task = new Task(3, "Bloodstone Shard", "Purchased from Miyani or any Mystic Forge Attendant for 200 Spirit Shards.", 1);

        Task retrievedTask = (Task)taskDao.getById(7);

        assertEquals(task, retrievedTask);
    }

    /**
     * Tests whether inserting a legendary is successful.
     */
    @Test
    void insertTaskSuccess() {
        Legendary newLegendary = new Legendary("Xiuquatl", "Scepter");
        int id = taskDao.insert(newLegendary);

        Legendary returnedLegendary = (Legendary)taskDao.getById(id);

        assertEquals(newLegendary, returnedLegendary);
    }

    /**
     * Tests whether updating a legendary is successful.
     */
    @Test
    void updateTaskSuccess() {
        Legendary newLegendary = new Legendary("Claw of the Khan-Ur", "Dagger");
        newLegendary.setId(4);

        taskDao.saveOrUpdate(newLegendary);

        Legendary returnedLegendary = (Legendary)taskDao.getById(4);

        assertEquals(newLegendary, returnedLegendary);
    }

    /**
     * Tests whether deleting a legendary is successful.
     */
    @Test
    void deleteTaskSuccess() {
        Legendary returnedLegendary = (Legendary)taskDao.getById(5);
        assertNotNull(returnedLegendary);

        taskDao.delete(returnedLegendary);

        assertNull(taskDao.getById(5));
    }


}