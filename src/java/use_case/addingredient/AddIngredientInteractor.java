package use_case.addingredient;

import entity.IngredientFactory;

/**
 * Interactor for the Add Ingredient use case. Implements the input boundary
 * and handles the business logic for adding ingredients to a user's collection.
 */
public class AddIngredientInteractor implements AddIngredientInputBoundary {
    public final AddIngredientUserDataAccessInterface userIngredientAccessObject;
    public final IngredientFactory ingredientFactory;
    public final AddIngredientOutputBoundary ingredientPresenter;

    /**
     * Constructs an AddIngredientInteractor.
     *
     * @param userDataAccessObject The data access object for user ingredients.
     * @param ingredientPresenter The output boundary to handle success or failure views.
     * @param ingredientFactory The factory for creating Ingredient objects.
     */
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
        }
//        else {
//            final Ingredient ingredient = ingredientFactory.create(addIngredientInputData.getIngredientName(),
//                    addIngredientInputData.getExpireDate());
//            userIngredientAccessObject.addIngredientToUser(addIngredientInputData.getUser(), ingredient);
//        }
//
//        AddIngredientOutputData outputData = new AddIngredientOutputData(Ingredient.getName(), false);
//        ingredientPresenter.prepareSuccessView(outputData);
    }
}


