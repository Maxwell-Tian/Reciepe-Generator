package use_case.addorcancelingredient;

/**
 * Input Boundary for actions which are related to add ingredient.
 */
public interface AddorCancelIngredientInputBoundary {
    /**
     * Executes the add ingredient use case.
     * @param addorCancelIngredientInputData the input data
     */
    void execute(AddorCancelIngredientInputData addorCancelIngredientInputData);

    /**
     * Executes the switch to initial view use case.
     */
    void switchToInitialView();
}
