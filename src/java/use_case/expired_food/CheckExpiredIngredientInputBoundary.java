package use_case.expired_food;

import entity.Ingredient;
import java.util.List;

/**
 * Interface defining the input boundary for checking expired ingredients.
 * Implementers of this interface should provide logic to fetch and process expired ingredients.
 */
public interface CheckExpiredIngredientInputBoundary {
    /**
     * Executes the process of retrieving a list of expired ingredients.
     *
     * @return A list of expired ingredients.
     */
    List<Ingredient> execute();
}
