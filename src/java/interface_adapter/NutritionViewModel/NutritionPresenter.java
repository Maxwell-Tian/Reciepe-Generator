package interface_adapter.NutritionViewModel;

import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialViewModel;
import use_case.searchNutrition.NutritionOutputBoundary;
import view.ErrorInfoView;

import java.util.List;

public class NutritionPresenter implements NutritionOutputBoundary {
    private final InitialViewModel initialViewModel;
    private final NutritionViewModel nutritionViewModel;
    private final ViewManagerModel viewManagerModel;

    public NutritionPresenter(InitialViewModel initialViewModel, NutritionViewModel nutritionViewModel, ViewManagerModel viewManagerModel) {
        this.initialViewModel = initialViewModel;
        this.nutritionViewModel = nutritionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentNutrition(List<String> NutritionInfo) {
        switchNutritionView();
        for(String nutritionInfo : NutritionInfo){
            System.out.println(nutritionInfo);
        }
    }

    @Override
    public void switchToInitialView() {
        viewManagerModel.setState(initialViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentErrorMessage(String errorMessage) {
        ErrorInfoView errorInfoView = new ErrorInfoView();
        errorInfoView.ShowErrorView(errorMessage);
    }

    @Override
    public void presentSuccessMessage(String successMessage) {
        System.out.println(successMessage);
    }

    @Override
    public void switchNutritionView() {
        viewManagerModel.setState(nutritionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
