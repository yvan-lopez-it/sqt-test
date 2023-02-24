import model.RootCategory;
import model.SubCategory;
import service.CategoryService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        //---- Few tests for Main program: ----

        // 1st. level
        RootCategory rootCategory = new RootCategory();

        // 2nd. level
        Set<String> furnitureKeywords = new HashSet<>(Arrays.asList("furniture", "chair", "sofa"));
        SubCategory furnitureCategory = new SubCategory("Furniture", furnitureKeywords, rootCategory);

        Set<String> electronicsKeywords = new HashSet<>(Arrays.asList("TV", "phone", "laptop"));
        SubCategory electronicsCategory = new SubCategory("Electronics", electronicsKeywords, rootCategory);

        SubCategory homeAppliancesCategory = new SubCategory("Home Appliances", rootCategory);

        // 3rd. level
        Set<String> majorAppliancesKeywords = new HashSet<>(Arrays.asList("refrigerator", "oven", "washer"));
        SubCategory majorAppliancesCategory = new SubCategory("Major Appliances", majorAppliancesKeywords, homeAppliancesCategory);

        Set<String> minorAppliancesKeywords = new HashSet<>(Arrays.asList("toaster", "blender"));
        SubCategory minorAppliancesCategory = new SubCategory("Minor Appliances", minorAppliancesKeywords, homeAppliancesCategory);

        Set<String> lawnAndGardenKeywords = new HashSet<>(Arrays.asList("Lawn", "Garden", "GardeningTools"));
        SubCategory lawnAndGardenCategory = new SubCategory("Lawn & Garden", lawnAndGardenKeywords, homeAppliancesCategory);

        // 4th. level
        Set<String> kitchenAppliancesKeywords = new HashSet<>(Arrays.asList("stove", "microwave"));
        SubCategory kitchenAppliancesCategory = new SubCategory("Kitchen Appliances", kitchenAppliancesKeywords, majorAppliancesCategory);

        Set<String> generalAppliancesKeywords = new HashSet<>(Arrays.asList("air conditioner", "vacuum cleaner"));
        SubCategory generalAppliancesCategory = new SubCategory("General Appliances", generalAppliancesKeywords, majorAppliancesCategory);


        // -------- Run --------
        CategoryService.getCategoryKeywords(homeAppliancesCategory).forEach(System.out::println);
        System.out.println();
        CategoryService.getCategoryKeywords(generalAppliancesCategory).forEach(System.out::println);
        System.out.println();
        System.out.println("Level (zero based): " + CategoryService.getCategoryLevel(kitchenAppliancesCategory));

    }
}
