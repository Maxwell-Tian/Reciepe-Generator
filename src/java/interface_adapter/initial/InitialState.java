package interface_adapter.initial;

import entity.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class InitialState {
    private List<Ingredient> ingredients;
    private String errorMessage;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getErrorMessage() {return errorMessage;}

    public void setError(String errorMessage) {this.errorMessage = errorMessage;}

    public void deleteIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
