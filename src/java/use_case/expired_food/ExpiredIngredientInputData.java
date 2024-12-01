package use_case.expired_food;

import java.time.LocalDate;

/**
 * Data structure to encapsulate input data for the expired ingredient use case.
 * Holds the name and expiry date of an ingredient.
 */
public class ExpiredIngredientInputData {
    private final String ingredientName;
    private final LocalDate expiryDate;

    /**
     * Constructs an ExpiredIngredientInputData object.
     *
     * @param ingredientName The name of the ingredient.
     * @param expiryDate The expiry date of the ingredient.
     */
    public ExpiredIngredientInputData(String ingredientName, LocalDate expiryDate) {
        this.ingredientName = ingredientName;
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the name of the ingredient.
     *
     * @return The ingredient name.
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Gets the expiry date of the ingredient.
     *
     * @return The expiry date.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
