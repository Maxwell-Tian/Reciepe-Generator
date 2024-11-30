package java.use_case.expired_food;

import entity.Ingredient;

import java.util.List;

public interface CheckExpiredIngredientUserDataAccessInterface {
    List<Ingredient> getAllIngredients();
    boolean existsByIngredientName(String ingredientName);
    void setIngredients(List<Ingredient> currentIngredients);
}