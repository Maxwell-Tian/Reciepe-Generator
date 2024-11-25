package use_case.recipe_management;

/**
 * The Recipe Management Use Case.
 */
public interface RecipeManagementInputBoundary {

    /**
     * Execute the Recipe Management Use Case.
     * @param recipeManagementInputData the input data for this use case
     */
    void execute(RecipeManagementInputData recipeManagementInputData);

}
