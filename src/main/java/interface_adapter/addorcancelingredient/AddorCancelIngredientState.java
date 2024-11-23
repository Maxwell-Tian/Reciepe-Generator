package interface_adapter.addorcancelingredient;

/**
 * The state for add or cancel ingredient view model.
 */
public class AddorCancelIngredientState {
    private String ingredientname = "";
    private String errorMessage;
    private String expirydate = "";

    public String getIngredientname() {return ingredientname;}

    public String getErrorMessage() {return errorMessage;}

    public String getExpirydate() {return expirydate;}

    public void setIngredientname(String ingredientname) {this.ingredientname = ingredientname;}

    public void setError(String errorMessage) {this.errorMessage = errorMessage;}

    public void setExpirydate(String expirydate) {this.expirydate = expirydate;}


    @Override
    public String toString() {
        return "AddorCancelIngredientState{"
                + "ingredientname='" + ingredientname + '\''
                + ", expirydate='" + expirydate + '\''
                + '}';
    }
}
