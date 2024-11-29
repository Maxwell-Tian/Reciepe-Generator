package use_case.addingredient;

import entity.Ingredient;

public interface AddIngredientUserDataAccessInterface {
    void addIngredientToUser(String userId, Ingredient ingredient);
}