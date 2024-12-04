package use_case.searchNutrition;


import api.NutritionRequest;
import entity.Ingredient;
import interface_adapter.NutritionViewModel.NutritionInfoState;

public class NutritionInteractor implements SearchNutritionInputBoundary{
    private final NutritionOutputBoundary outputBoundary;

    public NutritionInteractor(NutritionOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void getNutrition(Ingredient ingredient) {
        NutritionRequest nutritionRequest = new NutritionRequest(ingredient);
        outputBoundary.presentNutrition(nutritionRequest.get_nutrition());
        switchToNutritionView();
    }

    @Override
    public void switchToNutritionView() {
        outputBoundary.switchNutritionView();
    }

    @Override
    public void switchToInitialView() {
        outputBoundary.switchToInitialView();
    }
}
