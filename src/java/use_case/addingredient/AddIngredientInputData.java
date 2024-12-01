package use_case.addingredient;

import java.time.LocalDate;

/**
 * Data structure to encapsulate the input data for the Add Ingredient use case.
 * Holds the name of the ingredient and its expiration date.
 */
public class AddIngredientInputData {
    private final String ingredientName;
    private final LocalDate expireDate;

    /**
     * Constructs an AddIngredientInputData object.
     *
     * @param ingredientName The name of the ingredient to be added.
     * @param expireDate The expiration date of the ingredient.
     */
    public AddIngredientInputData(String ingredientName, LocalDate expireDate) {
        this.ingredientName = ingredientName;
        this.expireDate = expireDate;
    }

    /**
     * Gets the name of the ingredient.
     *
     * @return The ingredient name.
     */
    String getIngredientName() { return ingredientName; }

    /**
     * Gets the expiration date of the ingredient.
     *
     * @return The expiration date.
     */
    LocalDate getExpireDate() { return expireDate; }
}
