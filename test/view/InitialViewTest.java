package view;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import data_access.InMemoryIngredientDataAccessObject;
import entity.CommonIngredientFactory;
import entity.Ingredient;
import interface_adapter.ViewManagerModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientViewModel;
import interface_adapter.deleteingredient.DeleteIngredientController;
import interface_adapter.deleteingredient.DeleteIngredientPresenter;
import interface_adapter.initial.InitialState;
import interface_adapter.initial.InitialViewModel;
import interface_adapter.recipemanagement.RecipeManagementViewModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_case.delete_ingredient.DeleteIngredientInputBoundary;
import use_case.delete_ingredient.DeleteIngredientInteractor;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class InitialViewTest {
    private InitialViewModel initialViewModel;
    private AddorCancelIngredientViewModel aocIngredientViewModel;
    private RecipeManagementViewModel recipeManagementViewModel;
    private ViewManagerModel viewManagerModel;

    private DeleteIngredientPresenter presenter;
    private DeleteIngredientInputBoundary deleteIngredientInteractor;
    private DeleteIngredientController deleteIngredientController;

    private InMemoryIngredientDataAccessObject ingredientDataAccessObject;

    private InitialState initialState;
    private InitialView initialView;

    @BeforeEach
    void setUp() {
        List<Ingredient> ingredients = new ArrayList<>();
        CommonIngredientFactory ingredientFactory = new CommonIngredientFactory();
        Ingredient mango = ingredientFactory.create("mango", LocalDate.parse("2025-12-11"));
        Ingredient eggplant = ingredientFactory.create("eggplant", LocalDate.parse("2025-08-11"));
        ingredients.add(mango);
        ingredients.add(eggplant);

        initialViewModel = new InitialViewModel();
        initialState = new InitialState();
        initialState.setIngredients(ingredients);
        initialViewModel.setState(initialState);

        initialViewModel = new InitialViewModel();
        aocIngredientViewModel = new AddorCancelIngredientViewModel();
        recipeManagementViewModel = new RecipeManagementViewModel();
        viewManagerModel = new ViewManagerModel();
        ingredientDataAccessObject = new InMemoryIngredientDataAccessObject();

        presenter = new DeleteIngredientPresenter(
                initialViewModel,
                aocIngredientViewModel,
                recipeManagementViewModel,
                viewManagerModel
        );

        deleteIngredientInteractor = new DeleteIngredientInteractor(ingredientDataAccessObject, presenter);
        deleteIngredientController = new DeleteIngredientController(deleteIngredientInteractor);

        initialView = new InitialView(initialViewModel);
        initialView.setDeleteIngredientController(deleteIngredientController);
        PropertyChangeEvent event = new PropertyChangeEvent(initialViewModel, "ingredients", null,
                ingredients);
        initialView.propertyChange(event);
    }

    @AfterEach
    void tearDown() {
        ingredientDataAccessObject = null;
    }

    @Test
    void testAddIngredientButtonClick() {
        JButton addIngredientButton = findButtonByLabel("Add Ingredient");
        addIngredientButton.doClick();

        assertEquals(aocIngredientViewModel.getViewName(), viewManagerModel.getState());
    }

    @Test
    void testGenerateRecipeButtonClick() {
        JButton generateRecipeButton = findButtonByLabel("Generate Recipe");
        generateRecipeButton.doClick();

        assertEquals(recipeManagementViewModel.getViewName(), viewManagerModel.getState());
    }

    @Test
    void testPropertyChangeUpdatesIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        CommonIngredientFactory ingredientFactory = new CommonIngredientFactory();
        Ingredient orange = ingredientFactory.create("orange", LocalDate.parse("2025-08-11"));
        ingredients.add(orange);

        PropertyChangeEvent event = new PropertyChangeEvent(initialViewModel, "ingredients", null,
                ingredients);
        initialView.propertyChange(event);

        Component[] components = initialView.getComponents();
        assertTrue(components.length > 2, "InitialView should have at least 3 components");
    }

    @Test
    void testIngredientDeletion() {
        List<Ingredient> ingredients = new ArrayList<>();
        CommonIngredientFactory ingredientFactory = new CommonIngredientFactory();
        Ingredient cabbage = ingredientFactory.create("cabbage", LocalDate.parse("2028-10-11"));
        ingredients.add(cabbage);

        initialState.setIngredients(ingredients);
        initialViewModel.setState(initialState);

        PropertyChangeEvent event = new PropertyChangeEvent(initialViewModel, "ingredients",
                null, ingredients);
        initialView.propertyChange(event);

        JButton deleteButton = findDeleteButtonByIngredientName("cabbage");
        assertNotNull(deleteButton, "Delete button for 'cabbage' should exist before deletion");

        deleteButton.doClick();

        assertFalse(initialViewModel.getState().getIngredients().contains(cabbage),
                "cabbage should be deleted");

        deleteButton = findDeleteButtonByIngredientName("cabbage");
        assertNull(deleteButton, "Delete button for 'cabbage' should no longer exist after deletion");
    }

    @Test
    void testGetViewName() {
        assertEquals("initial", initialView.getViewName());
    }

    @Test
    void testActionPerformedAddIngredient() {
        JButton addIngredientButton = (JButton) findButtonByLabel("Add Ingredient");
        addIngredientButton.doClick();

        assertEquals(aocIngredientViewModel.getViewName(), viewManagerModel.getState());
    }

    @Test
    void testActionPerformedGenerateRecipe() {
        JButton generateRecipeButton = (JButton) findButtonByLabel("Generate Recipe");
        generateRecipeButton.doClick();

        assertEquals(recipeManagementViewModel.getViewName(), viewManagerModel.getState());
    }

    private JButton findButtonByLabel(String label) {
        for (Component component : initialView.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component subComponent : panel.getComponents()) {
                    if (subComponent instanceof JButton) {
                        JButton button = (JButton) subComponent;
                        if (button.getText().equals(label)) {
                            return button;
                        }
                    }
                }
            }
        }
        return null;
    }

    private JButton findDeleteButtonByIngredientName(String name) {
        // Loop through the components of the InitialView
        for (Component component : initialView.getComponents()) {
            // Check if the component is a JScrollPane
            if (component instanceof JScrollPane scrollPane) {
                // Retrieve the viewport's view, which should be the ingredients panel
                Component view = scrollPane.getViewport().getView();
                if (view instanceof JPanel ingredientsPanel) {
                    // Iterate through the subcomponents of the ingredients panel
                    for (Component subComponent : ingredientsPanel.getComponents()) {
                        // Check if the subcomponent is an ingredient panel
                        if (subComponent instanceof JPanel ingredientPanel) {
                            JLabel nameLabel = null;
                            JButton deleteButton = null;

                            // Loop through the components of the ingredient panel
                            for (Component ingredientComponent : ingredientPanel.getComponents()) {
                                if (ingredientComponent instanceof JLabel label && label.getText().equals(name)) {
                                    nameLabel = label;
                                } else if (ingredientComponent instanceof JButton button) {
                                    deleteButton = button;
                                }
                            }

                            // Return the delete button if the name label matches
                            if (nameLabel != null && deleteButton != null) {
                                return deleteButton;
                            }
                        }
                    }
                }
            }
        }
        // Return null if no matching delete button is found
        return null;
    }
}