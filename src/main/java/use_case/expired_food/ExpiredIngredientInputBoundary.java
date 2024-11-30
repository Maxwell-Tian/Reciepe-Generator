package use_case.expired_food;

public interface ExpiredIngredientInputBoundary {
    void execute(ExpiredIngredientInputData inputData);
    void prepareFailView(String error);
}
