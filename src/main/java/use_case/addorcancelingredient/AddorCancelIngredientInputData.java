package use_case.addorcancelingredient;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The Input Data for the add ingredient Use Case.
 */
public class AddorCancelIngredientInputData {

    private final String ingredientname;
    private LocalDate expirydate;
    private boolean valid = true;

    public AddorCancelIngredientInputData(String ingredientname, String expirydate) {
        this.ingredientname = ingredientname;
        try {this.expirydate = LocalDate.parse(expirydate);
        } catch (DateTimeParseException e) {
            this.valid = false;
            this.expirydate = LocalDate.parse("9999-12-31");
        }
    }

    boolean getValid() { return this.valid;}

    String getIngredientname() { return ingredientname;}

    LocalDate getExpirydate() { return expirydate;}

}
