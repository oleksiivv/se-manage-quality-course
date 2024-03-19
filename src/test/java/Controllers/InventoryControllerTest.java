package Controllers;
import Controllers.InventoryController;
import Models.Order;
import Models.Product;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class InventoryControllerTest {
    private InventoryController inventoryController;
    private StubProductController productController;
    private StubOrderController orderController;

    @BeforeMethod
    public void setUp() {
        productController = new StubProductController();
        orderController = new StubOrderController();
        inventoryController = new InventoryController(productController, orderController);
    }

    @Test
    public void testProcessOrder() {

        Order order = new Order();
        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1, "Product 1", 100);
        product1.setQuantity(1);
        productController.createProduct(product1);

        products.add(product1);
        order.setProducts(products);


        orderController.addOrder(order);


        Order result = inventoryController.processOrder(1);

        assertEquals(result.getProducts().get(0).getQuantity(), 0);
    }

    @Test
    public void testGetLowStockProducts() {

        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1, "Product 1", 100);
        product1.setQuantity(3);

        products.add(product1);


        productController.setProducts(products);


        List<Product> lowStockProducts = inventoryController.getLowStockProducts(5);


        assertEquals(lowStockProducts.size(), 1);
        assertEquals(lowStockProducts.get(0), product1);
    }


    private static class StubProductController extends ProductController {
        private List<Product> products;

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        @Override
        public List<Product> getAllProducts() {
            return products;
        }
    }

    private static class StubOrderController extends OrderController {
        private Order order;

        public void addOrder(Order order) {
            this.order = order;
        }

        @Override
        public Order getOrderById(int orderId) {
            return order;
        }
    }
}
