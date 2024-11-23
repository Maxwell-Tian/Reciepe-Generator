package use_case.addingredient;

import java.time.LocalDate;

public class AddIngredientOutputData {
    private final String ingredientName;
    private final LocalDate expireDate;

    public AddIngredientOutputData(String ingredientName, LocalDate expireDate) {
        this.ingredientName = ingredientName;
        this.expireDate = expireDate;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }
}
