package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RootCategory extends Category {
    public RootCategory() {
        super("Root", new HashSet<>(Arrays.asList("SquareTrade", "Insurance")));
    }

    @Override
    public Set<String> getAllKeywords() {
        return getKeywords();
    }

    @Override
    public void inheritKeywords() {
        // Do nothing, root category has default keywords
    }
}
