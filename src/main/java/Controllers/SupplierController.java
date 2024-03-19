package Controllers;

import Models.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class SupplierController {
    private static final Logger logger = LogManager.getLogger(SupplierController.class);
    private List<Supplier> supplierList;

    public SupplierController() {

        this.supplierList = new ArrayList<>();
    }

    public void createSupplier(Supplier supplier) {
        try {

            supplierList.add(supplier);
            logger.info("Supplier created: " + supplier.getName());
        } catch (Exception e) {
            logger.error("Error creating supplier: " + e.getMessage());

            throw e;
        }
    }

    public void updateSupplier(Supplier supplier) {
        try {

            for (int i = 0; i < supplierList.size(); i++) {
                if (supplierList.get(i).getId() == supplier.getId()) {
                    supplierList.set(i, supplier);
                    logger.info("Supplier updated: " + supplier.getName());
                    return;
                }
            }

            logger.warn("Supplier not found with ID: " + supplier.getId());
            throw new IllegalArgumentException("Supplier not found with ID: " + supplier.getId());
        } catch (Exception e) {
            logger.error("Error updating supplier: " + e.getMessage());

            throw e;
        }
    }

    public void deleteSupplier(int supplierId) {
        try {

            for (Supplier supplier : supplierList) {
                if (supplier.getId() == supplierId) {
                    supplierList.remove(supplier);
                    logger.info("Supplier deleted with ID: " + supplierId);
                    return;
                }
            }
            logger.warn("Supplier not found with ID: " + supplierId);
            throw new IllegalArgumentException("Supplier not found with ID: " + supplierId);
        } catch (Exception e) {
            logger.error("Error deleting supplier: " + e.getMessage());

            throw e;
        }
    }

    public List<Supplier> getAllSuppliers() {

        logger.info("Retrieved all suppliers");
        return supplierList;
    }

    public Supplier getSupplierById(int supplierId) {

        for (Supplier supplier : supplierList) {
            if (supplier.getId() == supplierId) {
                logger.info("Retrieved supplier by ID: " + supplierId);
                return supplier;
            }
        }
        logger.warn("Supplier not found with ID: " + supplierId);
        return null;
    }
}
