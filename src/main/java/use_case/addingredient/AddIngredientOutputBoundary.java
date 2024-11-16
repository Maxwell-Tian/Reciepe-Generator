package use_case.addingredient;

public interface AddIngredientOutputBoundary {
    void prepareFailView(String errorMessage);

    void prepareSuccessView(AddIngredientOutputData outputData);
}
