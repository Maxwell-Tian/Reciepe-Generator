package use_case.recipe_management;

import data_access.InMemoryRecipeDataAccessObject;
import data_access.InMemoryRecipeManagementRepository;
import entity.Recipe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeManagementInteractor implements RecipeManagementInputBoundary {
    //    private InMemoryRecipeManagementRepository repository;
    private final InMemoryRecipeDataAccessObject recipeDataAccessObject;
    //    private List<Recipe> recipeRepository = repository.getCurrentRecipes();
    private final RecipeManagementOutputBoundary outputBoundary;

    public RecipeManagementInteractor(InMemoryRecipeDataAccessObject recipeDataAccessObject, RecipeManagementOutputBoundary outputBoundary) {
        this.recipeDataAccessObject = recipeDataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Execute the Recipe Management Use Case.
     *
     * @param recipeManagementInputData the input data for this use case
     * @return
     */
    public List<Recipe> execute(RecipeManagementInputData recipeManagementInputData) {
        Map<String, LocalDate> userIngredients = recipeManagementInputData.getUserIngredients();
        recipeDataAccessObject.populateAllRecipes();
        List<Recipe> allRecipes = recipeDataAccessObject.getCurrentRecipes();
        String category = recipeManagementInputData.getFilterCategory();

        List<Recipe> filteredRecipes = filterRecipesByCategory(allRecipes, category);
        Map<String, List<String>> recommendations = recommendRecipes(userIngredients, filteredRecipes);

        outputBoundary.presentRecipes(filteredRecipes);
        outputBoundary.presentSuccessMessage("Recommendations generated.");
        return allRecipes;
    }

    @Override
    public List<Recipe> getCurrentRecipes() {
        return List.of();
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

    private Map<String, List<String>> recommendRecipes(Map<String, LocalDate> userIngredients, List<Recipe> recipes) {
        List<String> fullyMatchedRecipes = new ArrayList<>();
        List<String> partiallyMatchedRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            Map<String, Integer> requiredIngredients = recipe.getRecipeMap();
            boolean fullyMatched = true;
            StringBuilder missingIngredients = new StringBuilder();

            for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
                String ingredientName = entry.getKey();
                LocalDate currenttime = LocalDate.ofEpochDay(entry.getValue());
                LocalDate expiredate = userIngredients.getOrDefault(ingredientName, LocalDate.ofEpochDay(0));

                if (expiredate.isBefore(currenttime)) {
                    fullyMatched = false;
                    missingIngredients.append(String.format("%s (%d food has expired), "));
                }
            }

            if (fullyMatched) {
                fullyMatchedRecipes.add(recipe.getName());
            } else {
                partiallyMatchedRecipes.add(recipe.getName() + " - food has expired ");
            }
        }

        Map<String, List<String>> recommendations = new HashMap<>();
        recommendations.put("Fully Matched", fullyMatchedRecipes);
        recommendations.put("Partially Matched", partiallyMatchedRecipes);
        return recommendations;
    }

    @Override
    public void switchToInitialView() {
        outputBoundary.switchToInitialView();
    }

    @Override
    public void switchToRecipeListView() { outputBoundary.switchToRecipeListView(); }
}
