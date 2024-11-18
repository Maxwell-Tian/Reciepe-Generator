package entity;

import java.util.List;

/**
 * Factory for creating CommonRecipe objects.
 */
public class CommonRecipeFactory implements RecipeFactory {

    @Override
    public Recipe create(String name, List<String> description) {
        return new CommonRecipe(name, description);
    };
}
