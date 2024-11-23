package interface_adapter.addorcancelingredient;

import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialViewModel;
import use_case.addorcancelingredient.AddorCancelIngredientOutputBoundary;
import use_case.addorcancelingredient.AddorCancelIngredientOutputData;

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
//        final InitialState initialState = initialViewModel.getState();
//        initialState.setIngredientname(response.getIngredientname());
//        this.initialViewModel.setState(initialState);
//        initialViewModel.firePropertyChanged();

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
