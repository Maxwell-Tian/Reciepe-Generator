package use_case.addingredient;

import java.time.LocalDate;

public interface AddIngredientInputBoundary {
    void addIngredient(String ingredientName, LocalDate expireDate);
}
