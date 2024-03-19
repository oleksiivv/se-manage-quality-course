package Models;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SupplierTest {
    private Supplier supplier;

    @BeforeClass
    public void setUp() {
        supplier = new Supplier();
    }

    @AfterClass
    public void tearDown() {
        supplier = null;
    }

    @Test
    public void testSupplierCreation() {
        assertNotNull(supplier);
    }

    @Test(dependsOnMethods = "testSupplierCreation")
    public void testSupplierId() {
        supplier.setId(1);
        assertEquals(supplier.getId(), 1);
    }

    @Test(dependsOnMethods = "testSupplierCreation")
    public void testSupplierName() {
        supplier.setName("Supplier Inc.");
        assertEquals(supplier.getName(), "Supplier Inc.");
    }

    @Test(dependsOnMethods = "testSupplierCreation")
    public void testSupplierContactInfo() {
        supplier.setContactInfo("123 Main St, City, Country");
        assertEquals(supplier.getContactInfo(), "123 Main St, City, Country");
    }
}

