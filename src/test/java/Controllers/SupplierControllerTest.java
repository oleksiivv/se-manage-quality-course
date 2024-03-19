package Controllers;
import Models.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class SupplierControllerTest {
    private SupplierController supplierController;
    private static final Logger logger = LogManager.getLogger(SupplierControllerTest.class);

    @BeforeMethod
    public void setUp() {
        supplierController = new SupplierController();
    }

    @Test
    public void testCreateSupplier() {
        Supplier supplier = new Supplier();
        supplier.setId(1);
        supplier.setName("Supplier 1");

        supplierController.createSupplier(supplier);

        List<Supplier> suppliers = supplierController.getAllSuppliers();
        assertNotNull(suppliers);
        assertEquals(suppliers.size(), 1);
        assertEquals(suppliers.get(0).getName(), "Supplier 1");
    }

    @Test
    public void testUpdateSupplier() {
        Supplier supplier = new Supplier();
        supplier.setId(1);
        supplier.setName("Supplier 1");
        supplierController.createSupplier(supplier);

        Supplier updatedSupplier = new Supplier();
        updatedSupplier.setId(1);
        updatedSupplier.setName("Updated Supplier 1");
        supplierController.updateSupplier(updatedSupplier);

        List<Supplier> suppliers = supplierController.getAllSuppliers();
        assertNotNull(suppliers);
        assertEquals(suppliers.size(), 1);
        assertEquals(suppliers.get(0).getName(), "Updated Supplier 1");
    }

    @Test
    public void testDeleteSupplier() {
        Supplier supplier = new Supplier();
        supplier.setId(1);
        supplier.setName("Supplier 1");
        supplierController.createSupplier(supplier);

        supplierController.deleteSupplier(1);

        List<Supplier> suppliers = supplierController.getAllSuppliers();
        assertNotNull(suppliers);
        assertEquals(suppliers.size(), 0);
    }

    @Test
    public void testGetSupplierById() {
        Supplier supplier = new Supplier();
        supplier.setId(1);
        supplier.setName("Supplier 1");
        supplierController.createSupplier(supplier);

        Supplier retrievedSupplier = supplierController.getSupplierById(1);
        assertNotNull(retrievedSupplier);
        assertEquals(retrievedSupplier.getName(), "Supplier 1");
    }

    @Test
    public void testGetNonExistingSupplierById() {
        Supplier retrievedSupplier = supplierController.getSupplierById(1);
        assertNull(retrievedSupplier);
    }

    @Test(expectedExceptions = Exception.class)
    public void testUpdateNonExistingSupplier() {
        Supplier supplier = new Supplier();
        supplier.setId(1);
        supplier.setName("Supplier 1");

        // Attempting to update non-existing supplier should throw an exception
        supplierController.updateSupplier(supplier);
    }

    @Test(expectedExceptions = Exception.class)
    public void testDeleteNonExistingSupplier() {
        // Attempting to delete non-existing supplier should throw an exception
        supplierController.deleteSupplier(1);
    }
}
