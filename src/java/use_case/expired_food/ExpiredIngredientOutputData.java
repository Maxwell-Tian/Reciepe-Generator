package java.use_case.expired_food;

public class ExpiredIngredientOutputData {
    private final String ingredientName;

    public ExpiredIngredientOutputData(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }
}
