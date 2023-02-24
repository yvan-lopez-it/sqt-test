package model;

import java.util.HashSet;
import java.util.Set;

public class SubCategory extends Category {
    public SubCategory(String name, Category parent) {
        super(name, parent);
    }

    public SubCategory(String name, Set<String> keywords, Category parent) {
        super(name, keywords, parent);
    }

    /**
     * Get all keywords from a category.
     * <p>
     * If the category itself does not have any keywords, it looks up to the parent hierarchy and inherits its keywords.
     *
     * @return A set of all keywords associated with a given category.
     */
    @Override
    public Set<String> getAllKeywords() {
        Set<String> allKeywords = new HashSet<>(getKeywords());

        if (allKeywords.isEmpty()) {
            Category currentCategory = getParent();

            while (currentCategory != null) {
                allKeywords.addAll(currentCategory.getKeywords());
                currentCategory = currentCategory.getParent();
            }
        }

        return allKeywords;
    }

    /**
     * Inherit keywords from parent categories if the current category has no keywords of its own.
     */
    @Override
    public void inheritKeywords() {
        Set<String> allKeywords = getAllKeywords();
        if (!allKeywords.isEmpty()) {
            super.getKeywords().clear();
            super.getKeywords().addAll(allKeywords);
        }
    }
}
