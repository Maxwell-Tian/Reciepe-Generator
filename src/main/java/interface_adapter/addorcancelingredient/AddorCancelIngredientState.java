package interface_adapter.addorcancelingredient;

/**
 * The state for add or cancel ingredient view model.
 */
public class AddorCancelIngredientState {
    private String ingredientname = "";
    private String ingredientnameError;
    private String expirydate = "";
    private String expirydateError;

    private String getIngredientname() {return ingredientname;}

    private String getIngredientnameError() {return ingredientnameError;}

    private String getExpirydate() {return expirydate;}

    private String getExpirydateError() {return expirydateError;}

    public void setIngredientname(String ingredientname) {this.ingredientname = ingredientname;}

    public void setIngredientnameError(String ingredientnameError) {this.ingredientnameError = ingredientnameError;}

    public void setExpirydate(String expirydate) {this.expirydate = expirydate;}

    public void setExpirydateError(String expirydateError) {this.expirydateError = expirydateError;}

    @Override
    public String toString() {
        return "AddorCancelIngredientState{"
                + "ingredientname='" + ingredientname + '\''
                + ", expirydate='" + expirydate + '\''
                + '}';
    }
}
