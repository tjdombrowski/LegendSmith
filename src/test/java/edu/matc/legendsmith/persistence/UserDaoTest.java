package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class UserDaoTest {
    GenericDao dao;

    @BeforeEach
    public void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(User.class);
    }

    @Test
    public void getAllUsersSuccess() {
        List<User> users = dao.getAll();

        assertEquals(4, users.size());
    }

    @Test
    public void getUserByIdSuccess() {

    }

    @Test
    public void addUserSuccess() {

    }

    @Test
    public void updateUserSuccess() {


    }

    @Test
    public void deleteUserSuccess() {

    }
}
