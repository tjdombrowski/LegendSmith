package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * This method can be used to validate a set of user data. It checks whether they're empty, have any white space,
     * exceed 20 characters, if the username is unique or if the passwords don't match,
     * and returns an error message on the first one to meet those conditions.
     *
     * TODO consider breaking this method up
     *
     * @param userDataMap the string set
     * @return errorMsg the error message
     */
    public String validateAll(Map<String, String> userDataMap) {
        for (Map.Entry entry : userDataMap.entrySet()) {
            //Check if passwords match, but this will be overwritten first
            errorMsg = checkWhetherPasswordsMatch(userDataMap.get("password1"), userDataMap.get("password2"));

            if (entry.getKey().equals("username")) {
                errorMsg = checkIfUsernameIsUnique((String)entry.getValue());
            }

            while (errorMsg.isEmpty()) {
                String text = (String)entry.getValue();

                errorMsg = checkWhetherStringIsEmpty(text);
                errorMsg = checkForNoWhiteSpace(text);
                errorMsg = checkWhetherStringSizeOver20(text);
                logger.info("validateAll errorMsg value in while loop: {}, with key: {}", errorMsg, (String)entry.getKey());

                break;
            }
        }

        return errorMsg;
    }

    /**
     * Checks whether the string is empty or null and returns an error message if it is.
     *
     * @param text the text
     * @return errorMsg the error message
     */
    public String checkWhetherStringIsEmpty(String text) {
        if (text.isEmpty() || text == null) {
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
        String msg = "You cannot enter any white spaces.";

        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            errorMsg = msg;
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
        if (!pass1.equals(pass2)) {
            errorMsg = "Please make sure your passwords match.";
        }

        return errorMsg;
    }
}
