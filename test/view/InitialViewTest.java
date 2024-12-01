package view;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import entity.CommonIngredientFactory;
import entity.Ingredient;
import interface_adapter.deleteingredient.DeleteIngredientController;
import interface_adapter.initial.InitialState;
import interface_adapter.initial.InitialViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class InitialViewTest {
    @Mock
    private InitialViewModel initialViewModel;

    @Mock
    private DeleteIngredientController deleteIngredientController;

    @Mock
    private InitialState initialState;

    private InitialView initialView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<Ingredient> ingredients = new ArrayList<>();
        CommonIngredientFactory ingredientFactory = new CommonIngredientFactory();
        Ingredient mango = ingredientFactory.create("mango", LocalDate.parse("2025-12-11"));
        Ingredient eggplant = ingredientFactory.create("eggplant", LocalDate.parse("2025-08-11"));
        ingredients.add(mango);
        ingredients.add(eggplant);

        initialViewModel = mock(InitialViewModel.class);
        initialState = mock(InitialState.class);
        when(initialState.getIngredients()).thenReturn(ingredients);
        initialViewModel.setState(initialState);
        when(initialViewModel.getState()).thenReturn(initialState);

        deleteIngredientController = mock(DeleteIngredientController.class);

        initialView = new InitialView(initialViewModel);
        initialView.setDeleteIngredientController(deleteIngredientController);
        PropertyChangeEvent event = new PropertyChangeEvent(initialViewModel, "ingredients", null,
                ingredients);
        initialView.propertyChange(event);
    }

    @Test
    void testAddIngredientButtonClick() {
        JButton addIngredientButton = findButtonByLabel("Add Ingredient");
        addIngredientButton.doClick();

        verify(deleteIngredientController).switchToAddIngredientView();
    }

    @Test
    void testGenerateRecipeButtonClick() {
        JButton generateRecipeButton = findButtonByLabel("Generate Recipe");
        generateRecipeButton.doClick();

        verify(deleteIngredientController).switchToRecipeView();
    }

    @Test
    void testPropertyChangeUpdatesIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        CommonIngredientFactory ingredientFactory = new CommonIngredientFactory();
        Ingredient apple = ingredientFactory.create("apple", LocalDate.parse("2025-12-11"));
        Ingredient orange = ingredientFactory.create("orange", LocalDate.parse("2025-08-11"));
        ingredients.add(apple);
        ingredients.add(orange);

        when(initialState.getIngredients()).thenReturn(ingredients);

        PropertyChangeEvent event = new PropertyChangeEvent(initialViewModel, "ingredients", null,
                ingredients);
        initialView.propertyChange(event);

        Component[] components = initialView.getComponents();
        assertTrue(components.length > 2, "InitialView should have at least 3 components");
    }

    @Test
    void testIngredientDeletion() throws FileNotFoundException {
        List<Ingredient> ingredients = initialState.getIngredients();
        Ingredient ingredient = ingredients.get(0);
        String ingredientName = ingredient.getName();

        JButton deleteButton = findDeleteButtonByIngredientName(ingredientName);
        deleteButton.doClick();

        verify(deleteIngredientController, times(1)).execute(eq(ingredients), eq(ingredient));
    }

    @Test
    void testGetViewName() {
        assertEquals(initialView.getViewName(), "initial");
    }

    @Test
    void testActionPerformedAddIngredient() {
        JButton addIngredientButton = (JButton) findButtonByLabel("Add Ingredient");
        addIngredientButton.doClick();

        verify(deleteIngredientController, times(1)).switchToAddIngredientView();
    }

    @Test
    void testActionPerformedGenerateRecipe() {
        JButton generateRecipeButton = (JButton) findButtonByLabel("Generate Recipe");
        generateRecipeButton.doClick();
        verify(deleteIngredientController, times(1)).switchToRecipeView();
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
        for (Component component : initialView.getComponents()) {
            if (component instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) component;
                Component view = scrollPane.getViewport().getView();
                if (view instanceof JPanel) {
                    JPanel ingredientsPanel = (JPanel) view;
                    for (Component subComponent : ingredientsPanel.getComponents()) {
                        if (subComponent instanceof JPanel) {
                            JPanel ingredientPanel = (JPanel) subComponent;
                            JLabel nameLabel = null;
                            JButton deleteButton = null;

                            for (Component ingredientComponent : ingredientPanel.getComponents()) {
                                if (ingredientComponent instanceof JLabel) {
                                    nameLabel = (JLabel) ingredientComponent;
                                } else if (ingredientComponent instanceof JButton) {
                                    deleteButton = (JButton) ingredientComponent;
                                }
                            }

                            if (nameLabel != null && nameLabel.getText().equals(name)) {
                                return deleteButton;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}