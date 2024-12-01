package use_case.expired_food;

import entity.Ingredient;

import java.time.LocalDate;
import use_case.expired_food.CheckExpiredIngredientUserDataAccessInterface;
import java.util.List;
import java.util.ArrayList;

/**
 * Interactor for the Check Expired Ingredients use case. Implements the logic
 * to identify and manage expired ingredients.
 */
public class CheckExpiredIngredientInteractor {
    private final CheckExpiredIngredientUserDataAccessInterface dataAccess;

    /**
     * Constructs a CheckExpiredIngredientInteractor with the specified data access object.
     *
     * @param dataAccess The data access interface for ingredient management.
     */
    public CheckExpiredIngredientInteractor(CheckExpiredIngredientUserDataAccessInterface dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * Retrieves a list of ingredients that have expired or are expiring today.
     *
     * @return A list of expired ingredients.
     */
    public List<Ingredient> execute() {
        List<Ingredient> ingredients = dataAccess.getAllIngredients();
        LocalDate today = LocalDate.now();

        List<Ingredient> expiredIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getExpiryDate().isBefore(today) || ingredient.getExpiryDate().isEqual(today)) {
                expiredIngredients.add(ingredient);
            }
        }

        return expiredIngredients;
    }

    /**
     * Deletes the specified list of expired ingredients from the user's collection.
     *
     * @param ingredientsToDelete The list of expired ingredients to delete.
     */
    public void deleteIngredients(List<Ingredient> ingredientsToDelete) {
        List<Ingredient> currentIngredients = dataAccess.getAllIngredients();
        for (Ingredient ingredient : ingredientsToDelete) {
            currentIngredients.remove(ingredient);
        }
        dataAccess.setIngredients(currentIngredients);
    }
}

