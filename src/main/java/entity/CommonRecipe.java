package entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * A simple implementation of the Recipe interface.
 * For the attribute description, it is a list of strings, which the first string
 * represents the text description of the recipe and the second string represents the
 * category of the recipe.
 */
public class CommonRecipe implements Recipe{

    private final String name;
    private final List<String> description;
    private final Map<String, Integer> recipeMap;

    public CommonRecipe(String name, List<String> description, Map<String, Integer> recipeMap) {
        this.name = name;
        this.description = description;
        this.recipeMap = recipeMap;
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description.getFirst();}

    @Override
    public String getCategory() {return description.get(1);}

    @Override
    public Map<String, Integer> getRecipeMap() {return recipeMap;}
}
