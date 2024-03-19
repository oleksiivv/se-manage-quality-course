package Controllers;

import Models.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);
    private List<Product> productList;

    public ProductController() {

        this.productList = new ArrayList<>();
    }

    public void createProduct(Product product) {
        try {

            productList.add(product);
            logger.info("Product created: " + product.getName());
        } catch (Exception e) {
            logger.error("Error creating product: " + e.getMessage());

            throw e;
        }
    }

    public void updateProduct(Product product) {
        try {

            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getId() == product.getId()) {
                    productList.set(i, product);
                    logger.info("Product updated: " + product.getName());
                    return;
                }
            }
            logger.warn("Product not found with ID: " + product.getId());
            throw new IllegalArgumentException("Product not found with ID: " + product.getId());
        } catch (Exception e) {
            logger.error("Error updating product: " + e.getMessage());

            throw e;
        }
    }

    public void deleteProduct(int productId) {
        try {

            for (Product product : productList) {
                if (product.getId() == productId) {
                    productList.remove(product);
                    logger.info("Product deleted with ID: " + productId);
                    return;
                }
            }
            logger.warn("Product not found with ID: " + productId);
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        } catch (Exception e) {
            logger.error("Error deleting product: " + e.getMessage());

            throw e;
        }
    }

    public List<Product> getAllProducts() {

        logger.info("Retrieved all products");
        return productList;
    }

    public Product getProductById(int productId) {

        for (Product product : productList) {
            if (product.getId() == productId) {
                logger.info("Retrieved product by ID: " + productId);
                return product;
            }
        }
        logger.warn("Product not found with ID: " + productId);
        return null;
    }
}
