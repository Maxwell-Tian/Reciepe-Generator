package interface_adapter.recipemanagement;

import entity.Recipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialViewModel;
import use_case.recipe_management.RecipeManagementOutputBoundary;

import java.util.List;
import java.util.Map;

public class RecipeManagementPresenter implements RecipeManagementOutputBoundary {

    private final InitialViewModel initialViewModel;
    private final RecipeManagementViewModel recipeManagementViewModel;
    private final RecipeInfoViewModel recipeInfoViewModel;
    private final ViewManagerModel viewManagerModel;

    public RecipeManagementPresenter(InitialViewModel initialViewModel,
                                     RecipeManagementViewModel recipeManagementViewModel,
                                     RecipeInfoViewModel recipeInfoViewModel,
                                     ViewManagerModel viewManagerModel) {
        this.initialViewModel = initialViewModel;
        this.recipeManagementViewModel = recipeManagementViewModel;
        this.recipeInfoViewModel = recipeInfoViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void presentRecipes(List<Recipe> recipes) {
        System.out.println("Generated Recipes:");
        for (Recipe recipe : recipes) {
            System.out.println(recipe.getName());
        }
    }

    @Override
    public void presentSuccessMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void presentErrorMessage(String errorMessage) {

    }

    @Override
    public void presentRecommendations(Map<String, List<String>> recommendations) {

    }

    @Override
    public void switchToInitialView() {
        viewManagerModel.setState(initialViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToRecipeListView() {
        viewManagerModel.setState(recipeManagementViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToRecipeInfoView() {
        viewManagerModel.setState(recipeInfoViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
