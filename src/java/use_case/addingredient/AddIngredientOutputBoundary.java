package use_case.addingredient;

/**
 * Interface defining the output boundary for the Add Ingredient use case.
 * Responsible for presenting success or failure views based on the outcome of the operation.
 */
public interface AddIngredientOutputBoundary {
    /**
     * Prepares a failure view with an error message.
     *
     * @param errorMessage The error message to display.
     */
    void prepareFailView(String errorMessage);

    /**
     * Prepares a success view with output data.
     *
     * @param outputData The data to display in the success view.
     */
    void prepareSuccessView(AddIngredientOutputData outputData);
}
