package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserCreatorTest {
    UserCreator userCreator;

    @BeforeEach
    public void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        userCreator = new UserCreator();
    }

    /**
     * Tests whether adding an user with an username and password is successful.
     */
    @Test
    public void addUserSuccess() {
        String errorMsg = userCreator.addUser("username", "password");

        assertEquals("", errorMsg);
    }
}
