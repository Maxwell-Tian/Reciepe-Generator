package java.use_case.expired_food;

/**
 * The ExpiredIngredientOutputData class encapsulates data for presenting the
 * expiration warning use case results.
 */
public class ExpiredIngredientOutputData {
    private final String ingredientName;

    /**
     * Constructs an ExpiredIngredientOutputData object with the specified ingredient name.
     *
     * @param ingredientName the name of the ingredient
     */
    public ExpiredIngredientOutputData(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }
}
