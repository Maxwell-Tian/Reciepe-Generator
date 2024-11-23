package interface_adapter.deleteingredient;

import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialState;
import interface_adapter.initial.InitialViewModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientViewModel;
import use_case.delete_ingredient.DeleteIngredientOutputBoundary;
import use_case.delete_ingredient.DeleteIngredientOutputData;

/**
 * The Presenter for the Delete Ingredient Use Case.
 */
public class DeleteIngredientPresenter implements DeleteIngredientOutputBoundary {
    private final InitialViewModel viewModel;
    private final AddorCancelIngredientViewModel addorCancelIngredientViewModel;
    private final ViewManagerModel viewManagerModel;

    public DeleteIngredientPresenter(InitialViewModel viewModel,
                                     AddorCancelIngredientViewModel addorCancelIngredientViewModel,
                                     ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.addorCancelIngredientViewModel = addorCancelIngredientViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DeleteIngredientOutputData response){
        final InitialState initialState = viewModel.getState();
        initialState.setIngredients(response.getIngredients());
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
        // waiting for recipe interface_adapter
    }
}
