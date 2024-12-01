package use_case.addingredient;

import java.time.LocalDate;

/**
 * Data structure to encapsulate the output data for the Add Ingredient use case.
 * Holds the name and expiration date of the successfully added ingredient.
 */
public class AddIngredientOutputData {
    private final String ingredientName;
    private final LocalDate expireDate;

    public AddIngredientOutputData(String ingredientName, LocalDate expireDate) {
        this.ingredientName = ingredientName;
        this.expireDate = expireDate;
    }

    /**
     * Gets the name of the added ingredient.
     *
     * @return The ingredient name.
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Gets the expiration date of the added ingredient.
     *
     * @return The expiration date.
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }
}
