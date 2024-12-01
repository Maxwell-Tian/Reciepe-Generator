package use_case.expired_food;

import entity.Ingredient;

import java.io.FileNotFoundException;
import java.util.List;

public interface ExpiredIngredientUserDataAccessInterface {

    List<Ingredient> getCurrentIngredients() throws FileNotFoundException;
//    boolean existsByIngredientName(String ingredientName);
    void deleteIngredient(Ingredient ingredient);
}
