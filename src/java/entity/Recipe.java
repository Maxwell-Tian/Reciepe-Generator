package entity;

import java.util.List;
import java.util.Map;

/**
 * The representation of a recipe in our program.
 */
public interface Recipe {
    /**
     * Return the name of the recipe.
     * @return the name of the recipe.
     */
    String getName();

    /**
     * Return the description.
     * @return the description.
     */
    String getDescription();

    /**
     * Return the Category.
     * @return the Category.
     */
    String getCategory();

    /**
     * Return the recipe map mapping ingredients to weights(integer).
     * @return the map.
     */
    Map<String, Integer> getRecipeMap();
}
