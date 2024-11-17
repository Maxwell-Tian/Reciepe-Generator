package use_case.recipe_management;

import entity.Recipe;

import java.util.List;

/**
 * The output boundary for the Recipe Management Use Case.
 */
public interface RecipeManagementOutputBoundary {
    /**
     * Prepares the success view for the Delete Ingredient Use Case.
     * @param filteredRecipes the output data
     */
    void presentRecipes(List<Recipe> filteredRecipes);

    /**
     * Prepares the success view for the Delete Ingredient Use Case.
     * @param successMessage the output data
     */
    void presentSuccessMessage(String successMessage);

    /**
     * Prepares the success view for the Delete Ingredient Use Case.
     * @param errorMessage the output data
     */
    void presentErrorMessage(String errorMessage);
}