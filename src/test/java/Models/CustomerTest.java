package Models;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

public class CustomerTest {
    private Customer customer;

    @BeforeClass
    public void setUp() {
        customer = new Customer();
    }

    @AfterClass
    public void tearDown() {
        customer = null;
    }

    @Test
    public void testCustomerCreation() {
        assertNotNull(customer);
    }

    @Test(dependsOnMethods = "testCustomerCreation")
    public void testCustomerId() {
        customer.setId(1);
        assertEquals(customer.getId(), 1);
    }

    @Test(dependsOnMethods = "testCustomerCreation")
    public void testCustomerName() {
        customer.setName("John Doe");
        assertEquals(customer.getName(), "John Doe");
    }

    @Test(dependsOnMethods = "testCustomerCreation")
    public void testCustomerEmail() {
        customer.setEmail("john@example.com");
        assertEquals(customer.getEmail(), "john@example.com");
    }
}
