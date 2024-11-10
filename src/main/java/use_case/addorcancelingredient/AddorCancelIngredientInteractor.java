package use_case.addorcancelingredient;

import entity.Ingredient;
import entity.IngredientFactory;

import java.time.LocalDate;

/**
 * The add or cancel ingredient Interactor.
 */
public class AddorCancelIngredientInteractor implements AddorCancelIngredientInputBoundary{

    private final AddorCancelIngredientIngredientDataAccessInterface ingredientDataAccessObject;
    private final AddorCancelIngredientOutputBoundary ingredientPresenter;
    private final IngredientFactory ingredientFactory;

    public AddorCancelIngredientInteractor(AddorCancelIngredientIngredientDataAccessInterface ingredientDataAccessObject,
                                           AddorCancelIngredientOutputBoundary ingredientPresenter,
                                           IngredientFactory ingredientFactory) {
        this.ingredientDataAccessObject = ingredientDataAccessObject;
        this.ingredientPresenter = ingredientPresenter;
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void execute(AddorCancelIngredientInputData addorCancelIngredientInputData){
        if (ingredientDataAccessObject.existsByIngredientName(addorCancelIngredientInputData.getIngredientname())) {
            ingredientPresenter.prepareFailView("Ingredient already exists.");
        }
        else if (!addorCancelIngredientInputData.getExpirydate().isAfter(LocalDate.now())) {
            ingredientPresenter.prepareFailView("Expiry date has already passed.");
        }
        else{
            final Ingredient ingredient = ingredientFactory.create(addorCancelIngredientInputData.getIngredientname(), addorCancelIngredientInputData.getExpirydate());
            ingredientDataAccessObject.save(ingredient);

            final AddorCancelIngredientOutputData addorCancelIngredientOutputData = new AddorCancelIngredientOutputData(ingredient.getName(), false);
            ingredientPresenter.prepareSuccessView(addorCancelIngredientOutputData);
        }
    }

    @Override
    public void switchToInitialView() {
        ingredientPresenter.switchToInitialView();
    }
}
