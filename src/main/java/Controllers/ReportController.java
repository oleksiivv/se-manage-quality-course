package Controllers;

import Models.Order;
import Models.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class ReportController {
    private static final Logger logger = LogManager.getLogger(ReportController.class);
    private ProductController productController;
    private OrderController orderController;

    public ReportController(ProductController productController, OrderController orderController) {
        this.productController = productController;
        this.orderController = orderController;
    }

    public double generateSalesReport() {
        try {

            List<Order> orders = orderController.getAllOrders();


            double totalSales = 0;
            for (Order order : orders) {
                for (Product product : order.getProducts()) {
                    totalSales += product.getPrice() * product.getQuantity();
                }
            }


            logger.info("Total sales: $" + totalSales);

            return totalSales;
        } catch (Exception e) {
            logger.error("Error generating sales report: " + e.getMessage());
            throw e;
        }
    }

    public int generateInventoryReport() {
        try {

            List<Product> products = productController.getAllProducts();

            int quantity = 0;

            logger.info("Inventory Report:");
            for (Product product : products) {
                quantity+=product.getQuantity();

                logger.info(product.getName() + " - Quantity: " + product.getQuantity());
            }

            return quantity;
        } catch (Exception e) {
            logger.error("Error generating inventory report: " + e.getMessage());
            throw e;
        }
    }
}

