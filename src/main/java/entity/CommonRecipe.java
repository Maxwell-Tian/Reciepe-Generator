package main.java.entity;

import java.util.HashMap;
import java.util.Map;
/**
 * A simple implementation of the Recipe interface.
 */
public class CommonRecipe implements Recipe{

    private final String name;
    private final String description;
    private final Map<String, Integer> recipeMap;

    public CommonRecipe(String name, String description) {
        this.name = name;
        this.description = description;
        this.recipeMap = new HashMap<String, Integer>();
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}

    @Override
    public Map<String, Integer> getRecipeMap() {return recipeMap;}
}
