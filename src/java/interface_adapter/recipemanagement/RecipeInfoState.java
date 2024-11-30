package interface_adapter.recipemanagement;

import entity.Recipe;

/**
 * State for the RecipeInfoViewModel.
 * Holds the data for the current recipe being displayed.
 */
public class RecipeInfoState {
    private Recipe currentRecipe;

    /**
     * Gets the current recipe.
     * @return the current recipe
     */
    public Recipe getCurrentRecipe() {
        return currentRecipe;
    }

    /**
     * Sets the current recipe.
     * @param currentRecipe the recipe to set
     */
    public void setCurrentRecipe(Recipe currentRecipe) {
        this.currentRecipe = currentRecipe;
    }
}
