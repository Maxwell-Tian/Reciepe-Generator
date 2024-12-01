package entity;

import java.util.List;
import java.util.Map;

/**
 * Factory for creating CommonRecipe objects.
 */
public class CommonRecipeFactory implements RecipeFactory {

    @Override
    public Recipe create(String name, List<String> recipe) {
        return new CommonRecipe(name, recipe);
    };
}
