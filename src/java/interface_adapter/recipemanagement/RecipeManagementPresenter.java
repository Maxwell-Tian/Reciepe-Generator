package interface_adapter.recipemanagement;

import entity.Recipe;
import use_case.recipe_management.RecipeManagementOutputBoundary;

import java.util.List;
import java.util.Map;

public class RecipeManagementPresenter implements RecipeManagementOutputBoundary {

    @Override
    public void presentRecipes(List<Recipe> recipes) {
        System.out.println("Generated Recipes:");
        for (Recipe recipe : recipes) {
            System.out.println(recipe.getName());
        }
    }

    @Override
    public void presentSuccessMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void presentErrorMessage(String errorMessage) {

    }

    @Override
    public void presentRecommendations(Map<String, List<String>> recommendations) {

    }
}
