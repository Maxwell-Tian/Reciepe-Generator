package use_case.addorcancelingredient;

import java.io.FileNotFoundException;

public interface AddorCancelIngredientOutputBoundary {

//    /**
//     * Prepares the success view for the add ingredient Use Case.
//     * @param outputData the output data
//     */
//    void prepareSuccessView(AddorCancelIngredientOutputData outputData);

    void prepareSuccessView(AddorCancelIngredientOutputData response) throws FileNotFoundException;

    /**
     * Prepares the failure view for the add ingredient Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the initial View.
     */
    void switchToInitialView();
}
