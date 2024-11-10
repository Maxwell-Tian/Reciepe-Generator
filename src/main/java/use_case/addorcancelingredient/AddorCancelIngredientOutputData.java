package use_case.addorcancelingredient;

/**
 * Output Data for the add Ingredient Use Case.
 */
public class AddorCancelIngredientOutputData {

    private final String ingredientname;

    private final boolean useCaseFailed;

    public AddorCancelIngredientOutputData(String ingredientname, boolean useCaseFailed) {
        this.ingredientname = ingredientname;
        this.useCaseFailed = useCaseFailed;
    }

    public String getIngredientname() {
        return ingredientname;
    }

    public boolean isUseCaseFailed() { return useCaseFailed; }
}
