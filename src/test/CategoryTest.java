package test;

import model.Category;
import model.RootCategory;
import model.SubCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.CategoryService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    /**
     * No keywords.
     */
    @Test
    public void testGetCategoryKeywords_NoKeywords() {
        Category category = new SubCategory("Test Category", new RootCategory());
        Set<String> keywords = CategoryService.getCategoryKeywords(category);
        Assertions.assertFalse(keywords.isEmpty());
    }

    /**
     * Only category keywords.
     */
    @Test
    public void testGetCategoryKeywords_OnlyCategoryKeywords() {
        Set<String> categoryKeywords = new HashSet<>(Arrays.asList("Keyword1", "Keyword2"));
        Category category = new SubCategory("Test Category", categoryKeywords, new RootCategory());
        Set<String> keywords = CategoryService.getCategoryKeywords(category);
        assertEquals(categoryKeywords, keywords);
    }

    /**
     * Only parent keywords.
     */
    @Test
    public void testGetCategoryKeywords_OnlyParentKeywords() {
        Set<String> parentKeywords = new HashSet<>(Arrays.asList("Keyword1", "Keyword2", "Insurance", "SquareTrade"));
        Category parent = new SubCategory("Parent Category", parentKeywords, new RootCategory());
        Category category = new SubCategory("Test Category", parent);

        Set<String> keywords = CategoryService.getCategoryKeywords(category);

        assertEquals(parentKeywords, keywords);
    }

    /**
     * Category and parent keywords.
     */
    @Test
    public void testGetCategoryKeywords_CategoryAndParentKeywords() {
        Set<String> parentKeywords = new HashSet<>(Arrays.asList("Keyword1", "Keyword2"));
        Set<String> categoryKeywords = new HashSet<>(Arrays.asList("Keyword2", "Keyword3"));
        Category parent = new SubCategory("Parent Category", parentKeywords, new RootCategory());
        Category category = new SubCategory("Test Category", categoryKeywords, parent);
        Set<String> expectedKeywords = new HashSet<>(Arrays.asList("Keyword2", "Keyword3"));
        Set<String> keywords = CategoryService.getCategoryKeywords(category);
        assertEquals(expectedKeywords, keywords);
    }


    /**
     * Testing the root category has a level of 0.
     */
    @Test
    public void testGetCategoryLevel_RootCategory() {
        Category root = new RootCategory();
        int level = CategoryService.getCategoryLevel(root);
        assertEquals(0, level);
    }

    /**
     * Testing a subcategory with a parent category has a level greater than 0.
     */
    @Test
    public void testGetCategoryLevel_SubCategory_Level1() {
        Category root = new RootCategory();
        Category subCategoryTest = new SubCategory("SubCategoryTest", root);
        int level = CategoryService.getCategoryLevel(subCategoryTest);
        assertEquals(1, level);
    }


    /**
     * Testing a subcategory with multiple parent categories has the correct level.
     */
    @Test
    public void testGetCategoryLevel_SubCategory_Level2() {
        Category root = new RootCategory();
        Category subCategory1 = new SubCategory("SubCategory1", root);
        Category subCategory2 = new SubCategory("SubCategory2", subCategory1);
        int level = CategoryService.getCategoryLevel(subCategory2);
        assertEquals(2, level);
    }

    /**
     * Testing a subcategory with no parent category has a level of 0.
     */
    @Test
    public void testGetCategoryLevel_SubCategory_NoParent() {
        Category category = new SubCategory("category", new HashSet<>(), null);
        int level = CategoryService.getCategoryLevel(category);
        assertEquals(0, level);
    }


}

