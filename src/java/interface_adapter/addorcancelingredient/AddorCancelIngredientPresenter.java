package interface_adapter.addorcancelingredient;

import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialState;
import interface_adapter.initial.InitialViewModel;
import interface_adapter.recipemanagement.RecipeManagementState;
import interface_adapter.recipemanagement.RecipeManagementViewModel;
import use_case.addorcancelingredient.AddorCancelIngredientOutputBoundary;
import use_case.addorcancelingredient.AddorCancelIngredientOutputData;

import java.io.FileNotFoundException;

/**
 * Presenter for the add or cancel ingredient use case.
 */
public class AddorCancelIngredientPresenter implements AddorCancelIngredientOutputBoundary {

    private final AddorCancelIngredientViewModel addorCancelIngredientViewModel;
    private final InitialViewModel initialViewModel;
    private final RecipeManagementViewModel recipeManagementViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddorCancelIngredientPresenter(AddorCancelIngredientViewModel addorCancelIngredientViewModel, InitialViewModel initialViewModel, RecipeManagementViewModel recipeManagementViewModel, ViewManagerModel viewManagerModel) {
        this.addorCancelIngredientViewModel = addorCancelIngredientViewModel;
        this.initialViewModel = initialViewModel;
        this.recipeManagementViewModel = recipeManagementViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddorCancelIngredientOutputData response) throws FileNotFoundException {
        final InitialState initialState = initialViewModel.getState();
        initialState.addIngredient(response.getIngredient());
        this.initialViewModel.setState(initialState);
        initialViewModel.firePropertyChanged();
        final RecipeManagementState recipeManagementState = recipeManagementViewModel.getState();
        recipeManagementState.regenerateList();
        this.recipeManagementViewModel.setState(recipeManagementState);
        recipeManagementViewModel.firePropertyChanged();

        viewManagerModel.setState(initialViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final AddorCancelIngredientState addorCancelIngredientState = addorCancelIngredientViewModel.getState();
        addorCancelIngredientState.setError(error);
        addorCancelIngredientViewModel.firePropertyChanged();
    }

    @Override
    public void switchToInitialView() {
        viewManagerModel.setState(initialViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
