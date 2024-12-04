package interface_adapter.NutritionViewModel;

import entity.Ingredient;
import use_case.searchNutrition.NutritionInputData;
import use_case.searchNutrition.SearchNutritionInputBoundary;

public class NutritionController {
    private final SearchNutritionInputBoundary interactor;

    public NutritionController(SearchNutritionInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(Ingredient ingredient) {;
        interactor.getNutrition(ingredient);
    }

    public void switchToNutritionView() {
        interactor.switchToNutritionView();
    }

    public void switchToInitialView() {
        interactor.switchToInitialView();
    }
}
