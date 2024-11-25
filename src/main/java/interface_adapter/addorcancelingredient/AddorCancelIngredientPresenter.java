package interface_adapter.addorcancelingredient;

import entity.CommonIngredient;
import entity.CommonIngredientFactory;
import entity.Ingredient;
import entity.IngredientFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialState;
import interface_adapter.initial.InitialViewModel;
import use_case.addorcancelingredient.AddorCancelIngredientOutputBoundary;
import use_case.addorcancelingredient.AddorCancelIngredientOutputData;

import java.util.List;

/**
 * Presenter for the add or cancel ingredient use case.
 */
public class AddorCancelIngredientPresenter implements AddorCancelIngredientOutputBoundary {

    private final AddorCancelIngredientViewModel addorCancelIngredientViewModel;
    private final InitialViewModel initialViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddorCancelIngredientPresenter(AddorCancelIngredientViewModel addorCancelIngredientViewModel, InitialViewModel initialViewModel, ViewManagerModel viewManagerModel) {
        this.addorCancelIngredientViewModel = addorCancelIngredientViewModel;
        this.initialViewModel = initialViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddorCancelIngredientOutputData response) {
        // On success, switch to the Initial view.
        final InitialState initialState = initialViewModel.getState();
        initialState.addIngredient(response.getIngredient());
        this.initialViewModel.setState(initialState);
        initialViewModel.firePropertyChanged();

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
