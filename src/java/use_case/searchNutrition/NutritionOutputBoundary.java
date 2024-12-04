package use_case.searchNutrition;

import entity.Recipe;

import java.util.List;

public interface NutritionOutputBoundary {

    void switchToInitialView();

    void presentErrorMessage(String errorMessage);

    void presentNutrition(List<String> NutritionInfo);

    void presentSuccessMessage(String successMessage);

    void switchNutritionView();
}
