package model;

import java.util.*;

public abstract class Category {
    private final String name;

    // final and used a defensive copy when setting the value in the constructor to make the class immutable and follow the OCP principle.
    private final Set<String> keywords;

    private final List<Category> subcategories;
    private final Category parent;

    public Category(String name, Set<String> keywords) {
        this(name, keywords, null);
    }

    public Category(String name, Category parent) {
        this(name, new HashSet<>(), parent);
    }

    public Category(String name, Set<String> keywords, Category parent) {
        this.name = name;
        this.keywords = new HashSet<>(keywords);
        this.subcategories = new ArrayList<>();
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public Set<String> getKeywords() {
        // unmodifiable to further enforce immutability and follow the LSP principle.
        return Collections.unmodifiableSet(keywords);
    }

    public Category getParent() {
        return parent;
    }

    public List<Category> getSubcategories() {
        // Collections.unmodifiableList to prevent direct modification of the list and follow the LSP principle.
        return Collections.unmodifiableList(subcategories);
    }

    public void addSubcategory(Category subcategory) {
        subcategories.add(subcategory);
    }

    public abstract Set<String> getAllKeywords();

    public abstract void inheritKeywords();

    // Removed the setKeywords method to prevent changing the keywords field after construction,
    // thus enforcing immutability and following the OCP principle.


}
