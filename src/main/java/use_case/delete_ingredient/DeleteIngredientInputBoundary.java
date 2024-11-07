package use_case.delete_ingredient;

/**
 * The Delete Ingredient Use Case.
 */
public interface DeleteIngredientInputBoundary {

    /**
     * Execute the Delete Ingredient Use Case.
     * @param deleteIngredientInputData the input data for this use case
     */
    void execute(DeleteIngredientInputData deleteIngredientInputData);

}
