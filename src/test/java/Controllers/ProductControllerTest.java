package Controllers;

import Controllers.ProductController;
import Models.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ProductControllerTest {
    private ProductController productController;
    private static final Logger logger = LogManager.getLogger(ProductControllerTest.class);

    @BeforeMethod
    public void setUp() {
        productController = new ProductController();
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product(1, "Product 1", 10.0);

        productController.createProduct(product);

        List<Product> products = productController.getAllProducts();
        assertNotNull(products);
        assertEquals(products.size(), 1);
        assertEquals(products.get(0).getName(), "Product 1");
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product(1, "Product 1", 10.0);

        productController.createProduct(product);

        product.setName("Updated Product 1");
        product.setPrice(20.0);
        productController.updateProduct(product);

        List<Product> products = productController.getAllProducts();
        assertNotNull(products);
        assertEquals(products.size(), 1);
        assertEquals(products.get(0).getName(), "Updated Product 1");
        assertEquals(products.get(0).getPrice(), 20.0);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product(1, "Product 1", 10.0);

        productController.createProduct(product);

        productController.deleteProduct(1);

        List<Product> products = productController.getAllProducts();
        assertNotNull(products);
        assertEquals(products.size(), 0);
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1, "Product 1", 10.0);

        productController.createProduct(product);

        Product retrievedProduct = productController.getProductById(1);
        assertNotNull(retrievedProduct);
        assertEquals(retrievedProduct.getName(), "Product 1");
    }

    @Test
    public void testGetNonExistingProductById() {
        Product retrievedProduct = productController.getProductById(1);
        assertNull(retrievedProduct);
    }

    @Test
    public void testUpdateProductProductNotFoundException() {
        ProductController productController = new ProductController();

        assertThrows(IllegalArgumentException.class, () -> {
            productController.updateProduct(new Product(1000, "Non-existing Product", 10.0, 5));
        });
    }

    @Test
    public void testDeleteProductProductNotFoundException() {
        ProductController productController = new ProductController();

        assertThrows(IllegalArgumentException.class, () -> {
            productController.deleteProduct(1000);
        });
    }
}
