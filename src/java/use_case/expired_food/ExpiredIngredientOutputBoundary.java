package use_case.expired_food;

import entity.Ingredient;

import java.util.List;

public interface ExpiredIngredientOutputBoundary {
//    void execute();

    //    void prepareFailView(String error);
    void deleteIngredient(Ingredient ingredient);

    void switchToInitialView();

    List<Ingredient> getCurrentIngredients();
}