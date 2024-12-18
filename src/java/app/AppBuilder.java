package app;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;

import data.txtConnector;
import interface_adapter.NutritionViewModel.NutritionController;
import interface_adapter.NutritionViewModel.NutritionPresenter;
import interface_adapter.NutritionViewModel.NutritionViewModel;
import interface_adapter.recipemanagement.RecipeInfoViewModel;
import use_case.expired_food.ExpiredIngredientInteractor;
import use_case.expired_food.ExpiredIngredientOutputBoundary;
import use_case.searchNutrition.NutritionInteractor;
import use_case.searchNutrition.NutritionOutputBoundary;
import use_case.searchNutrition.SearchNutritionInputBoundary;
import view.ExpirationWarningView;
import javax.swing.*;

import data_access.InMemoryRecipeDataAccessObject;
import data_access.InMemoryRecipeManagementRepository;
import entity.*;

import interface_adapter.ExpirationWarning.ExpirationWarningController;
import interface_adapter.ExpirationWarning.ExpirationWarningPresenter;
import interface_adapter.ExpirationWarning.ExpirationWarningViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientController;
import interface_adapter.addorcancelingredient.AddorCancelIngredientPresenter;
import interface_adapter.addorcancelingredient.AddorCancelIngredientViewModel;
import interface_adapter.deleteingredient.DeleteIngredientController;
import interface_adapter.deleteingredient.DeleteIngredientPresenter;
import interface_adapter.initial.InitialViewModel;


import interface_adapter.recipemanagement.RecipeManagementController;
import interface_adapter.recipemanagement.RecipeManagementPresenter;
import interface_adapter.recipemanagement.RecipeManagementViewModel;

import use_case.addorcancelingredient.AddorCancelIngredientInteractor;
import use_case.delete_ingredient.DeleteIngredientInputBoundary;
import use_case.delete_ingredient.DeleteIngredientInteractor;
import use_case.delete_ingredient.DeleteIngredientOutputBoundary;
import use_case.addorcancelingredient.AddorCancelIngredientInputBoundary;
import use_case.addorcancelingredient.AddorCancelIngredientOutputBoundary;

import use_case.recipe_management.RecipeManagementInputBoundary;
import use_case.recipe_management.RecipeManagementInteractor;
import use_case.recipe_management.RecipeManagementOutputBoundary;
import view.*;


public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

//    private final UserFactory userFactory = new CommonUserFactory();
    private final IngredientFactory ingredientFactory = new CommonIngredientFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

//    private final InMemoryIngredientDataAccessObject ingredientDataAccessObject =
//            new InMemoryIngredientDataAccessObject();
    private final txtConnector ingredientDataAccessObject = new txtConnector();

