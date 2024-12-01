package interface_adapter.ExpirationWarning;
import entity.Ingredient;
import use_case.expired_food.ExpiredIngredientInteractor;

import java.util.List;

public class ExpirationWarningController {
    private final ExpiredIngredientInteractor interactor;

    public ExpirationWarningController(ExpiredIngredientInteractor interactor) {
        if (interactor == null) {
            throw new IllegalArgumentException("Interactor cannot be null");
        }
        this.interactor = interactor;
    }

    public List<Ingredient> getExpiredIngredients() {
        return interactor.execute();
    }

    public List<Ingredient> execute() {
        return interactor.execute();
    }

    public void deleteSelectedIngredients(Ingredient ingredient) {
        interactor.deleteIngredients(ingredient);
    }

    public void switchToInitialView() {
        interactor.switchToInitialView();
    }
}

