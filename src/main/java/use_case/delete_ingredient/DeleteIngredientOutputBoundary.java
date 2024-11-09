package use_case.delete_ingredient;

/**
 * The output boundary for the Delete Ingredient Use Case.
 */
public interface DeleteIngredientOutputBoundary {
    /**
     * Prepares the success view for the Delete Ingredient Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(DeleteIngredientOutputData outputData);

    /**
     * Prepares the failure view for the Delete Ingredient Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
