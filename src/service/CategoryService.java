package service;


import model.RootCategory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CategoryService {

    public static RootCategory rootCategory;
    static RootCategory furnitureCategory;
    static RootCategory electronicsCategory;
    static RootCategory homeApplianceCategory;
    static RootCategory majorApplianceCategory;
    static RootCategory kitchenApplianceCategory;
    static RootCategory generalApplianceCategory;
    static RootCategory minorApplianceCategory;
    static RootCategory lawnAndGardenCategory;


    static {
        rootCategory = new RootCategory("Root", new HashSet<>());

        furnitureCategory = new RootCategory("Furniture", new HashSet<>(Arrays.asList("furniture", "chair", "sofa")));
        electronicsCategory = new RootCategory("Electronics", new HashSet<>(Arrays.asList("TV", "phone", "laptop")));
        homeApplianceCategory = new RootCategory("Electronics", new HashSet<>());

        rootCategory.addSubCategory(furnitureCategory);
        rootCategory.addSubCategory(electronicsCategory);
        rootCategory.addSubCategory(homeApplianceCategory);

        majorApplianceCategory = new RootCategory("Major Appliance", new HashSet<>(Arrays.asList("refrigerator", "oven", "washer")));
        homeApplianceCategory.addSubCategory(majorApplianceCategory);

        kitchenApplianceCategory = new RootCategory("Kitchen Appliance", new HashSet<>(Arrays.asList("stove", "mircowave")));
        majorApplianceCategory.addSubCategory(kitchenApplianceCategory);

        generalApplianceCategory = new RootCategory("General Appliance", new HashSet<>(Arrays.asList("air conditioner", "vacuum cleaner")));
        majorApplianceCategory.addSubCategory(generalApplianceCategory);

        minorApplianceCategory = new RootCategory("Minor Appliance", new HashSet<>(Arrays.asList("toaster", "blender")));
        homeApplianceCategory.addSubCategory(minorApplianceCategory);

        lawnAndGardenCategory = new RootCategory("Lawn & Garden", new HashSet<>(Arrays.asList("lawn", "garden", "gardeningTools")));
        homeApplianceCategory.addSubCategory(minorApplianceCategory);

    }


    /**
     * 2.	Write code that gets keywords for a given category.
     *
     * @param category     The Root category.
     * @param categoryName Name of the category.
     * @return A list with all the keywords from a specific category.
     */

    public static Set<String> getKeywordsForCategory(RootCategory category, String categoryName) {
        //If the category name is the same that the one from the given category
        // then it just returns its keywords.
        if (category.getName().equalsIgnoreCase(categoryName)) {
            return category.getKeyWords();
        } else {
            //Let's do it recursive.
            for (RootCategory subCategory : category.getSubCategories()) {
                Set<String> keywords = getKeywordsForCategory(subCategory, categoryName);
                if (!keywords.isEmpty()) {
                    return keywords;
                }
            }
        }
        return new HashSet<>();
    }

    /**
     * 3.	Write code that gets the "level" of the category starting from the Root.
     *
     * @param root     The Root category.
     * @param category The category where looking for the level.
     * @return The number level.
     */

    public static int getCategoryLevel(RootCategory root, RootCategory category) {

        if (root == category) {
            return 0;
        }

        for (RootCategory subCategory : root.getSubCategories()) {
            int level = getCategoryLevel(subCategory, category);
            if (level != -1) {
                return level + 1;
            }
        }
        return -1;
    }

}
