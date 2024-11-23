package use_case.addingredient;

import java.time.LocalDate;

public class AddIngredientInputData {
    private final String ingredientName;
    private final LocalDate expireDate;

    public AddIngredientInputData(String ingredientName, LocalDate expireDate) {
        this.ingredientName = ingredientName;
        this.expireDate = expireDate;
    }

    String getIngredientName() { return ingredientName; }
    LocalDate getExpireDate() { return expireDate; }
}
