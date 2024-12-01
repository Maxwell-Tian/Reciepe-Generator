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
    private final List<String> recipeList;

    public CommonRecipe(String name, List<String> recipeList) {
        this.name = name;
        this.recipeList = recipeList;
    }

    @Override
    public String getName() {return name;}


    @Override
    public List<String> getRecipeList() {return recipeList;}
}
