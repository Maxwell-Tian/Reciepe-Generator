package interface_adapter.initial;

import entity.CommonIngredientFactory;
import entity.Ingredient;
import entity.IngredientFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for InitialState and InitialViewModel.
 */
class InitialInterfaceAdapterTest {

    private InitialState initialState;
    private InitialViewModel initialViewModel;

    @BeforeEach
    void setUp() {
        initialState = new InitialState() {};
        initialViewModel = new InitialViewModel();
        initialViewModel.setState(initialState);
    }

    @AfterEach
    void tearDown() {
        initialState.setIngredients(null);
    }

    @Test
    void testAddIngredient() {
        IngredientFactory factory = new CommonIngredientFactory();
        Ingredient egg = factory.create("egg", LocalDate.parse("2024-12-13"));
        initialState.addIngredient(egg);

        assertTrue(initialState.getIngredients().contains(egg));
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
    void testSetIngredients() {
        IngredientFactory factory = new CommonIngredientFactory();
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient pork = factory.create("pork", LocalDate.parse("2024-12-11"));
        ingredients.add(pork);
        initialState.setIngredients(ingredients);

        assertEquals(ingredients, initialState.getIngredients());
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

