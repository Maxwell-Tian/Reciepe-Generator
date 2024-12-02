package view;

import data_access.InMemoryIngredientDataAccessObject;
import entity.CommonIngredientFactory;
import entity.Ingredient;
import entity.IngredientFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientViewModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientState;
import interface_adapter.addorcancelingredient.AddorCancelIngredientController;
import interface_adapter.addorcancelingredient.AddorCancelIngredientPresenter;
import interface_adapter.initial.InitialViewModel;
import interface_adapter.recipemanagement.RecipeManagementViewModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.addorcancelingredient.AddorCancelIngredientInteractor;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AddIngredientViewTest {
    private AddorCancelIngredientViewModel aocViewModel;
    private InitialViewModel initialViewModel;
    private RecipeManagementViewModel recipeManagementViewModel;
    private ViewManagerModel viewManagerModel;

    private AddorCancelIngredientPresenter aocPresenter;
    private AddorCancelIngredientInteractor aocInteractor;
    private AddorCancelIngredientController aocController;

    private IngredientFactory ingredientFactory;
    private InMemoryIngredientDataAccessObject ingredientDataAccessObject;

    private AddorCancelIngredientState aocState;
    private AddIngredientView addIngredientView;

    @BeforeEach
    void setUp() {
        aocViewModel = new AddorCancelIngredientViewModel();
        aocState = new AddorCancelIngredientState();

        ingredientFactory = new CommonIngredientFactory();
        ingredientDataAccessObject = new InMemoryIngredientDataAccessObject();

        initialViewModel = new InitialViewModel();
        recipeManagementViewModel = new RecipeManagementViewModel();
        viewManagerModel = new ViewManagerModel();


        aocPresenter = new AddorCancelIngredientPresenter(aocViewModel, initialViewModel,
                recipeManagementViewModel, viewManagerModel);
        aocInteractor = new AddorCancelIngredientInteractor(ingredientDataAccessObject,
                aocPresenter, ingredientFactory);
        aocController = new AddorCancelIngredientController(aocInteractor);

        addIngredientView = new AddIngredientView(aocViewModel);
        aocViewModel.setState(aocState);
        addIngredientView.setAddorCancelIngredientController(aocController);
    }

    @AfterEach
    void tearDown() {
        ingredientDataAccessObject = null;
    }

    @Test
    void testAddButtonUpdatesViewModel() {
        JTextField ingredientField = getTextFieldByName("ingredientnameInputField");
        JTextField expiryField = getTextFieldByName("expirydateInputField");
        JButton addButton = findButtonByLabel("add");

        ingredientField.setText("chicken");
        expiryField.setText("2025-12-01");
        Ingredient chicken = ingredientFactory.create("chicken", LocalDate.parse("2025-12-01"));

        addButton.doClick();

        assertEquals("chicken", aocViewModel.getState().getIngredientname());
        assertEquals("2025-12-01", aocViewModel.getState().getExpirydate());
        assertNull(aocViewModel.getState().getErrorMessage());
        assertEquals(initialViewModel.getViewName(), viewManagerModel.getState());
    }

    @Test
    void testCancelButtonResetsViewModel() {
        JTextField ingredientField = getTextFieldByName("ingredientnameInputField");
        JTextField expiryField = getTextFieldByName("expirydateInputField");
        JButton cancelButton = findButtonByLabel("cancel");

        ingredientField.setText("cheese");
        expiryField.setText("2025-09-01");
        Ingredient cheese = ingredientFactory.create("cheese", LocalDate.parse("2025-09-01"));

        cancelButton.doClick();
        assertFalse(initialViewModel.getState().getIngredients().contains(cheese));
    }

    private JButton findButtonByLabel(String label) {
        for (Component component : addIngredientView.getComponents()) {
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

    private JTextField getTextFieldByName(String fieldName) {
        try {
            Field field = addIngredientView.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return JTextField.class.cast(field.get(addIngredientView));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Unable to access field: " + fieldName, e);
        }
    }
}
