package use_case.expired_food;

import entity.Ingredient;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * ExpiredIngredientInteractor is responsible for handling the business logic of the
 * expiration warning use case. It implements the ExpiredIngredientInputBoundary interface
 * and interacts with the data access layer and presenter through defined boundaries.
 */
public class ExpiredIngredientInteractor implements ExpiredIngredientInputBoundary{
    private final ExpiredIngredientUserDataAccessInterface dataAccess;
    private final ExpiredIngredientOutputBoundary expiredIngredientOutputBoundary;

    /**
     * Constructs an ExpiredIngredientInteractor with the specified data access interface
     * and output boundary.
     *
     * @param dataAccess                    the interface for accessing ingredient data
     * @param expiredIngredientOutputBoundary the interface for outputting results to the presenter
     */
    public ExpiredIngredientInteractor(ExpiredIngredientUserDataAccessInterface dataAccess,
                                       ExpiredIngredientOutputBoundary expiredIngredientOutputBoundary) {
        this.dataAccess = dataAccess;
        this.expiredIngredientOutputBoundary = expiredIngredientOutputBoundary;
    }

    /**
     * Switches the system to the initial view, typically called when navigating back to the main screen.
     */
    @Override
    public void switchToInitialView() {
        expiredIngredientOutputBoundary.switchToInitialView();
    }

    /**
     * Executes the expiration warning use case by retrieving all ingredients,
     * checking their expiration dates, and returning a list of expired or near-expired ingredients.
     *
     * @return a list of expired or near-expired ingredients
     */
    public List<Ingredient> execute() {
        List<Ingredient> ingredients = expiredIngredientOutputBoundary.getCurrentIngredients();
        LocalDate today = LocalDate.now();

        List<Ingredient> expiredIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getExpiryDate().isBefore(today) || ingredient.getExpiryDate().isEqual(today)) {
                expiredIngredients.add(ingredient);
            }
        }

        return expiredIngredients;
    }

    public void deleteIngredients(Ingredient ingredient) {
//        List<Ingredient> currentIngredients = dataAccess.getCurrentIngredients();
//        for (Ingredient ingredient : ingredientsToDelete) {
//            currentIngredients.remove(ingredient);
//        }
//        dataAccess.setIngredients(currentIngredients);

        expiredIngredientOutputBoundary.deleteIngredient(ingredient);
        dataAccess.deleteIngredient(ingredient);
    }
}
