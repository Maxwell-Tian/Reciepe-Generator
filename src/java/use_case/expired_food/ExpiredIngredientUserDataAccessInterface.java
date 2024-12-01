package use_case.expired_food;

import entity.Ingredient;

import java.util.List;

public interface ExpiredIngredientUserDataAccessInterface {

    List<Ingredient> getCurrentIngredients();
    boolean existsByIngredientName(String ingredientName);
//    void setIngredients(List<Ingredient> currentIngredients);
}
