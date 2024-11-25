package use_case.recipe_management;

import entity.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Integer> userIngredients = recipeManagementInputData.getUserIngredients();
        List<Recipe> allRecipes = recipeRepository.getCurrentRecipes();
        String category = recipeManagementInputData.getFilterCategory();

        List<Recipe> filteredRecipes = filterRecipesByCategory(allRecipes, category);
        Map<String, List<String>> recommendations = recommendRecipes(userIngredients, filteredRecipes);

        outputBoundary.presentRecipes(filteredRecipes);
        outputBoundary.presentSuccessMessage("Recommendations generated.");
    }

    private List<Recipe> filterRecipesByCategory(List<Recipe> recipes, String category) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getCategory().equals(category)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    private Map<String, List<String>> recommendRecipes(Map<String, Integer> userIngredients, List<Recipe> recipes) {
        List<String> fullyMatchedRecipes = new ArrayList<>();
        List<String> partiallyMatchedRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            Map<String, Integer> requiredIngredients = recipe.getRecipeMap();
            boolean fullyMatched = true;
            StringBuilder missingIngredients = new StringBuilder();

            for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
                String ingredientName = entry.getKey();
                int requiredQuantity = entry.getValue();
                int availableQuantity = userIngredients.getOrDefault(ingredientName, 0);

                if (availableQuantity < requiredQuantity) {
                    fullyMatched = false;
                    missingIngredients.append(String.format("%s (%d needed), ", ingredientName, requiredQuantity - availableQuantity));
                }
            }

            if (fullyMatched) {
                fullyMatchedRecipes.add(recipe.getName());
            } else {
                partiallyMatchedRecipes.add(recipe.getName() + " - Missing: " + missingIngredients.toString());
            }
        }

        Map<String, List<String>> recommendations = new HashMap<>();
        recommendations.put("Fully Matched", fullyMatchedRecipes);
        recommendations.put("Partially Matched", partiallyMatchedRecipes);
        return recommendations;
    }
}

