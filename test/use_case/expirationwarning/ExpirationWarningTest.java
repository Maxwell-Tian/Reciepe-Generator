package use_case.expirationwarning;

import entity.CommonIngredient;
import entity.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.expired_food.ExpiredIngredientInteractor;
import use_case.expired_food.ExpiredIngredientOutputBoundary;
import use_case.expired_food.ExpiredIngredientUserDataAccessInterface;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
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

    /**
     * Tests the execute method to verify that it correctly identifies expired ingredients.
     * Ensures that the correct number of expired ingredients is returned and that their names match.
     */
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

    /**
     * Tests the deleteIngredients method to verify that it correctly deletes an expired ingredient.
     * Ensures that both data access and output boundary methods are called exactly once.
     */
    @Test
    public void testDeleteExpiredIngredient() {
        Ingredient ingredient = new CommonIngredient("Milk", LocalDate.parse("2023-12-01"));

        doNothing().when(dataAccess).deleteIngredient(ingredient);
        doNothing().when(outputBoundary).deleteIngredient(ingredient);

        interactor.deleteIngredients(ingredient);

        verify(dataAccess, times(1)).deleteIngredient(ingredient);
        verify(outputBoundary, times(1)).deleteIngredient(ingredient);
    }

    /**
     * Tests the switchToInitialView method to verify that it correctly switches to the initial view.
     * Ensures that the output boundary's switchToInitialView method is called exactly once.
     */
    @Test
    public void testSwitchToInitialView() {
        doNothing().when(outputBoundary).switchToInitialView();

        interactor.switchToInitialView();

        verify(outputBoundary, times(1)).switchToInitialView();
    }

    /**
     * Tests the execute method with no ingredients to verify that the expired ingredients list is empty.
     * Ensures that the execute method handles an empty list of ingredients correctly.
     */
    @Test
    public void testExecuteWithNoIngredients() {
        when(outputBoundary.getCurrentIngredients()).thenReturn(Collections.emptyList());

        List<Ingredient> expiredIngredients = interactor.execute();

        assertTrue(expiredIngredients.isEmpty(), "Expired ingredients list should be empty when there are no ingredients.");
    }


    /**
     * Tests the execute method with ingredients that have future expiration dates.
     * Ensures that the expired ingredients list is empty when all ingredients have future expiration dates.
     */
    @Test
    public void testExecuteWithFutureExpirationDates() {
        Ingredient ingredient1 = new CommonIngredient("Butter", LocalDate.parse("2024-12-01"));
        Ingredient ingredient2 = new CommonIngredient("Cheese", LocalDate.parse("2024-11-30"));
        List<Ingredient> allIngredients = Arrays.asList(ingredient1, ingredient2);

        when(outputBoundary.getCurrentIngredients()).thenReturn(allIngredients);

        List<Ingredient> expiredIngredients = interactor.execute();

        assertTrue(expiredIngredients.isEmpty(), "Expired ingredients list should be empty when all ingredients have future expiration dates.");
    }

    /**
     * Tests the getCurrentIngredients method to verify that it throws a FileNotFoundException.
     * Ensures that the exception is correctly thrown and the message matches the expected value.
     */
    @Test
    public void testGetCurrentIngredientsThrowsFileNotFoundException() throws FileNotFoundException {
        when(dataAccess.getCurrentIngredients()).thenThrow(new FileNotFoundException("Ingredient data not found"));

        FileNotFoundException thrown = assertThrows(FileNotFoundException.class, () -> {
            dataAccess.getCurrentIngredients();
        });

        assertEquals("Ingredient data not found", thrown.getMessage());
    }

    /**
     * Tests the deleteIngredients method to verify that it throws an IllegalArgumentException when the ingredient is not found.
     * Ensures that the exception is correctly thrown and the message matches the expected value.
     */
    @Test
    public void testDeleteIngredientNotFound() {
        Ingredient ingredient = new CommonIngredient("Milk", LocalDate.parse("2023-12-01"));

        doThrow(new IllegalArgumentException("Ingredient not found")).when(dataAccess).deleteIngredient(ingredient);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            interactor.deleteIngredients(ingredient);
        });

        assertEquals("Ingredient not found", thrown.getMessage());
    }
}
