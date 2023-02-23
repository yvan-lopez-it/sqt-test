package test;

import model.RootCategory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static service.CategoryService.getCategoryLevel;
import static service.CategoryService.getKeywordsForCategory;

class CategoryServiceTest {

    @Test
    void testGetKeywordsForCategory() {
        RootCategory rootCategory = new RootCategory("Root", new HashSet<>());

        RootCategory furnitureCategory = new RootCategory("Furniture", new HashSet<>(Arrays.asList("chair", "table", "sofa")));
        rootCategory.addSubCategory(furnitureCategory);

        RootCategory electronicsCategory = new RootCategory("Electronics", new HashSet<>(Arrays.asList("TV", "phone", "laptop")));
        rootCategory.addSubCategory(electronicsCategory);

        RootCategory homeAppliancesCategory = new RootCategory("Home Appliances", new HashSet<>());
        rootCategory.addSubCategory(homeAppliancesCategory);

        RootCategory majorAppliancesCategory = new RootCategory("Major Appliances", new HashSet<>(Arrays.asList("refrigerator", "oven", "washer")));
        homeAppliancesCategory.addSubCategory(majorAppliancesCategory);

        RootCategory kitchenAppliancesCategory = new RootCategory("Kitchen Appliances", new HashSet<>(Arrays.asList("stove", "microwave")));
        majorAppliancesCategory.addSubCategory(kitchenAppliancesCategory);

        RootCategory generalAppliancesCategory = new RootCategory("General Appliances", new HashSet<>(Arrays.asList("air conditioner", "vacuum cleaner")));
        majorAppliancesCategory.addSubCategory(generalAppliancesCategory);

        RootCategory minorAppliancesCategory = new RootCategory("Minor Appliances", new HashSet<>(Arrays.asList("toaster", "blender")));
        homeAppliancesCategory.addSubCategory(minorAppliancesCategory);

        RootCategory lawnAndGardenCategory = new RootCategory("Lawn & Garden", new HashSet<>(Arrays.asList("lawn", "garden", "gardeningTools")));
        rootCategory.addSubCategory(lawnAndGardenCategory);

        Set<String> expectedKeywords = new HashSet<>(Arrays.asList("refrigerator", "oven", "washer"));
        Set<String> actualKeywords = getKeywordsForCategory(rootCategory, "Major Appliances");
        assertEquals(expectedKeywords, actualKeywords);

        expectedKeywords = new HashSet<>(Arrays.asList("stove", "microwave"));
        actualKeywords = getKeywordsForCategory(rootCategory, "Kitchen Appliances");
        assertEquals(expectedKeywords, actualKeywords);

        expectedKeywords = new HashSet<>();
        actualKeywords = getKeywordsForCategory(rootCategory, "Non-existent Category");
        assertEquals(expectedKeywords, actualKeywords);
    }


    @Test
    public void testGetCategoryLevelSameCategory() {
        RootCategory root = new RootCategory("Root", new HashSet<>());
        RootCategory electronics = new RootCategory("Electronics", new HashSet<>(Arrays.asList("TV", "phone", "laptop")));
        root.addSubCategory(electronics);

        int level = getCategoryLevel(root, root);

        assertEquals(0, level);
    }

    @Test
    public void testGetCategoryLevelDirectChild() {
        RootCategory root = new RootCategory("Root", new HashSet<>());
        RootCategory electronics = new RootCategory("Electronics", new HashSet<>(Arrays.asList("TV", "phone", "laptop")));
        root.addSubCategory(electronics);

        int level = getCategoryLevel(root, electronics);

        assertEquals(1, level);
    }

    @Test
    public void testGetCategoryLevelIndirectChild() {
        RootCategory root = new RootCategory("Root", new HashSet<>());
        RootCategory electronics = new RootCategory("Electronics", new HashSet<>(Arrays.asList("TV", "phone", "laptop")));
        root.addSubCategory(electronics);
        RootCategory phoneCategory = new RootCategory("Phone", new HashSet<>(Arrays.asList("smartphone", "flip phone")));
        electronics.addSubCategory(phoneCategory);

        int level = getCategoryLevel(root, phoneCategory);

        assertEquals(2, level);
    }

    @Test
    public void testGetCategoryLevelNotInTree() {
        RootCategory root = new RootCategory("Root", new HashSet<>());
        RootCategory electronics = new RootCategory("Electronics", new HashSet<>(Arrays.asList("TV", "phone", "laptop")));
        root.addSubCategory(electronics);
        RootCategory phoneCategory = new RootCategory("Phone", new HashSet<>(Arrays.asList("smartphone", "flip phone")));

        int level = getCategoryLevel(root, phoneCategory);

        assertEquals(-1, level);
    }


}