package Controllers;

import Models.Customer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CustomerControllerTest {
    private CustomerController customerController;

    @BeforeClass
    public void setUp() {
        customerController = new CustomerController();
    }

    @AfterClass
    public void tearDown() {
        customerController = null;
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setId(1);
        customerController.createCustomer(customer);

        List<Customer> customers = customerController.getAllCustomers();
        assertEquals(customers.size(), 1);
        assertEquals(customers.get(0).getName(), "John Doe");
        assertEquals(customers.get(0).getEmail(), "john@example.com");
    }

    @Test(dependsOnMethods = "testCreateCustomer")
    public void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setId(12); // Assuming customer with ID 1 already exists
        customer.setName("Jane Doe");
        customer.setEmail("jane@example.com");
        customerController.createCustomer(customer);
        customer.setName("Jane Doe1");
        customer.setEmail("jane@example11.com");
        customerController.updateCustomer(customer);

        Customer updatedCustomer = customerController.getCustomerById(12);
        assertEquals(updatedCustomer.getName(), "Jane Doe1");
        assertEquals(updatedCustomer.getEmail(), "jane@example11.com");
    }

    @Test(dependsOnMethods = "testCreateCustomer")
    public void testDeleteCustomer() {
        customerController.deleteCustomer(1); // Assuming customer with ID 1 already exists
        assertNull(customerController.getCustomerById(1));
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = customerController.getAllCustomers();
        assertNotNull(customers);
        assertEquals(customers.size(), 1); // Assuming no customers exist initially
    }

    @Test(dependsOnMethods = "testCreateCustomer")
    public void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setId(11);
        customerController.createCustomer(customer);

        Customer retrievedCustomer = customerController.getCustomerById(11); // Assuming customer with ID 1 exists
        assertNotNull(retrievedCustomer);
        assertEquals(retrievedCustomer.getName(), "John Doe");
        assertEquals(retrievedCustomer.getEmail(), "john@example.com");
    }

    @Test(dependsOnMethods = "testCreateCustomer")
    public void testUpdateCustomerCustomerNotFoundException() {
        // Arrange
        CustomerController customerController = new CustomerController();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            customerController.updateCustomer(new Customer());
        });
    }

    @Test(dependsOnMethods = "testCreateCustomer")
    public void testDeleteCustomerCustomerNotFoundException() {
        // Arrange
        CustomerController customerController = new CustomerController();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            customerController.deleteCustomer(1000);
        });
    }

    @Test(dependsOnMethods = "testCreateCustomer")
    public void testGetCustomerByIdCustomerNotFoundReturnsNull() {
        // Arrange
        CustomerController customerController = new CustomerController();

        // Act
        Customer customer = customerController.getCustomerById(1000);

        // Assert
        assertNull(customer);
    }

    @Test(dependsOnMethods = "testCreateCustomer")
    public void testGetAllCustomersEmptyListReturnsEmptyList() {
        // Arrange
        CustomerController customerController = new CustomerController();

        // Act
        List<Customer> customers = customerController.getAllCustomers();

        // Assert
        assertTrue(customers.isEmpty());
    }
}
