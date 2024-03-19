package Controllers;

import Models.Order;
import Models.Product;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ReportControllerTest {

    @Test
    public void testGenerateSalesReportPositive() {

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1, "Product1", 10.0, 2));
        products.add(new Product(2, "Product2", 15.0, 3));
        order1.setProducts(products);

        Order order2 = new Order(2);
        List<Product> products2 = new ArrayList<Product>();
        products2.add(new Product(3, "Product3", 20.0, 1));
        products2.add(new Product(4, "Product4", 25.0, 4));
        order2.setProducts(products2);
        orders.add(order1);
        orders.add(order2);


        ProductController productController = new ProductController();
        OrderController orderController = new OrderController();


        for (Order order : orders) {
            orderController.createOrder(order);
        }


        ReportController reportController = new ReportController(productController, orderController);


        double expectedTotalSales = (10.0 * 2 + 15.0 * 3) + (20.0 * 1 + 25.0 * 4);
        double actualTotalSales = reportController.generateSalesReport();
        assertEquals(actualTotalSales, expectedTotalSales);
    }

    @Test
    public void testGenerateSalesReportException() {
        // Create real instance of OrderController that throws an exception
        OrderController orderController = new OrderController() {
            @Override
            public List<Order> getAllOrders() {
                throw new RuntimeException("Error retrieving orders");
            }
        };

        // Create ReportController instance
        ReportController reportController = new ReportController(new ProductController(), orderController);

        // Test generateSalesReport
        assertThrows(RuntimeException.class, () -> reportController.generateSalesReport());
    }

    @Test
    public void testGenerateInventoryReportPositive() {
        // Set up sample data for products
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Product1", 10.0, 5));
        products.add(new Product(2, "Product2", 15.0, 10));

        // Create real instance of ProductController
        ProductController productController = new ProductController();

        // Add sample data to controller
        for (Product product : products) {
            productController.createProduct(product);
        }

        // Create ReportController instance
        ReportController reportController = new ReportController(productController, new OrderController());

        // Test generateInventoryReport
        int expectedTotalQuantity = 5 + 10;
        int actualTotalQuantity = reportController.generateInventoryReport();
        assertEquals(actualTotalQuantity, expectedTotalQuantity);
    }

    @Test
    public void testGenerateInventoryReportException() {
        // Create real instance of ProductController that throws an exception
        ProductController productController = new ProductController() {
            @Override
            public List<Product> getAllProducts() {
                throw new RuntimeException("Error retrieving products");
            }
        };

        // Create ReportController instance
        ReportController reportController = new ReportController(productController, new OrderController());

        // Test generateInventoryReport
        assertThrows(RuntimeException.class, () -> reportController.generateInventoryReport());
    }
}
