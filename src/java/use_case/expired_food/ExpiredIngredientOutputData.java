package use_case.expired_food;

/**
 * Data structure to encapsulate output data for the expired ingredient use case.
 * Holds the name of an ingredient identified as expired.
 */
public class ExpiredIngredientOutputData {
    private final String ingredientName;

    /**
     * Constructs an ExpiredIngredientOutputData object.
     *
     * @param ingredientName The name of the expired ingredient.
     */
    public ExpiredIngredientOutputData(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    /**
     * Gets the name of the expired ingredient.
     *
     * @return The ingredient name.
     */
    public String getIngredientName() {
        return ingredientName;
    }
}
