package use_case.searchNutrition;

import entity.Ingredient;

public interface SearchNutritionInputBoundary {

    void getNutrition(Ingredient ingredient);

    void switchToNutritionView();

    void switchToInitialView();

}
