package Controllers;

import Models.Category;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class CategoryControllerTest {
    private CategoryController categoryController;

    @BeforeClass
    public void setUp() {
        categoryController = new CategoryController();
    }

    @AfterClass
    public void tearDown() {
        categoryController = null;
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setName("Electronics");
        categoryController.createCategory(category);

        List<Category> categories = categoryController.getAllCategories();
        assertEquals(categories.size(), 1);
        assertEquals(categories.get(0).getName(), "Electronics");
    }

    @Test(dependsOnMethods = "testCreateCategory")
    public void testUpdateCategory() {
        Category category = new Category();
        category.setId(1); // Assuming category with ID 1 already exists
        category.setName("_Books");
        categoryController.createCategory(category);

        category.setName("Books");
        categoryController.updateCategory(category);

        Category updatedCategory = categoryController.getCategoryById(1);
        assertEquals(updatedCategory.getName(), "Books");
    }

    @Test(dependsOnMethods = "testCreateCategory")
    public void testDeleteCategory() {
        categoryController.deleteCategory(1); // Assuming category with ID 1 already exists
        assertNull(categoryController.getCategoryById(1));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullCategory() {
        categoryController.createCategory(null);
    }

    @Test(dependsOnMethods = "testCreateCategory", expectedExceptions = IllegalArgumentException.class)
    public void testUpdateCategoryWithInvalidId() {
        Category category = new Category();
        category.setId(-1); // Invalid ID
        categoryController.updateCategory(category);
    }

    @Test(dependsOnMethods = "testCreateCategory", expectedExceptions = IllegalArgumentException.class)
    public void testDeleteCategoryWithInvalidId() {
        categoryController.deleteCategory(-1); // Invalid ID
    }
}

