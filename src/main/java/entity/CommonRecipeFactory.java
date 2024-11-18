package entity;

import java.util.List;
import java.util.Map;

/**
 * Factory for creating CommonRecipe objects.
 */
public class CommonRecipeFactory implements RecipeFactory {

    @Override
    public Recipe create(String name, List<String> description, Map<String, Integer> recipe) {
        return new CommonRecipe(name, description, recipe);
    };
}
