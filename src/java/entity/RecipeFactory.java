package entity;

import java.util.List;
import java.util.Map;

/**
 * Factory for creating recipes.
 */
public interface RecipeFactory {
    /**
     * Creates a new Recipe.
     * @param name the name of the Recipe.
     * @return the new Recipe.
     */
    Recipe create(String name, List<String> recipe);
}
