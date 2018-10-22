package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserRoleDaoTest {
    GenericDao dao;

    /**
     * Sets up the tests by resetting the database and instantiating the necessary dao.
     */
    @BeforeEach
    public void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(UserRole.class);
    }

    /**
     *
     */
    @Test
    public void getAllUserRolesSuccess() {
        List<UserRole> userRoles = dao.getAll();

        assertEquals(4, userRoles.size());
    }

    /**
     * Tests whether getting an user's role by the id is a success.
     */
    @Test
    public void getUserRoleByIdSuccess() {
        UserRole retrievedRole = (UserRole)dao.getById(1);
        UserRole userRole = new UserRole(1, "admin");

        assertEquals(userRole, retrievedRole);
    }


    /**
     * Tests whether adding an user's role is a success.
     */
    @Test
    public void addUserRoleSuccess() {
        UserRole newUserRole = new UserRole("usersss");
        GenericDao userDao = new GenericDao(User.class);
        User newUser = new User("cyclone", "securepassword");

        int userInsertedId  = dao.insert(newUser);
        assertNotEquals(0, userInsertedId);

        newUserRole.setUser((User)userDao.getById(userInsertedId));

        int insertedId  = dao.insert(newUserRole);

        assertEquals(5, dao.getAll().size());
        assertEquals(newUserRole, dao.getById(insertedId));
    }

    @Test
    public void updateUserRoleSuccess() {
        UserRole role = (UserRole)dao.getById(1);
        assertNotNull(role);

        role.setRole("user");

        dao.saveOrUpdate(role);

        UserRole updatedRole = (UserRole)dao.getById(1);

        assertEquals(role, updatedRole);
    }

    @Test
    public void deleteUserRoleSuccess() {
        UserRole role = (UserRole)dao.getById(4);

        assertNotNull(role);

        dao.delete(role);

        assertNull(dao.getById(4));
    }


}
