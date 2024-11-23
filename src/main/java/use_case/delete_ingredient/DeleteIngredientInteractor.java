package use_case.delete_ingredient;


import entity.Ingredient;

import java.util.List;

/**
 * The Delete Ingredient Interactor.
 */
public class DeleteIngredientInteractor implements DeleteIngredientInputBoundary {
    private final DeleteIngredientIngredientDataAccessInterface ingredientDataAccessObject;
    private final DeleteIngredientOutputBoundary deleteIngredientPresenter;

    public DeleteIngredientInteractor(DeleteIngredientIngredientDataAccessInterface ingredientDataAccessObject,
                                      DeleteIngredientOutputBoundary deleteIngredientOutputBoundary) {
        this.ingredientDataAccessObject = ingredientDataAccessObject;
        this.deleteIngredientPresenter = deleteIngredientOutputBoundary;
    }

    @Override
    public void execute(DeleteIngredientInputData deleteIngredientInputData) {
        if (deleteIngredientInputData.getIngredient() == null) {
            deleteIngredientPresenter.prepareFailView("No ingredient selected");
        }
        final List<Ingredient> ingredients = deleteIngredientInputData.getIngredients();
        final Ingredient ingredient = deleteIngredientInputData.getIngredient();
        ingredients.remove(ingredient);
        ingredientDataAccessObject.setCurrentIngredients(ingredients);
        final DeleteIngredientOutputData deleteIngredientOutputData = new DeleteIngredientOutputData(ingredients,
                ingredient, false);
        deleteIngredientPresenter.prepareSuccessView(deleteIngredientOutputData);
    }

    @Override
    public void switchToAddIngredientView() {
       deleteIngredientPresenter.switchToAddIngredientView();
    }

    @Override
    public void switchToRecipeView() {
        deleteIngredientPresenter.switchToRecipeView();
    }
}
