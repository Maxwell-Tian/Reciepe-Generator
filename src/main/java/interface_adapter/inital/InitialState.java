package interface_adapter.inital;

import entity.Ingredient;

import java.util.List;

public class InitialState {
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
