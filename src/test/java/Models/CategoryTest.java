package Models;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

public class CategoryTest {
    private Category category;

    @BeforeClass
    public void setUp() {
        category = new Category();
    }

    @AfterClass
    public void tearDown() {
        category = null;
    }

    @Test
    public void testCategoryCreation() {
        assertNotNull(category);
    }

    @Test(dependsOnMethods = "testCategoryCreation")
    public void testCategoryId() {
        category.setId(1);
        assertEquals(category.getId(), 1);
    }

    @Test(dependsOnMethods = "testCategoryCreation")
    public void testCategoryName() {
        category.setName("Electronics");
        assertEquals(category.getName(), "Electronics");
    }

    @Test(dependsOnMethods = "testCategoryCreation")
    public void testCategoryDescription() {
        category.setDescription("Electronic devices and appliances");
        assertEquals(category.getDescription(), "Electronic devices and appliances");
    }
}
