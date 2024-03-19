package Controllers;

import Models.Order;
import Models.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class InventoryController {
    private static final Logger logger = LogManager.getLogger(InventoryController.class);
    private ProductController productController;
    private OrderController orderController;

    public InventoryController(ProductController productController, OrderController orderController) {
        this.productController = productController;
        this.orderController = orderController;
    }

    public Order processOrder(int orderId) {

        Order order = orderController.getOrderById(orderId);
        if (order == null) {
            logger.warn("Order not found with ID: " + orderId);
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }


        boolean productsAvailable = true;
        for (Product product : order.getProducts()) {
            Product inventoryProduct = productController.getProductById(product.getId());
            if (inventoryProduct == null || inventoryProduct.getQuantity() < product.getQuantity()) {
                productsAvailable = false;
                logger.warn("Product not available in inventory: " + product.getName());
                break;
            }
        }


        if (productsAvailable) {

            for (Product product : order.getProducts()) {
                Product inventoryProduct = productController.getProductById(product.getId());
                inventoryProduct.setQuantity(inventoryProduct.getQuantity() - product.getQuantity());
                productController.updateProduct(inventoryProduct);
            }
            logger.info("Order processed successfully: " + orderId);
        } else {
            logger.error("Order processing failed due to insufficient inventory");
        }

        return order;
    }

    public List<Product> getLowStockProducts(int threshold) {

        List<Product> lowStockProducts = productController.getAllProducts();
        lowStockProducts.removeIf(product -> product.getQuantity() >= threshold);
        return lowStockProducts;
    }
}
