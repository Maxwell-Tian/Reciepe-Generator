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
     * Return the recipe map mapping ingredients to weights(integer).
     * @return the map.
     */
    List<String> getRecipeList();
}
