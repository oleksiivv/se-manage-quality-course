package Controllers;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserControllerTest {
    private UserController userController;

    @BeforeMethod
    public void setUp() {
        userController = new UserController();
    }

    @Test
    public void testLogin() {
        userController.register("user1", "password1", "user1@example.com");
        userController.login("user1", "password1");

    }

    @Test
    public void testLogout() {
        userController.register("user2", "password2", "user2@example.com");
        userController.login("user2", "password2");
        userController.logout("user2");

    }

    @Test
    public void testRegister() {
        userController.register("user3", "password3", "user3@example.com");

    }

    @Test
    public void testUpdatePassword() {
        userController.register("user4", "password4", "user4@example.com");
        userController.updatePassword("user4", "newPassword");

    }

    @Test(
            threadPoolSize = 3, invocationCount = 6, timeOut = 1000,
            dependsOnMethods = "testRegister", expectedExceptions = IllegalArgumentException.class
    )
    public void testParallelExecutionRegister() {
        userController.register("user", "password", "user@example.com");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testRegisterUsernameExistsException() {

        UserController userController = new UserController();


        userController.register("user1", "password1", "user1@example.com");

        userController.register("user1", "password2", "user2@example.com");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testUpdatePasswordUserNotFoundException() {

        UserController userController = new UserController();

        userController.updatePassword("non_existing_user", "new_password");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testLogoutException() {

        UserController userController = new UserController();

        userController.logout("non_existing_user");
    }
}

