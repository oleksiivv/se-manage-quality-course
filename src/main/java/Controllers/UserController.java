package Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private Map<String, String> userCredentials;

    public UserController() {

        this.userCredentials = new HashMap<>();
    }

    public void login(String username, String password) {
        try {
            if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                logger.info("User logged in: " + username);
            } else {
                logger.warn("Login failed: Invalid credentials");
            }
        } catch (Exception e) {
            logger.error("Error during login: " + e.getMessage());

            throw e;
        }
    }

    public void logout(String username) {
        try {
            if (!userCredentials.containsKey(username)) {
                throw new IllegalArgumentException("User not found");
            }

            logger.info("User logged out: " + username);
        } catch (Exception e) {
            logger.error("Error during logout: " + e.getMessage());

            throw e;
        }
    }

    public void register(String username, String password, String email) {
        try {
            if (!userCredentials.containsKey(username)) {
                userCredentials.put(username, password);
                logger.info("User registered: " + username);
            } else {
                logger.warn("Registration failed: Username already exists");
                throw new IllegalArgumentException("Registration failed: Username already exists");
            }
        } catch (Exception e) {
            logger.error("Error during registration: " + e.getMessage());

            throw e;
        }
    }

    public void updatePassword(String username, String newPassword) {
        try {
            if (userCredentials.containsKey(username)) {
                userCredentials.put(username, newPassword);
                logger.info("Password updated for user: " + username);
            } else {
                logger.warn("Password update failed: User not found");
                throw new IllegalArgumentException("Password update failed: User not found");
            }
        } catch (Exception e) {
            logger.error("Error updating password: " + e.getMessage());

            throw e;
        }
    }
}
