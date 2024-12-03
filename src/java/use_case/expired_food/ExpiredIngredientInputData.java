package java.use_case.expired_food;

import java.time.LocalDate;

/**
 * The ExpiredIngredientInputData class encapsulates the data required for
 * the expiration warning use case, representing an ingredient's name and expiration date.
 */
public class ExpiredIngredientInputData {
    private final String ingredientName;
    private final LocalDate expiryDate;

    public ExpiredIngredientInputData(String ingredientName, LocalDate expiryDate) {
        this.ingredientName = ingredientName;
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the name of the ingredient.
     *
     * @return the ingredient's name
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Returns the expiration date of the ingredient.
     *
     * @return the ingredient's expiration date
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
