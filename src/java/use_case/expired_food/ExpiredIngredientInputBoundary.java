package use_case.expired_food;

import entity.Ingredient;
import java.util.List;

/**
 * The ExpiredIngredientInputBoundary interface defines the input operations
 * for the expiration warning use case.
 */
public interface ExpiredIngredientInputBoundary {
    List<Ingredient> execute();

    public void switchToInitialView();
}
