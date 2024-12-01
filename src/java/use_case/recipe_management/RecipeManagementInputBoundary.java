package use_case.recipe_management;

import entity.Recipe;

import java.util.List;

/**
 * The Recipe Management Use Case.
 */
public interface RecipeManagementInputBoundary {

//    /**
//     * Execute the Recipe Management Use Case.
//     * @param recipeManagementInputData the input data for this use case
//     */
//    List<Recipe> execute(RecipeManagementInputData recipeManagementInputData);

//    List<Recipe> getCurrentRecipes();

    void switchToInitialView();

    void switchToRecipeListView();

    void switchToRecipeInfoView();
}
