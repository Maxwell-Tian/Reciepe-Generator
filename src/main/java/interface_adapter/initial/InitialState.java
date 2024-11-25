package interface_adapter.initial;

import entity.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class InitialState {
    private List<Ingredient> ingredients;
    private List<String> ingredientNames;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        this.ingredients.add(ingredient);
    }

    public void deleteIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }

    public void setIngredients(List<Ingredient> ingredients) {this.ingredients = ingredients;}
}
