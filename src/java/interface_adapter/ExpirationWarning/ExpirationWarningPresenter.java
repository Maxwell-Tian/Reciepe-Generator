package interface_adapter.ExpirationWarning;

import entity.Ingredient;
import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialState;
import interface_adapter.initial.InitialViewModel;
import use_case.expired_food.ExpiredIngredientOutputBoundary;

import java.util.List;

public class ExpirationWarningPresenter implements ExpiredIngredientOutputBoundary {
    private final ExpirationWarningViewModel viewModel;
    private final InitialViewModel initialViewModel;
    private final ViewManagerModel viewManagerModel;

    public ExpirationWarningPresenter(ExpirationWarningViewModel viewModel,
                                      InitialViewModel initialViewModel,
                                      ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.initialViewModel = initialViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public List<Ingredient> getCurrentIngredients(){
        final InitialState initialState = initialViewModel.getState();
        return initialState.getIngredients();
    }

    @Override
    public void deleteIngredient(Ingredient ingredient){
        final InitialState initialState = initialViewModel.getState();
        initialState.deleteIngredient(ingredient);
        this.initialViewModel.setState(initialState);
        initialViewModel.firePropertyChanged();
    }

    @Override
    public void switchToInitialView() {
        viewManagerModel.setState(initialViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
