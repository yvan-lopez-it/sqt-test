package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RootCategory {
    private String name;
    private Set<String> keyWords;
    private List<RootCategory> subCategories = new ArrayList<>();

    public RootCategory(String name, Set<String> keyWords) {
        this.name = name;
        this.keyWords = keyWords;
    }

    public void addSubCategory(RootCategory subCategory) {
        this.subCategories.add(subCategory);
    }

    public String getName() {
        return name;
    }

    public Set<String> getKeyWords() {
        return keyWords;
    }

    public List<RootCategory> getSubCategories() {
        return subCategories;
    }

}
