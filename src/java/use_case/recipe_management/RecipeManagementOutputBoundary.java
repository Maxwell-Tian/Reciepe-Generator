package use_case.recipe_management;

import entity.Recipe;

import java.util.List;
import java.util.Map;

/**
 * The output boundary for the Recipe Management Use Case.
 */
public interface RecipeManagementOutputBoundary {

    /**
     * Prepares the view for the filtered recipes.
     * @param filteredRecipes the filtered recipes
     */
    void presentRecipes(List<Recipe> filteredRecipes);

    /**
     * Prepares the view for success messages.
     * @param successMessage the success message
     */
    void presentSuccessMessage(String successMessage);

    /**
     * Prepares the view for error messages.
     * @param errorMessage the error message
     */
    void presentErrorMessage(String errorMessage);

    /**
     * Prepares the view for recipe recommendations.
     * @param recommendations the recommended recipes
     */
    void presentRecommendations(Map<String, List<String>> recommendations);

    /**
     * Switch to initial view
     */
    void switchToInitialView();

    /**
     * switch to recipe list view
     */
    void switchToRecipeListView();

    /**
     * switch to recipe info view
     */
    void switchToRecipeInfoView();
}
