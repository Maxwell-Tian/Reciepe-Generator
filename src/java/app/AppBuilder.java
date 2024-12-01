package app;

import java.awt.*;
import java.util.List;

import data.txtConnector;
import use_case.expired_food.ExpiredIngredientOutputBoundary;
import view.ExpirationWarningView;
import javax.swing.*;

import data_access.InMemoryRecipeDataAccessObject;
import data_access.InMemoryRecipeManagementRepository;
import entity.*;

import interface_adapter.expirationwarning.ExpirationWarningController;
import interface_adapter.expirationwarning.ExpirationWarningPresenter;
import interface_adapter.expirationwarning.ExpirationWarningViewModel;
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

import use_case.expired_food.ExpiredIngredientInteractor;
import use_case.recipe_management.RecipeManagementInputBoundary;
import use_case.recipe_management.RecipeManagementInteractor;
import use_case.recipe_management.RecipeManagementOutputBoundary;
import view.*;


public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserFactory userFactory = new CommonUserFactory();
    private final IngredientFactory ingredientFactory = new CommonIngredientFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

//    private final InMemoryIngredientDataAccessObject ingredientDataAccessObject =
//            new InMemoryIngredientDataAccessObject();
    private final txtConnector ingredientDataAccessObject = new txtConnector();

    private final InMemoryRecipeDataAccessObject recipeDataAccessObject = new InMemoryRecipeDataAccessObject();

    final List<Recipe> recipeRepository = new InMemoryRecipeManagementRepository().getCurrentRecipes();

    private AddIngredientView addIngredientView;
    private AddorCancelIngredientViewModel addIngredientViewModel;
    private InitialView initialView;
    private InitialViewModel initialViewModel;
    private RecipeListView recipeListView;
    private RecipeManagementViewModel recipeListViewModel;
    private RecipeInfoView recipeInfoView;
    private ExpirationWarningView expirationWarningView;
    private ExpirationWarningViewModel expirationWarningViewModel;

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

//    /**
//     * Adds the DeleteIngredientReminderView to the application.
//     * @return this builder
//     */
//    public AppBuilder addDeleteIngredientReminderView() {
//        deleteIngredientReminderView = new DeleteIngredientReminderViewModel();
//        deleteIngredientReminderView = new DeleteIngredientReminderView(DeleteIngredientReminderViewModel);
//        cardPanel.add(deleteIngredientReminderView, deleteIngredientReminderView, deleteIngredientReminderView.getViewName());
//        return this;
//    }

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
     * Adds the RecipeListView to the application.
     * @return this builder
     */
    public AppBuilder addRecipeListView() {
        recipeListViewModel = new RecipeManagementViewModel();
        RecipeManagementOutputBoundary recipeManagementOutputBoundary = new RecipeManagementPresenter(initialViewModel, recipeListViewModel, viewManagerModel);
        RecipeManagementInputBoundary interactor = new RecipeManagementInteractor(recipeDataAccessObject, recipeManagementOutputBoundary);
        List<Recipe> controller = new RecipeManagementController(interactor).getCurrentRecipes();
        recipeListView = new RecipeListView(controller, recipeInfoView, recipeListViewModel);
//        recipeInfoView = new RecipeInfoView(cardLayout, cardPanel);
//        final RecipeManagementOutputBoundary recipeManagementOutputBoundary = new RecipeManagementPresenter();
//        final RecipeManagementInputBoundary interactor = new RecipeManagementInteractor(recipeRepository, recipeManagementOutputBoundary);
//        final List<Recipe> controller = new RecipeManagementController(interactor).getCurrentRecipes();
//
//        recipeListView = new RecipeListView(controller, recipeInfoView, cardLayout, cardPanel);

        cardPanel.add(recipeListView, recipeListView.getViewName());
        return this;
    }

//    /**
//     * Adds the RecipeInfoView to the application.
//     * @return this builder
//     */
//    public AppBuilder addRecipeInfoView() {
//        recipeInfoView = new RecipeInfoView(cardLayout, cardPanel);
//        cardPanel.add(recipeInfoView, recipeInfoView.getViewName());
//        return this;
//    }

    /**
     * Adds the ExpirationWarningView to the application.
     * @return this builder
     */
    public AppBuilder addExpirationWarningView() {
        final ExpiredIngredientOutputBoundary expiredIngredientOutputBoundary = new ExpirationWarningPresenter(
                expirationWarningViewModel, initialViewModel, viewManagerModel);
        final ExpiredIngredientInteractor interactor = new ExpiredIngredientInteractor(expiredIngredientOutputBoundary);

        final ExpirationWarningController controller = new ExpirationWarningController(interactor);

        expirationWarningViewModel = new ExpirationWarningViewModel();
        expirationWarningView = new ExpirationWarningView(expirationWarningViewModel, controller);
        cardPanel.add(expirationWarningView, "expiration warning");
        return this;
    }

    /**
     * Adds the AddorCancelIngredient Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSAoCIUseCase() {
        final AddorCancelIngredientOutputBoundary aociOutputBoundary = new AddorCancelIngredientPresenter(
                addIngredientViewModel, initialViewModel, viewManagerModel);
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
                new DeleteIngredientPresenter(initialViewModel, addIngredientViewModel, recipeListViewModel, viewManagerModel);
        final DeleteIngredientInputBoundary deleteIngredientInteractor =
                new DeleteIngredientInteractor(ingredientDataAccessObject, deleteIngredientOutputBoundary);

        final DeleteIngredientController deleteIngredientController =
                new DeleteIngredientController(deleteIngredientInteractor);
        initialView.setDeleteIngredientController(deleteIngredientController);
        return this;
    }

    /**
     * Adds the generate recipe Use Case to the application.
     * @return this builder
     */
    public AppBuilder generateRecipeUseCase() {
        final RecipeManagementOutputBoundary recipeManagementOutputBoundary = new RecipeManagementPresenter(
                initialViewModel, recipeListViewModel, viewManagerModel);
        final RecipeManagementInputBoundary Interactor = new RecipeManagementInteractor(
                recipeDataAccessObject, recipeManagementOutputBoundary);

        final RecipeManagementController controller = new RecipeManagementController(Interactor);
        recipeListView.setRecipeManagementController(controller);
        return this;
    }

//    /**
//     * Adds the expiration warning Use Case to the application.
//     * @return this builder
//     */
//    public AppBuilder addExpirartionWarningUseCase() {
//        final ExpiredIngredientOutputBoundary expiredIngredientOutputBoundary = new ExpirationWarningPresenter(
//                expirationWarningViewModel, initialViewModel, viewManagerModel);
//        final ExpiredIngredientInteractor interactor = new ExpiredIngredientInteractor(expiredIngredientOutputBoundary);
//
//        final ExpirationWarningController controller = new ExpirationWarningController(interactor);
//        expirationWarningView.setExpirationWarningController(controller);
//        return this;
//    }

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

    public AppBuilder addExpirartionWarningUseCase() {
        final ExpiredIngredientOutputBoundary expiredIngredientOutputBoundary = new ExpirationWarningPresenter(
                expirationWarningViewModel, initialViewModel, viewManagerModel);
        final ExpiredIngredientInteractor interactor = new ExpiredIngredientInteractor(expiredIngredientOutputBoundary);
        final ExpirationWarningController controller = new ExpirationWarningController(interactor);
        if (expirationWarningView != null) {
            expirationWarningView.setExpirationWarningController(controller);
        }
        return this;
    }
}
