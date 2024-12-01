package use_case.recipe_management;

import entity.Recipe;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RecipeManagementUserDataAccessInterface {

    /**
     * Returns the recipes of the current user of the application.
     * @return the recipes of the current user; empty list indicates that current user has not added any recipe.
     */
    List<Recipe> getCurrentRecipes();

    /**
     * Save the recipes of the current user of the application.
     * @param recipe the recipe to save
     */
    void saveRecipe(Recipe recipe);

    /**
     * Save the user's ingredient data.
     * @param userIngredients a map of ingredient names and their quantities
     */
    void saveUserIngredients(Map<String, LocalDate> userIngredients);

    /**
     * Get the user's ingredient data.
     * @return a map of ingredient names and their quantities
     */
    Map<String, LocalDate> getUserIngredients();
}
