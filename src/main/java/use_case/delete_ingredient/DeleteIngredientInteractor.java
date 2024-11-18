package use_case.delete_ingredient;


import entity.Ingredient;

import java.util.List;

/**
 * The Delete Ingredient Interactor.
 */
public class DeleteIngredientInteractor implements DeleteIngredientInputBoundary {
    private final DeleteIngredientUserDataAccessInterface userDataAccessObject;
    private final DeleteIngredientOutputBoundary deleteIngredientPresenter;

    public DeleteIngredientInteractor(DeleteIngredientUserDataAccessInterface userDataAccessInterface,
                                      DeleteIngredientOutputBoundary deleteIngredientOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.deleteIngredientPresenter = deleteIngredientOutputBoundary;
    }

    @Override
    public void execute(DeleteIngredientInputData deleteIngredientInputData) {
        final List<Ingredient> ingredients = deleteIngredientInputData.getIngredients();
        final Ingredient ingredient = deleteIngredientInputData.getIngredient();
        ingredients.remove(ingredient);
        userDataAccessObject.setCurrentIngredients(ingredients);
        final DeleteIngredientOutputData deleteIngredientOutputData = new DeleteIngredientOutputData(ingredients,
                ingredient, false);
        // * tell the presenter to prepare a success view.
        deleteIngredientPresenter.prepareSuccessView(deleteIngredientOutputData);
    }

    @Override
    public void switchToAddIngredientView() {
       //
    }

    @Override
    public void switchToRecipeView() {
        //
    }
}
