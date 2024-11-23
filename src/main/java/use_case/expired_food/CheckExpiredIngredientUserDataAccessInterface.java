package use_case.expired_food;

import entity.Ingredient;

import java.util.List;

public interface CheckExpiredIngredientUserDataAccessInterface {
    List<Ingredient> getAllIngredients();
}
