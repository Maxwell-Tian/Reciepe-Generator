package use_case.recipe_management;

import entity.Ingredient;
import entity.Recipe;

import java.util.List;

public interface RecipeManagementUserDataAccessInterface {

    /**
     * Returns the recipes of the current user of the application.
     * @return the recipes of the current user; empty list indicates that current user has not added any recipe.
     */
    List<Recipe> getCurrentRecipes();

    /**
     * Save the recipes of the current user of the application.
     * Save the recipes of the current user; empty list indicates that current user has not added any recipe.
     */
    void saveRecipe(Recipe recipe);
}