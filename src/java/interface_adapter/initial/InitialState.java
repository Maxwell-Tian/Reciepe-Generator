package interface_adapter.initial;

import data.txtConnector;
import entity.Ingredient;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class InitialState {
    private List<Ingredient> ingredients = new ArrayList<>();
//    private List<String> ingredientNames;
    private final txtConnector ingredientDataAccessObject = new txtConnector();

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        this.ingredients.add(ingredient);
    }

    public void populateIngredients() throws FileNotFoundException {
        List<Ingredient> existingIngredients = ingredientDataAccessObject.getCurrentIngredients();
        for (Ingredient ingredient: existingIngredients) {
            ingredients.add(ingredient);
        }
    }

    public void deleteIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }

    public void setIngredients(List<Ingredient> ingredients) {this.ingredients = ingredients;}
}
