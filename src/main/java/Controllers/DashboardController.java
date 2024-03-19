package Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DashboardController {
    private static final Logger logger = LogManager.getLogger(DashboardController.class);
    private ProductController productController;
    private OrderController orderController;
    private ReportController reportController;

    public DashboardController(ProductController productController, OrderController orderController, ReportController reportController) {
        this.productController = productController;
        this.orderController = orderController;
        this.reportController = reportController;
    }

    public void displaySummary() {
        try {

            int totalProducts = productController.getAllProducts().size();
            int totalOrders = orderController.getAllOrders().size();


            logger.info("Dashboard Summary:");
            logger.info("Total Products: " + totalProducts);
            logger.info("Total Orders: " + totalOrders);


            reportController.generateSalesReport();


            reportController.generateInventoryReport();
        } catch (Exception e) {
            logger.error("Error displaying dashboard summary: " + e.getMessage());
            throw e;
        }
    }
}
