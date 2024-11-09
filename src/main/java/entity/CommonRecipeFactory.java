package java.entity;

/**
 * Factory for creating CommonRecipe objects.
 */
public class CommonRecipeFactory implements RecipeFactory {

    @Override
    public Recipe create(String name, String description) {
        return new CommonRecipe(name, description);
    };
}
