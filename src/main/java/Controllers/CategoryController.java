package Controllers;

import Models.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class CategoryController {
    private static final Logger logger = LogManager.getLogger(CategoryController.class);
    private List<Category> categoryList;

    public CategoryController() {

        this.categoryList = new ArrayList<>();
    }

    public void createCategory(Category category) {
        try {
            if (category == null) {
                throw new IllegalArgumentException("Category cannot be null");
            }

            categoryList.add(category);

            logger.info("Category created: " + category.getName());
        } catch (Exception e) {
            logger.error("Error creating category: " + e.getMessage());

            throw e;
        }
    }

    public void updateCategory(Category category) {
        try {
            if (category == null) {
                throw new IllegalArgumentException("Category cannot be null");
            }
            if (category.getId() <= 0) {
                throw new IllegalArgumentException("Invalid category ID");
            }


            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getId() == category.getId()) {
                    categoryList.set(i, category);
                    logger.info("Category updated: " + category.getName());
                    return;
                }
            }
            logger.warn("Category not found with ID: " + category.getId());
        } catch (Exception e) {
            logger.error("Error updating category: " + e.getMessage());

            throw e;
        }
    }

    public void deleteCategory(int categoryId) {
        try {
            if (categoryId <= 0) {
                throw new IllegalArgumentException("Invalid category ID");
            }


            for (Category category : categoryList) {
                if (category.getId() == categoryId) {
                    categoryList.remove(category);
                    logger.info("Category deleted with ID: " + categoryId);
                    return;
                }
            }
            logger.warn("Category not found with ID: " + categoryId);
        } catch (Exception e) {
            logger.error("Error deleting category: " + e.getMessage());

            throw e;
        }
    }

    public List<Category> getAllCategories() {

        logger.info("Retrieved all categories");
        return categoryList;
    }

    public Category getCategoryById(int categoryId) {

        for (Category category : categoryList) {
            if (category.getId() == categoryId) {
                logger.info("Retrieved category by ID: " + categoryId);
                return category;
            }
        }
        logger.warn("Category not found with ID: " + categoryId);
        return null;
    }
}
