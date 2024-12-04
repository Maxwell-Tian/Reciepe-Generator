package interface_adapter.NutritionViewModel;

import entity.Ingredient;

/**
    hold info for the nutrition of current ingredient
 **/
public class NutritionInfoState {
    private Ingredient ingredient;

    public Ingredient getIngredient() {
        return ingredient;
    }


    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
