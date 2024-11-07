package use_case.delete_ingredient;

import entity.Ingredient;
import java.util.ArrayList;
import java.util.List;

/**
 * The input data for the Delete Ingredient Use Case.
 */
public class DeleteIngredientInputData {

    private final List<Ingredient> ingredients;
    private final Ingredient ingredient;

    public DeleteIngredientInputData(List<Ingredient> ingredients, Ingredient ingredient) {
        this.ingredients = ingredients;
        this.ingredient = ingredient;
    }

    List<Ingredient> getIngredients() {
        return ingredients;
    }

    Ingredient getIngredient() {
        return ingredient;
    }

}
