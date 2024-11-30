package interface_adapter.initial;

import data.txtConnector;
import entity.Ingredient;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class InitialState {
    private List<Ingredient> ingredients = new ArrayList<>();
    private final txtConnector ingredientDataAccessObject = new txtConnector();
    private String errorMessage;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void populateIngredients() throws FileNotFoundException {
        List<Ingredient> existingIngredients = ingredientDataAccessObject.getCurrentIngredients();
        for (Ingredient ingredient: existingIngredients) {
            ingredients.add(ingredient);
        }
    }

    public String getErrorMessage() {return errorMessage;}

    public void setError(String errorMessage) {this.errorMessage = errorMessage;}

    public void deleteIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void setIngredients(List<Ingredient> ingredients) {this.ingredients = ingredients;}
}
