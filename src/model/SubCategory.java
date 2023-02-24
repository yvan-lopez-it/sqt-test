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
     * Get all keywords associated with a given category and its parent hierarchy.
     * <p>
     * This method returns a set of all keywords associated with a given category. If the category
     * itself does not have any keywords, it looks up to the parent hierarchy and inherits its keywords.
     *
     * @return A set of all keywords associated with a given category and its parent hierarchy.
     */
    @Override
    public Set<String> getAllKeywords() {
        Set<String> allKeywords = new HashSet<>(getKeywords());

        // If the category has no keywords of its own, inherit keywords from parent hierarchy
        if (allKeywords.isEmpty() && getParent() != null) {
            allKeywords.addAll(getParent().getAllKeywords());
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
