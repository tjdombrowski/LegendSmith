package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Data validator test.
 */
public class DataValidatorTest {
    private DataValidator dataValidator;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        dataValidator = new DataValidator();

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Test user data validation no errors.
     */
    @Test
    void testUserDataValidationNoErrors() {
        Map<String, String> userDataMap = new HashMap<>();

        userDataMap.put("username", "kokabiel");
        userDataMap.put("password1", "hotcocoa");
        userDataMap.put("password2", "hotcocoa");

        String msg = dataValidator.validateAll(userDataMap);
        assertEquals("", msg);
    }

    /**
     * Test user data validation with unique username error.
     */
    @Test
    void testUserDataValidationWithUniqueUsernameError() {
        Map<String, String> userDataMap = new HashMap<>();

        userDataMap.put("username", "lex");
        userDataMap.put("password1", "leopard");
        userDataMap.put("password2", "leopard");

        String msg = dataValidator.validateAll(userDataMap);

        assertEquals("That username is taken.", msg);
    }

    /**
     * Test user data validation with password match error.
     */
    @Test
    void testUserDataValidationWithPasswordMatchError() {
        Map<String, String> userDataMap = new HashMap<>();

        userDataMap.put("username", "kokabiel");
        userDataMap.put("password1", "cocoa");
        userDataMap.put("password2", "hotcocoa");

        String msg = dataValidator.validateAll(userDataMap);

        assertEquals("Please make sure your passwords match.", msg);
    }

    /**
     * Test user data validation with general error.
     */
    @Test
    void testUserDataValidationWithGeneralError() {
        Map<String, String> userDataMap = new HashMap<>();

        userDataMap.put("username", "");
        userDataMap.put("password1", "violet");
        userDataMap.put("password2", "violet");

        String msg = dataValidator.validateAll(userDataMap);

        assertTrue(!msg.isEmpty());
    }

    /**
     * Test if username unique.
     */
    @Test
    public void testIfUsernameUnique() {
        String msg = dataValidator.checkIfUsernameIsUnique("sathariel");
        assertEquals("", msg);

        msg = dataValidator.checkIfUsernameIsUnique("lex");
        assertEquals("That username is taken.", msg);
    }

    /**
     * Test if white space.
     */
    @Test
    public void testIfWhiteSpace() {
        String msg = dataValidator.checkForNoWhiteSpace(" ");
        assertEquals("You cannot enter any white spaces.", msg);

        msg = dataValidator.checkForNoWhiteSpace("d oge");
        assertEquals("You cannot enter any white spaces.", msg);

        msg = dataValidator.checkForNoWhiteSpace("birb  ");
        assertEquals("You cannot enter any white spaces.", msg);

        msg = dataValidator.checkForNoWhiteSpace("\nsnek");
        assertEquals("You cannot enter any white spaces.", msg);
    }

    /**
     * Test if no white space.
     */
    @Test
    public void testIfNoWhiteSpace() {
        String msg = dataValidator.checkForNoWhiteSpace("onagadori");
        assertEquals("", msg);
    }

    /**
     * Test if over and under 20 characters.
     */
    @Test
    public void testIfOverAndUnder20Characters() {
        String msg = dataValidator.checkWhetherStringSizeOver20("12345678901234567890");
        assertEquals("", msg);

        msg = dataValidator.checkWhetherStringSizeOver20("123456789012345678901");
        assertEquals("You cannot exceed 20 characters in length.", msg);
    }

    /**
     * Test if empty.
     */
    @Test
    public void testIfEmpty() {
        String msg = dataValidator.checkWhetherStringIsEmpty("");
        assertEquals("Please enter your information in each field.", msg);
    }

    /**
     * Test if not empty.
     */
    @Test
    public void testIfNotEmpty() {
        String msg = dataValidator.checkWhetherStringIsEmpty("hi");
        assertEquals("", msg);
    }

    /**
     * Test if passwords match.
     */
    @Test
    public void testIfPasswordsMatch() {
        String msg = dataValidator.checkWhetherPasswordsMatch("hi", "hi");
        assertEquals("", msg);

    }

    /**
     * Test if passwords dont match.
     */
    @Test
    public void testIfPasswordsDontMatch() {
        String msg = dataValidator.checkWhetherPasswordsMatch("hi", "hii");
        assertEquals("Please make sure your passwords match.", msg);
    }


}
