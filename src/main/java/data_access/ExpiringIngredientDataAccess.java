package data_access;

import entity.Ingredient;
import use_case.expired_food.CheckExpiredIngredientUserDataAccessInterface;

import java.util.List;

public class ExpiringIngredientDataAccess extends InMemoryIngredientDataAccessObject
        implements CheckExpiredIngredientUserDataAccessInterface {

    @Override
    public List<Ingredient> getAllIngredients() {
        return super.getCurrentIngredients();
    }

    @Override
    public void setIngredients(List<Ingredient> currentIngredients) {
        super.setCurrentIngredients(currentIngredients);
    }
}
