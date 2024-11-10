package use_case.addorcancelingredient;

import java.time.LocalDate;

/**
 * The Input Data for the add ingredient Use Case.
 */
public class AddorCancelIngredientInputData {

    private final String ingredientname;
    private final LocalDate expirydate;

    public AddorCancelIngredientInputData(String ingredientname, String expirydate) {
        this.ingredientname = ingredientname;
        this.expirydate = LocalDate.parse(expirydate);
    }

    String getIngredientname() { return ingredientname;}

    LocalDate getExpirydate() { return expirydate;}

}
