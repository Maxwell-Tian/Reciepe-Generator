package interface_adapter.deleteingredient;

import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialState;
import interface_adapter.initial.InitialViewModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientViewModel;
import interface_adapter.recipemanagement.RecipeManagementViewModel;
import use_case.delete_ingredient.DeleteIngredientOutputBoundary;
import use_case.delete_ingredient.DeleteIngredientOutputData;

/**
 * The Presenter for the Delete Ingredient Use Case.
 */
public class DeleteIngredientPresenter implements DeleteIngredientOutputBoundary {
    private final InitialViewModel viewModel;
    private final AddorCancelIngredientViewModel addorCancelIngredientViewModel;
    private final RecipeManagementViewModel recipeManagementViewModel;
    private final ViewManagerModel viewManagerModel;

    public DeleteIngredientPresenter(InitialViewModel viewModel,
                                     AddorCancelIngredientViewModel addorCancelIngredientViewModel,
                                     RecipeManagementViewModel recipeManagementViewModel,
                                     ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.addorCancelIngredientViewModel = addorCancelIngredientViewModel;
        this.recipeManagementViewModel = recipeManagementViewModel;
        this.viewManagerModel = viewManagerModel;
        this.recipeManagementViewModel = recipeManagementViewModel;
    }

    @Override
    public void prepareSuccessView(DeleteIngredientOutputData response){
        final InitialState initialState = viewModel.getState();
        initialState.deleteIngredient(response.getIngredient());
        this.viewModel.setState(initialState);
        this.viewModel.firePropertyChanged();

        this.viewManagerModel.setState(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage){
        // No such a case
    }

    @Override
    public void switchToAddIngredientView() {
        viewManagerModel.setState(addorCancelIngredientViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToRecipeView() {
        viewManagerModel.setState(recipeManagementViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
