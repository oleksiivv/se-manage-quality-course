package Controllers;

import Controllers.OrderController;
import Models.Customer;
import Models.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class OrderControllerTest {
    private OrderController orderController;

    @BeforeMethod
    public void setUp() {
        orderController = new OrderController();
    }

    @Test
    public void testCreateOrder() {

        Order order = new Order();
        order.setId(1);
        orderController.createOrder(order);


        List<Order> orders = orderController.getAllOrders();
        assertNotNull(orders);
        assertEquals(orders.size(), 1);
        assertEquals(orders.get(0).getId(), 1);
    }

    @Test
    public void testUpdateOrder() {

        Order order = new Order();
        order.setId(1);
        orderController.createOrder(order);


        Order updatedOrder = new Order();
        updatedOrder.setId(1);
        Customer customer = new Customer();
        customer.setName("John Doe");
        updatedOrder.setCustomer(customer);
        orderController.updateOrder(updatedOrder);


        List<Order> orders = orderController.getAllOrders();
        assertNotNull(orders);
        assertEquals(orders.size(), 1);
        assertEquals(orders.get(0).getCustomer().getName(), "John Doe");
    }

    @Test
    public void testDeleteOrder() {

        Order order = new Order();
        order.setId(1);
        orderController.createOrder(order);


        orderController.deleteOrder(1);


        List<Order> orders = orderController.getAllOrders();
        assertNotNull(orders);
        assertEquals(orders.size(), 0);
    }

    @Test
    public void testGetOrderById() {
        // Create a sample order
        Order order = new Order();
        order.setId(1);
        orderController.createOrder(order);

        // Retrieve the order by ID
        Order retrievedOrder = orderController.getOrderById(1);

        // Verify if the correct order is retrieved
        assertNotNull(retrievedOrder);
        assertEquals(retrievedOrder.getId(), 1);
    }
    @Test(expectedExceptions = NullPointerException.class)
    public void testCreateOrderWithNullOrder() {
        orderController.createOrder(null);
    }

    @Test
    public void testGetNonExistingOrderById() {
        Order order = orderController.getOrderById(1);
        assertNull(order);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateOrderOrderNotFoundExceptionThrown() {
        // Arrange
        OrderController orderController = new OrderController();
        Order orderToUpdate = new Order();
        orderToUpdate.setId(98);

        orderController.updateOrder(orderToUpdate);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteOrderOrderNotFoundExceptionThrown() {
        OrderController orderController = new OrderController();

        orderController.deleteOrder(1);
    }
}
