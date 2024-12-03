package interface_adapter.ExpirationWarning;

import entity.Ingredient;
import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialState;
import interface_adapter.initial.InitialViewModel;
import use_case.expired_food.ExpiredIngredientOutputBoundary;

import java.util.List;

/**
 * The ExpirationWarningPresenter class implements the ExpiredIngredientOutputBoundary
 * interface, facilitating the presentation of expiration warning data and managing
 * transitions between views.
 */
public class ExpirationWarningPresenter implements ExpiredIngredientOutputBoundary {
    private final ExpirationWarningViewModel viewModel;
    private final InitialViewModel initialViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs an ExpirationWarningPresenter with the specified ViewModel, InitialViewModel,
     * and ViewManagerModel.
     *
     * @param viewModel       the ViewModel for the expiration warning view
     * @param initialViewModel the ViewModel for the initial view
     * @param viewManagerModel the model for managing view states
     */
    public ExpirationWarningPresenter(ExpirationWarningViewModel viewModel,
                                      InitialViewModel initialViewModel,
                                      ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.initialViewModel = initialViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Retrieves the list of current ingredients.
     *
     * @return a list of current ingredients
     */
    @Override
    public List<Ingredient> getCurrentIngredients(){
        final InitialState initialState = initialViewModel.getState();
        return initialState.getIngredients();
    }

    /**
     * Deletes the specified ingredient from the system.
     *
     * @param ingredient the ingredient to delete
     */
    @Override
    public void deleteIngredient(Ingredient ingredient){
        final InitialState initialState = initialViewModel.getState();
        initialState.deleteIngredient(ingredient);
        this.initialViewModel.setState(initialState);
        initialViewModel.firePropertyChanged();
    }


    /**
     * Switches the view back to the initial screen.
     */
    @Override
    public void switchToInitialView() {
        viewManagerModel.setState(initialViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
