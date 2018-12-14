package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.User;
import edu.matc.legendsmith.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type User creator.
 */
public class UserCreator {

    private final Logger logger = LogManager.getLogger(this.getClass());


    /**
     * Adds an user to the database and returns a blank message if successful.
     *
     * @param username the username
     * @param password the password
     * @return errorMsg the error message
     */
    public String addUser(String username, String password) {
        String errorMsg = "";

        User user = insertUser(username, password);
        int userRoleId = insertUserRole(user);

        //If the insert performs correctly, userId should not be 0
        if (user.getId() == 0 || userRoleId == 0) {
            errorMsg = "Something went wrong with the creating your account. Please try again later.";
        }

        logger.debug("UserCreator, addUser() userId: " + user.getId());
        logger.debug("UserCreator, addUser() userRoleId: {}", userRoleId);
        logger.debug("UserCreator, addUser() errorMsg: {}", errorMsg);

        return errorMsg;
    }


    /**
     * Inserts the user to the database and returns the inserted user (not retrieved manually).
     *
     * @param username the username
     * @param password the password
     * @return user the User
     */
    private User insertUser(String username, String password) {
        GenericDao userDao = new GenericDao(User.class);
        User user = new User(username, password);
        int userId = userDao.insert(user);

        user.setId(userId);

        return user;
    }

    /**
     * Inserts the user's user role in the database.
     *
     * @param user the User
     * @return userRoleId the id of the UserRole
     */
    private int insertUserRole(User user) {
        String role = "user";

        GenericDao userRoleDao = new GenericDao(UserRole.class);
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUsername(user.getUsername());
        userRole.setUser(user);

        int userRoleId = userRoleDao.insert(userRole);

        return userRoleId;
    }
}
