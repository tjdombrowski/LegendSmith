package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Data validator.
 */
public class DataValidator {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private String errorMsg;

    /**
     * Instantiates a new Data validator.
     */
    public DataValidator() {
        errorMsg = ""; //This stays blank if there is nothing wrong

    }

    /**
     * Checks whether the string is empty and returns an error message if it is.
     *
     * @param text the text
     * @return errorMsg the error message
     */
    public String checkWhetherStringIsEmpty(String text) {
        if (text.isEmpty()) {
            errorMsg = "Please enter your information in each field.";
        }

        return errorMsg;
    }

    /**
     * Checks whether the String is over a size of 20 (The maximum size for an username and password).
     * Returns an error message if it is.
     *
     * @param text the text
     * @return errorMsg the error message
     */
    public String checkWhetherStringSizeOver20(String text) {
        if (text.length() > 20) {
            errorMsg = "You cannot exceed 20 characters in length.";
        }

        return errorMsg;
    }

    /**
     * Checks if there is any kind of white space in the text. If there is, an error message is returned.
     *
     * @param text the text
     * @return errorMsg the error message
     */
    public String checkForNoWhiteSpace(String text) {
        if (text.matches("\\s")) {
            errorMsg = "You cannot enter any white spaces.";
        }

        return errorMsg;
    }

    /**
     * Checks if the given username is unique and returns an error message if not.
     *
     * @param username the username
     * @return errorMsg the error message
     */
    public String checkIfUsernameIsUnique(String username) {
        GenericDao userDao = new GenericDao(User.class);
        List<User> users = userDao.getAll();

        for (User user : users) {
            if (username.equals(user.getUsername())) {
                errorMsg = "That username is taken.";

                break;
            }
        }

        return errorMsg;
    }

    /**
     * Checks whether the two passwords match each other and returns an error message if not.
     *
     * @param pass1 password 1
     * @param pass2 password 2
     * @return errorMsg the error message
     */
    public String checkWhetherPasswordsMatch(String pass1, String pass2) {
        if (pass1.equals(pass2) == false) {
            errorMsg = "Please make sure your passwords match.";
        }

        return errorMsg;
    }
}
