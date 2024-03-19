package Controllers;

import Models.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private static final Logger logger = LogManager.getLogger(OrderController.class);
    private List<Order> orderList;

    public OrderController() {
        this.orderList = new ArrayList<>();
    }

    public void createOrder(Order order) {
        try {

            orderList.add(order);
            logger.info("Order created with ID: " + order.getId());
        } catch (Exception e) {
            logger.error("Error creating order: " + e.getMessage());

            throw e;
        }
    }

    public void updateOrder(Order order) {
        try {

            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getId() == order.getId()) {
                    orderList.set(i, order);
                    logger.info("Order updated with ID: " + order.getId());
                    return;
                }
            }
            logger.warn("Order not found with ID: " + order.getId());
            throw new IllegalArgumentException("Order not found with ID: " + order.getId());
        } catch (Exception e) {
            logger.error("Error updating order: " + e.getMessage());

            throw e;
        }
    }

    public void deleteOrder(int orderId) {
        try {

            for (Order order : orderList) {
                if (order.getId() == orderId) {
                    orderList.remove(order);
                    logger.info("Order deleted with ID: " + orderId);
                    return;
                }
            }
            logger.warn("Order not found with ID: " + orderId);
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        } catch (Exception e) {
            logger.error("Error deleting order: " + e.getMessage());

            throw e;
        }
    }

    public List<Order> getAllOrders() {

        logger.info("Retrieved all orders");
        return orderList;
    }

    public Order getOrderById(int orderId) {

        for (Order order : orderList) {
            if (order.getId() == orderId) {
                logger.info("Retrieved order by ID: " + orderId);
                return order;
            }
        }
        logger.warn("Order not found with ID: " + orderId);
        return null;
    }
}
