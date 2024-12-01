package use_case.addingredient;

import java.time.LocalDate;

/**
 * Interface defining the input boundary for adding an ingredient use case.
 * Implementers of this interface should handle the execution logic for adding an ingredient.
 */

public interface AddIngredientInputBoundary {
    /**
     * Executes the process of adding an ingredient using the provided input data.
     *
     * @param addIngredientInputData The input data required for adding an ingredient.
     */
    void execute(AddIngredientInputData addIngredientInputData);
}
