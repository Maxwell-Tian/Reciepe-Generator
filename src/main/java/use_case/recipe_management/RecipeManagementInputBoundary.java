package use_case.recipe_management;

/**
 * The Recipe Management User Case.
 */
public interface RecipeManagementInputBoundary {

    /**
     * Execute the Recipe Management Use Case.
     * @param RecipeManagementInputData the input data for this use case
     */
    void execute(RecipeManagementInputData RecipeManagementInputData);

}
