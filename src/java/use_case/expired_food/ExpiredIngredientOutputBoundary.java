package java.use_case.expired_food;

public interface ExpiredIngredientOutputBoundary {
    void execute(ExpiredIngredientInputData inputData);
    void prepareFailView(String error);
}
