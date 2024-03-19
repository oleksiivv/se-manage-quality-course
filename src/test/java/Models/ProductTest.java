package Models;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

public class ProductTest {
    private Product product;

    @BeforeClass
    public void setUp() {
        product = new Product(1, "Laptop", 999.99);
    }

    @AfterClass
    public void tearDown() {
        product = null;
    }

    @Test
    public void testProductCreation() {
        assertNotNull(product);
    }

    @Test(dependsOnMethods = "testProductCreation")
    public void testProductId() {
        assertEquals(product.getId(), 1);
        product.setId(2);
        assertEquals(product.getId(), 2);
    }

    @Test(dependsOnMethods = "testProductCreation")
    public void testProductName() {
        assertEquals(product.getName(), "Laptop");
        product.setName("book");
        assertEquals(product.getName(), "book");
    }

    @Test(dataProvider = "invalidProductData", expectedExceptions = IllegalArgumentException.class)
    public void testInvalidProductCreation(int id, String name, double price) {
        new Product(id, name, price);
        fail("Expected IllegalArgumentException was not thrown");
    }

    @DataProvider(name = "invalidProductData")
    public Object[][] getInvalidProductData() {
        return new Object[][]{
                {-1, "Laptop", 999.99},
                {1, null, 999.99},
                {1, "Laptop", -999.99}
        };
    }

    @Test(dependsOnMethods = "testProductCreation")
    public void testProductDescription() {
        product.setDescription("Description");
        assertEquals(product.getDescription(), "Description");
    }

    @Test(dependsOnMethods = "testProductCreation")
    public void testProductPrice() {
        product.setPrice(100);
        assertEquals(product.getPrice(), 100);
    }

    @Test(dependsOnMethods = "testProductCreation")
    public void testProductQuantity() {
        product.setQuantity(10);
        assertEquals(product.getQuantity(), 10);
    }

    @Test(dependsOnMethods = "testProductCreation")
    public void testProductCategory() {
        Category category = new Category();
        product.setCategory(category);
        assertEquals(product.getCategory(), category);
    }

    @Test(dependsOnMethods = "testProductCreation")
    public void testProductSupplier() {
        Supplier supplier = new Supplier();
        product.setSupplier(supplier);
        assertEquals(product.getSupplier(), supplier);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructorInvalidIdException() {
        new Product(0, "Product 0", 10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructorNullNameException() {
        new Product(5, null, 10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructorEmptyNameException() {
        new Product(6, "", 10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSecondConstructorInvalidIdException() {
        new Product(0, "Product 0", 10.0, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSecondConstructorNullNameException() {
        new Product(5, null, 10.0, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSecondConstructorEmptyNameException() {
        new Product(6, "", 10.0, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSecondConstructorInvalidPriceException() {
        new Product(6, "E", -10.0, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSecondConstructorInvalidQuantity() {
        new Product(6, "E", 10.0, -1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructorInvalidPriceException() {
        new Product(7, "Product 7", -10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetPriceInvalidPriceException() {
        Product product = new Product(8, "Product 8", 35.0);

        product.setPrice(-20.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetNameNullNameException() {
        Product product = new Product(9, "Product 9", 40.0);

        product.setName(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetInvalidQuantityException() {
        Product product = new Product(9, "Product 9", 40.0);

        product.setQuantity(-6);
    }
}
