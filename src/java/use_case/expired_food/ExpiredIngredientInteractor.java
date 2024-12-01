package use_case.expired_food;

import entity.Ingredient;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ExpiredIngredientInteractor implements ExpiredIngredientInputBoundary{
    private final ExpiredIngredientUserDataAccessInterface dataAccess;
    private final ExpiredIngredientOutputBoundary expiredIngredientOutputBoundary;

    public ExpiredIngredientInteractor(ExpiredIngredientUserDataAccessInterface dataAccess,
                                       ExpiredIngredientOutputBoundary expiredIngredientOutputBoundary) {
        this.dataAccess = dataAccess;
        this.expiredIngredientOutputBoundary = expiredIngredientOutputBoundary;
    }

    @Override
    public void switchToInitialView() {
        expiredIngredientOutputBoundary.switchToInitialView();
    }

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
