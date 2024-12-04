package use_case.searchNutrition;

import entity.Ingredient;

public class NutritionInputData {
    private Ingredient ingredient;

    public NutritionInputData(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
