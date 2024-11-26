package app;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

import data_access.FileIngredientDataAccessObject;
import data_access.InMemoryRecipeDataAccessObject;
import data_access.InMemoryRecipeManagementRepository;
import entity.*;

import interface_adapter.ViewManagerModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientController;
import interface_adapter.addorcancelingredient.AddorCancelIngredientPresenter;
import interface_adapter.addorcancelingredient.AddorCancelIngredientViewModel;
import interface_adapter.deleteingredient.DeleteIngredientController;
import interface_adapter.deleteingredient.DeleteIngredientPresenter;
import interface_adapter.initial.InitialViewModel;


import interface_adapter.recipemanagement.RecipeInfoViewModel;
import interface_adapter.recipemanagement.RecipeManagementController;
import interface_adapter.recipemanagement.RecipeManagementPresenter;
import interface_adapter.recipemanagement.RecipeManagementViewModel;

import use_case.addorcancelingredient.AddorCancelIngredientInteractor;
import use_case.delete_ingredient.DeleteIngredientInputBoundary;
import use_case.delete_ingredient.DeleteIngredientInteractor;
import use_case.delete_ingredient.DeleteIngredientOutputBoundary;
import use_case.addorcancelingredient.AddorCancelIngredientInputBoundary;
import use_case.addorcancelingredient.AddorCancelIngredientOutputBoundary;

import data_access.InMemoryIngredientDataAccessObject;
import use_case.recipe_management.RecipeManagementInputBoundary;
import use_case.recipe_management.RecipeManagementInteractor;
import use_case.recipe_management.RecipeManagementOutputBoundary;
import use_case.recipe_management.RecipeManagementUserDataAccessInterface;
import view.*;


public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserFactory userFactory = new CommonUserFactory();
    private final IngredientFactory ingredientFactory = new CommonIngredientFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final InMemoryIngredientDataAccessObject memoryIngredientDataAccessObject =
            new InMemoryIngredientDataAccessObject();
    private final FileIngredientDataAccessObject ingredientDataAccessObject;

    final List<Recipe> recipeRepository = new InMemoryRecipeManagementRepository().getCurrentRecipes();

    private AddIngredientView addIngredientView;
    private AddorCancelIngredientViewModel addIngredientViewModel;
    private DeleteIngredientReminderView deleteIngredientReminderView;
    private InitialView initialView;
    private InitialViewModel initialViewModel;
    private RecipeListView recipeListView;
    private RecipeManagementViewModel recipeManagementViewModel;
    private RecipeManagementViewModel recipeListViewModel;
    private RecipeInfoView recipeInfoView;

    public AppBuilder(String csvPath) {
        cardPanel.setLayout(cardLayout);
        try {
            File csvFile = new File(csvPath);

            if (!csvFile.exists()) {
                // Create the file if it doesn't exist
                File parentDir = csvFile.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs(); // Create parent directories if necessary
                }
                if (csvFile.createNewFile()) {
                    System.out.println("File created at: " + csvFile.getAbsolutePath());
                }
            }

            ingredientDataAccessObject = new FileIngredientDataAccessObject(csvPath, ingredientFactory);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize FileIngredientDataAccessObject", e);
        }
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
        initialView = new InitialView(initialViewModel, ingredientDataAccessObject);
        cardPanel.add(initialView, initialView.getViewName());
        return this;
    }

//    /**
//     * Adds the RecipeListView to the application.
//     * @return this builder
//     */
//    public AppBuilder addRecipeListView() {
//        recipeInfoView = new RecipeInfoView(cardLayout, cardPanel);
//        recipeManagementViewModel = new RecipeManagementViewModel();
//        final RecipeManagementOutputBoundary recipeManagementOutputBoundary = new RecipeManagementPresenter();
//        final RecipeManagementInputBoundary interactor = new RecipeManagementInteractor(recipeRepository,
//                recipeManagementOutputBoundary);
//        final List<Recipe> controller = new RecipeManagementController(interactor).getCurrentRecipes();
//
//        recipeListView = new RecipeListView(controller, recipeInfoView, cardLayout, cardPanel);
//
//        cardPanel.add(recipeListView, recipeListView.getViewName());
//        return this;
//    }

    /**
     * Adds the RecipeInfoView to the application.
     * @return this builder
     */
    public AppBuilder addRecipeInfoView() {
        recipeInfoView = new RecipeInfoView(cardLayout, cardPanel);
        cardPanel.add(recipeInfoView, recipeInfoView.getViewName());
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
                new DeleteIngredientPresenter(initialViewModel, addIngredientViewModel, recipeListViewModel,
                        viewManagerModel);
        final DeleteIngredientInputBoundary deleteIngredientInteractor =
                new DeleteIngredientInteractor(ingredientDataAccessObject, deleteIngredientOutputBoundary);

        final DeleteIngredientController deleteIngredientController =
                new DeleteIngredientController(deleteIngredientInteractor);
        initialView.setDeleteIngredientController(deleteIngredientController);
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

        viewManagerModel.setState(initialView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
