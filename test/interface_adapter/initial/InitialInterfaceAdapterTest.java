package interface_adapter.initial;

import data.txtConnector;
import data_access.InMemoryIngredientDataAccessObject;
import entity.CommonIngredientFactory;
import entity.Ingredient;
import entity.IngredientFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for InitialState and InitialViewModel.
 */
class InitialInterfaceAdapterTest {

    private InitialState initialState;
    private InitialViewModel initialViewModel;
    private txtConnector mockDataAccessObject;

    @BeforeEach
    void setUp() {
        mockDataAccessObject = mock(txtConnector.class);
        initialState = new InitialState() {};
        initialViewModel = new InitialViewModel();
        initialViewModel.setState(initialState);
    }

    @Test
    void testAddIngredient() {
        IngredientFactory factory = new CommonIngredientFactory();
        Ingredient ingredient = factory.create("egg", LocalDate.parse("2024-12-13"));

        initialState.addIngredient(ingredient);

        assertTrue(initialState.getIngredients().contains(ingredient));
    }

    @Test
    void testDeleteIngredient() {
        IngredientFactory factory = new CommonIngredientFactory();
        Ingredient ingredient = factory.create("tomato", LocalDate.parse("2024-12-11"));
        initialState.addIngredient(ingredient);

        initialState.deleteIngredient(ingredient);

        assertFalse(initialState.getIngredients().contains(ingredient));
    }

    @Test
    void testSetError() {
        initialState.setError("Test error");

        assertEquals("Test error", initialState.getErrorMessage());
    }

    @Test
    void testViewModelInitialization() {
        String viewName = initialViewModel.getViewName();
        InitialState state = initialViewModel.getState();

        assertEquals("initial", viewName);
        assertNotNull(state);
    }

    @Test
    void testViewModelLabels() {
        assertEquals("Your Fridge", InitialViewModel.TITLE_LABEL);
        assertEquals("Add Ingredient", InitialViewModel.ADD_INGREDIENT_BUTTON_LABEL);
        assertEquals("Generate Recipe", InitialViewModel.GENERATE_RECIPE_BUTTON_LABEL);
    }
}

