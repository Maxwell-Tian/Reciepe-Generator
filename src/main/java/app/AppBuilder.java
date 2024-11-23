package app;

import java.awt.*;
import javax.swing.*;

import entity.UserFactory;
import entity.CommonIngredientFactory;
import entity.IngredientFactory;

import interface_adapter.ViewManagerModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientController;
import interface_adapter.addorcancelingredient.AddorCancelIngredientPresenter;
import interface_adapter.addorcancelingredient.AddorCancelIngredientViewModel;
import interface_adapter.deleteingredient.DeleteIngredientController;
import interface_adapter.deleteingredient.DeleteIngredientPresenter;
import interface_adapter.initial.InitialViewModel;

import use_case.delete_ingredient.DeleteIngredientInputBoundary;
import use_case.delete_ingredient.DeleteIngredientInteractor;
import use_case.delete_ingredient.DeleteIngredientOutputBoundary;
import use_case.addorcancelingredient.AddorCancelIngredientInputBoundary;
import use_case.addorcancelingredient.AddorCancelIngredientInteractor;
import use_case.addorcancelingredient.AddorCancelIngredientOutputBoundary;

import data_access.InMemoryIngredientDataAccessObject;
import view.*;


public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserFactory userFactory = new CommonUserFactory();
    private final IngredientFactory ingredientFactory = new CommonIngredientFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final InMemoryIngredientDataAccessObject ingredientDataAccessObject =
            new InMemoryIngredientDataAccessObject();
  
    private final IngredientFactory ingredientFactory = new CommonIngredientFactory();

    private AddIngredientView addIngredientView;
    private AddorCancelIngredientViewModel addIngredientViewModel;
    private DeleteIngredientReminderView deleteIngredientReminderView;
    private InitialView initialView;
    private InitialViewModel initialViewModel;
    private RecipeListView recipeListView;
    private RecipeManagementViewModel recipeListViewModel;
    private RecipeInfoView recipeInfoView;

    private final InMemoryIngredientDataAccessObject ingredientDataAccessObject = new InMemoryIngredientDataAccessObject();

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

//    /**
//     * Adds the RecipeListView to the application.
//     * @return this builder
//     */
//    public AppBuilder addRecipeListView() {
//        recipeListViewModel = new RecipeManagementViewModel();
//        recipeListView = new RecipeListView();
//        cardPanel.add(recipeListView, recipeListView.getViewName());
//        return this;
//    }

//    /**
//     * Adds the RecipeInfoView to the application.
//     * @return this builder
//     */
//    public AppBuilder addRecipeInfoView() {
//        recipeInfoView = new RecipeInfoViewModel();
//        recipeInfoView = new RecipeInfoView(RecipeInfoViewModel);
//        cardPanel.add(recipeInfoView, recipeInfoView.getViewName());
//        return this;
//    }

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
                new DeleteIngredientPresenter(initialViewModel, addIngredientViewModel, viewManagerModel);
        final DeleteIngredientInputBoundary deleteIngredientInteractor =
                new DeleteIngredientInteractor(ingredientDataAccessObject, deleteIngredientOutputBoundary);

        final DeleteIngredientController deleteIngredientController =
                new DeleteIngredientController(deleteIngredientInteractor);
        initialView.setDeleteIngredientController(deleteIngredientController);
        return this;
      
    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Recipe Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(initialView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
