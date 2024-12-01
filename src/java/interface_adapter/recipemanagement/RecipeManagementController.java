package interface_adapter.recipemanagement;

import entity.Recipe;
import use_case.recipe_management.RecipeManagementInputBoundary;
import use_case.recipe_management.RecipeManagementInputData;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class RecipeManagementController {
    private final RecipeManagementInputBoundary interactor;

    public RecipeManagementController(RecipeManagementInputBoundary interactor) {
        this.interactor = interactor;
    }

//    public void execute(Map<String, LocalDate> userIngredients, String filterCategory) {
//        if (userIngredients == null || userIngredients.isEmpty()) {
//            throw new IllegalArgumentException("User ingredients cannot be null or empty");
//        }
//        RecipeManagementInputData inputData = new RecipeManagementInputData(filterCategory, userIngredients);
//        interactor.execute(inputData);
//    }

//    public List<Recipe> getCurrentRecipes() {
//        return interactor.getCurrentRecipes();
//    }

    /**
     * Executes the "switch to InitialView" Use Case.
     */
    public void switchToInitialView() {
        interactor.switchToInitialView();
    }

    /**
     * switch to recipelist
     */
    public void switchToRecipeListView() {interactor.switchToRecipeListView(); }

    public void switchToRecipeInfoView() {
        interactor.switchToRecipeInfoView();
    }
}
