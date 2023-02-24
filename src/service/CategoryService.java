package service;

import model.Category;

import java.util.Set;

public class CategoryService {

    public static Set<String> getCategoryKeywords(Category category) {
        return category.getAllKeywords();
    }

    /**
     * The category input parameter is used as the starting point for determining the level.
     * The implementation added benefit of being able to determine the level of any category,
     * not just the RootCategory.
     *
     * @param category The category used to looking for the level.
     * @return The level number (zero based).
     */
    public static int getCategoryLevel(Category category) {
        int level = 0;
        Category currentCategory = category;

        while (currentCategory.getParent() != null) {
            level++;
            currentCategory = currentCategory.getParent();
        }

        return level;
    }
}
