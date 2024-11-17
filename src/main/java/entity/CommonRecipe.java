package entity;

import java.util.HashMap;
import java.util.Map;
/**
 * A simple implementation of the Recipe interface.
 */
public class CommonRecipe implements Recipe{

    private final String name;
    private final List<String> description;
    private final Map<List<String>, Integer> recipeMap;

    public CommonRecipe(String name, List<String> description) {
        this.name = name;
        this.description = description;
        this.recipeMap = new HashMap<List<String>, Integer>();
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description.get(0);}

    @Override
    public String getCategory() {return description.get(1);}

    @Override
    public Map<List<String>, Integer> getRecipeMap() {return recipeMap;}
}
