package interface_adapter.expirationwarning;
import entity.CommonIngredient;
import entity.Ingredient;
import interface_adapter.ExpirationWarning.ExpirationWarningController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.expired_food.ExpiredIngredientInteractor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExpirationWarningAdapterTest {

    private ExpirationWarningController controller;
    private ExpiredIngredientInteractor interactor;

    @BeforeEach
    public void setUp() {
        interactor = mock(ExpiredIngredientInteractor.class);
        controller = new ExpirationWarningController(interactor);
    }

    @Test
    public void testGetExpiredIngredients() {
        Ingredient ingredient1 = new CommonIngredient("Milk", LocalDate.parse("2023-12-01"));
        Ingredient ingredient2 = new CommonIngredient("Eggs", LocalDate.parse("2023-11-30"));
        List<Ingredient> expiredIngredients = Arrays.asList(ingredient1, ingredient2);

        when(interactor.execute()).thenReturn(expiredIngredients);

        List<Ingredient> result = controller.getExpiredIngredients();

        assertEquals(2, result.size());
        assertEquals("Milk", result.get(0).getName());
        assertEquals("Eggs", result.get(1).getName());
    }

    @Test
    public void testDeleteSelectedIngredients() {
        Ingredient ingredient1 = new CommonIngredient("Milk", LocalDate.parse("2023-12-01"));
        Ingredient ingredient2 = new CommonIngredient("Eggs", LocalDate.parse("2023-11-30"));

        doNothing().when(interactor).deleteIngredients(ingredient1);
        doNothing().when(interactor).deleteIngredients(ingredient2);

        controller.deleteSelectedIngredients(ingredient1);
        controller.deleteSelectedIngredients(ingredient2);

        verify(interactor, times(1)).deleteIngredients(ingredient1);
        verify(interactor, times(1)).deleteIngredients(ingredient2);
    }

    @Test
    public void testSwitchToInitialView() {
        doNothing().when(interactor).switchToInitialView();

        controller.switchToInitialView();

        verify(interactor, times(1)).switchToInitialView();
    }

    @Test
    public void testInteractorCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new ExpirationWarningController(null));
    }
}

