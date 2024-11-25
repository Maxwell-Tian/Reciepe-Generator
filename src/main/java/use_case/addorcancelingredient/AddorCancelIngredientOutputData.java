package use_case.addorcancelingredient;

import entity.Ingredient;
import entity.IngredientFactory;

import java.time.LocalDate;

/**
 * Output Data for the add Ingredient Use Case.
 */
public class AddorCancelIngredientOutputData {

    private final Ingredient ingredient;
    private final String ingredientname;

    private final boolean useCaseFailed;

    public AddorCancelIngredientOutputData(Ingredient ingredient, boolean useCaseFailed) {
        this.ingredient = ingredient;
        this.ingredientname = ingredient.getName();
        this.useCaseFailed = useCaseFailed;
    }

    public Ingredient getIngredient() {return ingredient;}

    public String getIngredientname() {
        return ingredientname;
    }

    public boolean isUseCaseFailed() { return useCaseFailed; }
}
