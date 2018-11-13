package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.hibernate.annotations.GenericGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The type User dao test.
 *
 */
public class UserDaoTest {

    GenericDao dao;
    GenericDao legendaryDao;

    /**
     * Sets up the tests by resetting the database and instantiating the necessary dao.
     */
    @BeforeEach
    public void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(User.class);
        legendaryDao = new GenericDao(Legendary.class);
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
     * Tests whether retrieving the legendary of an user is successful.
     */
    @Test
    public void getUserLegendarySuccess() {
        User user = (User)dao.getById(2);

        List<UserLegendary> userLegendaries = user.getUserLegendaries();

       assertEquals(2, userLegendaries.size());

       Legendary userLegendary = userLegendaries.get(0).getLegendary();
       Legendary legendary = (Legendary) legendaryDao.getById(2);

       assertEquals(legendary, userLegendary);

    }

    @Test
    public void getUserPrimaryItemsSuccess() {
        User user = (User)dao.getById(2);

        List<UserLegendaryPrimaryItem> userLegendaryPrimaryItems = user.getUserPrimaryItems();

        assertEquals(4, userLegendaryPrimaryItems.size());

        PrimaryItem userPrimaryItem = userLegendaryPrimaryItems.get(0).getLegendaryPrimaryItem().getPrimaryItem();

        GenericDao primaryItemDao = new GenericDao(PrimaryItem.class);

        PrimaryItem primaryItem = (PrimaryItem) primaryItemDao.getById(1);

        assertEquals(primaryItem, userPrimaryItem);
    }

}
