package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The type User dao test.
 */
public class UserDaoTest {

    GenericDao dao;

    /**
     * Sets up the tests by resetting the database and instantiating the necessary dao.
     */
    @BeforeEach
    public void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(User.class);
    }

    /**
     * Tests whether getting all users is a success.
     */
    @Test
    public void getAllUsersSuccess() {
        List<User> users = dao.getAll();

        assertEquals(4, users.size());
    }

    /**
     * Tests whether getting an user by the id is a success.
     */
    @Test
    public void getUserByIdSuccess() {
        User retrievedUser = (User)dao.getById(1);
        User user = new User(1, "silkie", "rabbitscream");

        assertEquals(user, retrievedUser);
    }

    /**
     * Tests whether adding an user is a success.
     */
    @Test
    public void addUserSuccess() {
        User newUser = new User("cyclone", "securepassword");

        int insertedId  = dao.insert(newUser);

        assertEquals(5, dao.getAll().size());
        assertEquals(newUser, (User)dao.getById(insertedId));
    }

    /**
     * Tests whether updating an user is successful.
     */
    @Test
    public void updateUserSuccess() {
        User newUser = new User(4, "argentium", "serendipity");

        dao.saveOrUpdate(newUser);

        assertEquals(newUser, (User)dao.getById(4));

    }

    /**
     * Tests whether deleting an user is a success.
     */
    @Test
    public void deleteUserSuccess() {
        User user = (User)dao.getById(3);

        dao.delete(user);

        GenericDao roleDao = new GenericDao(UserRole.class);

        assertNull(roleDao.getById(3));

        assertEquals(null, dao.getById(3));
        assertEquals(3, dao.getAll().size());
    }

    /**
     * Tests whether adding a user and setting a role on the user is a success.
     * TODO inserting does not add the role's fk
     */
    @Test
    public void addUserWithRoleSuccess() {
        User user = new User("pigeon", "beep");
        UserRole role = new UserRole("user");

        int id = dao.insert(user);
        assertNotEquals(0, id);

        User insertedUser = (User)dao.getById(id);
        insertedUser.setRole(role);

        assertEquals(user, insertedUser);
        assertEquals("user", insertedUser.getRole().getRole());

    }
}
