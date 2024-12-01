package use_case.expired_food;

/**
 * Interface defining the output boundary for the expired ingredient use case.
 * Responsible for preparing success or failure views based on the operation outcome.
 */
public interface ExpiredIngredientOutputBoundary {
    /**
     * Executes the presentation logic using the provided input data.
     *
     * @param inputData The input data to process.
     */
    void execute(ExpiredIngredientInputData inputData);

    /**
     * Prepares a failure view with the specified error message.
     *
     * @param error The error message to display.
     */
    void prepareFailView(String error);
}
