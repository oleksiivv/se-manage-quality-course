package Models;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class OrderTest {
    private Order order;

    @BeforeClass
    public void setUp() {
        order = new Order();
    }

    @AfterClass
    public void tearDown() {
        order = null;
    }

    @Test
    public void testOrderCreation() {
        assertNotNull(order);
    }

    @Test(dependsOnMethods = "testOrderCreation")
    public void testOrderId() {
        order.setId(1);
        assertEquals(order.getId(), 1);
    }

    @Test(dependsOnMethods = "testOrderCreation")
    public void testOrderProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 999.99));
        products.add(new Product(2, "Phone", 699.99));
        order.setProducts(products);
        assertEquals(order.getProducts(), products);
    }

    @Test(dependsOnMethods = "testOrderCreation")
    public void testOrderDate() {
        Date orderDate = new Date();
        order.setOrderDate(orderDate);
        assertEquals(order.getOrderDate(), orderDate);
    }

    @Test(dependsOnMethods = "testOrderCreation")
    public void testOrderCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        order.setCustomer(customer);
        assertEquals(order.getCustomer(), customer);
    }
}
