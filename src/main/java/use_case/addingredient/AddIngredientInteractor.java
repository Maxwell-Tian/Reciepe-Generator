package use_case.addingredient;

import entity.Ingredient;
import entity.IngredientFactory;
import entity.User;
import java.time.LocalDate;

public class AddIngredientInteractor implements AddIngredientInputBoundary {
    public final AddIngredientUserDataAccessInterface userIngredientAccessObject;
    public final IngredientFactory ingredientFactory;
    public final AddIngredientOutputBoundary ingredientPresenter;

    public AddIngredientInteractor(AddIngredientUserDataAccessInterface userDataAccessObject,
                                   AddIngredientOutputBoundary ingredientPresenter,
                                   IngredientFactory ingredientFactory) {
        this.userIngredientAccessObject = userDataAccessObject;
        this.ingredientPresenter = ingredientPresenter;
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void execute(AddIngredientInputData addIngredientInputData) {
        if (addIngredientInputData.getIngredientName() == null) {
            ingredientPresenter.prepareFailView("Ingredient name cannot be empty.");
        } else if (addIngredientInputData.getExpireDate() == null) {
            ingredientPresenter.prepareFailView("Expiry date is not provided.");
        } else {
            final Ingredient ingredient = ingredientFactory.create(addIngredientInputData.getIngredientName(),
                    addIngredientInputData.getExpireDate());
            userIngredientAccessObject.addIngredientToUser(addIngredientInputData.getUser(), ingredient);
        }
    }
}