//    private final InMemoryRecipeDataAccessObject recipeDataAccessObject = new InMemoryRecipeDataAccessObject();

    final List<Recipe> recipeRepository = new InMemoryRecipeManagementRepository().getCurrentRecipes();

    private AddIngredientView addIngredientView;
    private AddorCancelIngredientViewModel addIngredientViewModel;
    private InitialView initialView;
    private InitialViewModel initialViewModel;
    private RecipeListView recipeListView;
    private RecipeManagementViewModel recipeListViewModel;
    private RecipeInfoViewModel recipeInfoViewModel;
    private RecipeInfoView recipeInfoView;
    private ExpirationWarningView expirationWarningView;
    private ExpirationWarningViewModel expirationWarningViewModel;
    private NutritionViewModel nutritionViewModel;
    private NutritionView nutritionView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the AddIngredientView to the application.
     * @return this builder
     */
    public AppBuilder addAddIngredientView() {
        addIngredientViewModel = new AddorCancelIngredientViewModel();
        addIngredientView = new AddIngredientView(addIngredientViewModel);
        cardPanel.add(addIngredientView, addIngredientView.getViewName());
        return this;
    }


    /**
     * Adds the InitialView to the application.
     * @return this builder
     */
    public AppBuilder addInitialView() {
        initialViewModel = new InitialViewModel();
        initialView = new InitialView(initialViewModel);
        cardPanel.add(initialView, initialView.getViewName());
        return this;
    }

    /**
     * Adds the RecipeInfoView to the application.
     * @return this builder
     */
    public AppBuilder addNutritionView() {
        nutritionViewModel = new NutritionViewModel();
        nutritionView = new NutritionView(nutritionViewModel);
        cardPanel.add(nutritionView, nutritionView.getViewName());
        return this;
    }

    /**
     * Adds the NutritionView to the application.
     * @return this builder
     */
    public AppBuilder addRecipeInfoView() {
        recipeInfoViewModel = new RecipeInfoViewModel();
        recipeInfoView = new RecipeInfoView(recipeInfoViewModel);
        cardPanel.add(recipeInfoView, recipeInfoView.getViewName());
        return this;
    }

    /**
     * Adds the RecipeListView to the application.
     * @return this builder
     */
    public AppBuilder addRecipeListView() throws FileNotFoundException {
        recipeListViewModel = new RecipeManagementViewModel();
//        RecipeManagementOutputBoundary recipeManagementOutputBoundary = new RecipeManagementPresenter(initialViewModel, recipeListViewModel, recipeInfoViewModel, viewManagerModel);
//        RecipeManagementInputBoundary interactor = new RecipeManagementInteractor(recipeDataAccessObject, recipeManagementOutputBoundary);
//
//        List<Recipe> controller = new RecipeManagementController(interactor).getCurrentRecipes();
        recipeListView = new RecipeListView(recipeInfoView, recipeListViewModel);
        cardPanel.add(recipeListView, recipeListView.getViewName());
        return this;
    }


    /**
     * Adds the ExpirationWarningView to the application.
     * @return this builder
     */
    public AppBuilder addExpirationWarningView() {
        final ExpiredIngredientOutputBoundary expiredIngredientOutputBoundary = new ExpirationWarningPresenter(
                expirationWarningViewModel, initialViewModel, viewManagerModel);
        final ExpiredIngredientInteractor interactor = new ExpiredIngredientInteractor(ingredientDataAccessObject, expiredIngredientOutputBoundary);

        final ExpirationWarningController controller = new ExpirationWarningController(interactor);

        expirationWarningViewModel = new ExpirationWarningViewModel();
        expirationWarningView = new ExpirationWarningView(expirationWarningViewModel, controller);
        cardPanel.add(expirationWarningView, expirationWarningView.getViewName());
        return this;
    }

    /**
     * Adds the AddorCancelIngredient Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSAoCIUseCase() {
        final AddorCancelIngredientOutputBoundary aociOutputBoundary = new AddorCancelIngredientPresenter(
                addIngredientViewModel, initialViewModel, recipeListViewModel, viewManagerModel);
        final AddorCancelIngredientInputBoundary aociInteractor = new AddorCancelIngredientInteractor(
                ingredientDataAccessObject, aociOutputBoundary, ingredientFactory);

        final AddorCancelIngredientController controller = new AddorCancelIngredientController(aociInteractor);
        addIngredientView.setAddorCancelIngredientController(controller);
        return this;
    }

    /**
     * Adds the Delete Ingredient Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDeleteIngredientUseCase() {
        final DeleteIngredientOutputBoundary deleteIngredientOutputBoundary =
                new DeleteIngredientPresenter(initialViewModel, addIngredientViewModel, recipeListViewModel, viewManagerModel, nutritionViewModel);
        final DeleteIngredientInputBoundary deleteIngredientInteractor =
                new DeleteIngredientInteractor(ingredientDataAccessObject, deleteIngredientOutputBoundary);

        final DeleteIngredientController deleteIngredientController =
                new DeleteIngredientController(deleteIngredientInteractor);
        initialView.setDeleteIngredientController(deleteIngredientController);

        final NutritionOutputBoundary nutritionOutputBoundary =
                new NutritionPresenter(initialViewModel,nutritionViewModel,viewManagerModel);
        final SearchNutritionInputBoundary nutritionInputBoundary = new NutritionInteractor(nutritionOutputBoundary);
        final NutritionController controller = new NutritionController(nutritionInputBoundary);
        initialView.setNutritionController(controller);
        return this;
    }

    public AppBuilder addSearchNutritionUseCase() {
        final NutritionOutputBoundary nutritionOutputBoundary =
                new NutritionPresenter(initialViewModel,nutritionViewModel,viewManagerModel);
        final SearchNutritionInputBoundary nutritionInputBoundary = new NutritionInteractor(nutritionOutputBoundary);
        final NutritionController controller = new NutritionController(nutritionInputBoundary);
        nutritionView.setController(controller);
        cardPanel.add(nutritionView, nutritionView.getViewName());
        return this;
    }

    /**
     * Adds the generate recipe Use Case to the application.
     * @return this builder
     */
    public AppBuilder addgenerateRecipeUseCase() {
        final RecipeManagementOutputBoundary recipeManagementOutputBoundary = new RecipeManagementPresenter(
                initialViewModel, recipeListViewModel, recipeInfoViewModel, viewManagerModel);
        final RecipeManagementInputBoundary Interactor = new RecipeManagementInteractor(
                 recipeManagementOutputBoundary);

        final RecipeManagementController controller = new RecipeManagementController(Interactor);
        recipeListView.setRecipeManagementController(controller);
        recipeInfoView.setRecipeManagementController(controller);
        return this;
    }


    /**
     * Adds the expiration warning Use Case to the application.
     * @return this builder
     */
    public AppBuilder addExpirationWarningUseCase() {
        final ExpiredIngredientOutputBoundary expiredIngredientOutputBoundary = new ExpirationWarningPresenter(
                expirationWarningViewModel, initialViewModel, viewManagerModel);
        final ExpiredIngredientInteractor interactor = new ExpiredIngredientInteractor(ingredientDataAccessObject, expiredIngredientOutputBoundary);

        final ExpirationWarningController controller = new ExpirationWarningController(interactor);
        expirationWarningView.setExpirationWarningController(controller);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Recipe Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(expirationWarningView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
