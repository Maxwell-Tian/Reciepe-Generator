package use_case.expirationwarning;

import entity.CommonIngredient;
import entity.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.expired_food.ExpiredIngredientInteractor;
import use_case.expired_food.ExpiredIngredientOutputBoundary;
import use_case.expired_food.ExpiredIngredientUserDataAccessInterface;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExpirationWarningTest {
    private ExpiredIngredientInteractor interactor;
    private ExpiredIngredientUserDataAccessInterface dataAccess;
    private ExpiredIngredientOutputBoundary outputBoundary;

    @BeforeEach
    public void setUp() {
        dataAccess = mock(ExpiredIngredientUserDataAccessInterface.class);
        outputBoundary = mock(ExpiredIngredientOutputBoundary.class);
        interactor = new ExpiredIngredientInteractor(dataAccess, outputBoundary);
    }

    @Test
    public void testExecuteExpiredIngredients() {
        Ingredient ingredient1 = new CommonIngredient("Milk", LocalDate.parse("2023-12-01"));
        Ingredient ingredient2 = new CommonIngredient("Eggs", LocalDate.parse("2023-11-30"));
        List<Ingredient> allIngredients = Arrays.asList(ingredient1, ingredient2);

        when(outputBoundary.getCurrentIngredients()).thenReturn(allIngredients);

        List<Ingredient> expiredIngredients = interactor.execute();

        assertEquals(2, expiredIngredients.size());
        assertEquals("Milk", expiredIngredients.get(0).getName());
        assertEquals("Eggs", expiredIngredients.get(1).getName());
    }

    @Test
    public void testDeleteExpiredIngredient() {
        Ingredient ingredient = new CommonIngredient("Milk", LocalDate.parse("2023-12-01"));

        doNothing().when(dataAccess).deleteIngredient(ingredient);
        doNothing().when(outputBoundary).deleteIngredient(ingredient);

        interactor.deleteIngredients(ingredient);

        verify(dataAccess, times(1)).deleteIngredient(ingredient);
        verify(outputBoundary, times(1)).deleteIngredient(ingredient);
    }

    @Test
    public void testSwitchToInitialView() {
        doNothing().when(outputBoundary).switchToInitialView();

        interactor.switchToInitialView();

        verify(outputBoundary, times(1)).switchToInitialView();
    }
}
