package use_case.addorcancelingredient;

import entity.Ingredient;
import entity.IngredientFactory;

import javax.swing.*;
import java.time.LocalDate;

/**
 * The add or cancel ingredient Interactor.
 */
public class AddorCancelIngredientInteractor implements AddorCancelIngredientInputBoundary{

    public final AddorCancelIngredientIngredientDataAccessInterface ingredientDataAccessObject;
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
        else if (!addorCancelIngredientInputData.getValid()) {
            ingredientPresenter.prepareFailView("Invalid date.");
        }
        else if (!addorCancelIngredientInputData.getExpirydate().isAfter(LocalDate.now())) {
            ingredientPresenter.prepareFailView("Expiry date has already passed.");
        }
        else{
            final Ingredient ingredient = ingredientFactory.create(addorCancelIngredientInputData.getIngredientname(), addorCancelIngredientInputData.getExpirydate());
            ingredientDataAccessObject.save(ingredient);

            final AddorCancelIngredientOutputData addorCancelIngredientOutputData = new AddorCancelIngredientOutputData(ingredient, false);
            ingredientPresenter.prepareSuccessView(addorCancelIngredientOutputData);
          
            ingredientPresenter.switchToInitialView();
        }
    }

    @Override
    public void switchToInitialView() {
        ingredientPresenter.switchToInitialView();
    }
}

