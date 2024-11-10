package use_case.recipe_management;

import entity.Recipe;
import use_case.recipe_management.RecipeManagementUserDataAccessInterface;
import use_case.recipe_management.RecipeManagementOutputBoundary;
import use_case.recipe_management.RecipeManagementInputBoundary;

import java.util.ArrayList;
import java.util.List;

public class RecipeManagementInteractor implements RecipeManagementInputBoundary {
    private final RecipeManagementUserDataAccessInterface recipeRepository;
    private final RecipeManagementOutputBoundary outputBoundary;

    public RecipeManagementInteractor(RecipeManagementUserDataAccessInterface recipeRepository, RecipeManagementOutputBoundary outputBoundary) {
        this.recipeRepository = recipeRepository;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Execute the Recipe Management Use Case.
     *
     * @param recipeManagementInputData the input data for this use case
     */
    public void execute(RecipeManagementInputData recipeManagementInputData) {
        final String category = recipeManagementInputData.getFilterCategory();
        final List<Recipe> allRecipes = recipeRepository.getCurrentRecipes();

        for (Recipe recipe : allRecipes) {
            if (recipe.getCategory().equals(category)) {
                final String recipeName = recipe.getName();
                outputBoundary.presentErrorMessage("recipeName: " + recipeName + "is your filter recipe");
            }
            else if (recipe.getName() != null && !recipe.getName().isEmpty()) {
                recipeRepository.saveRecipe(recipe);
                outputBoundary.presentSuccessMessage("Recipe added successfully.");
            }
            else {
                outputBoundary.presentErrorMessage("Invalid recipe data.");
            }
        }
    }
}

