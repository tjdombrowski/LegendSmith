package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.User;
import edu.matc.legendsmith.entity.UserRole;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserRoleTest {
    GenericDao dao;

    @BeforeEach
    public void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(UserRole.class);
    }

    /**
     * Tests whether inserting an UserRole is a success.
     */
    @Test
    public void insertUserRoleSuccess() {
        UserRole userRole = new UserRole();
        userRole.setRole("admin");
        userRole.setUsername("lex");

        int id = dao.insert(userRole);

        assertEquals(5, dao.getAll().size());
        assertEquals(userRole, (UserRole)dao.getById(id));
    }
}
