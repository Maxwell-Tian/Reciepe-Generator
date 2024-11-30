package use_case.expired_food;

import data_access.ExpiringIngredientDataAccess;
import entity.*;
import org.junit.jupiter.api.Test;
import interface_adapter.ExpirationWarning.ExpirationWarningController;
import use_case.expired_food.CheckExpiredIngredientInteractor;
import use_case.expired_food.CheckExpiredIngredientUserDataAccessInterface;
import data_access.InMemoryIngredientDataAccessObject;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class expiredtest {

    @Test
    void testGetExpiredIngredients() {
        // Setting up ingredients - one expired, one not expired
        Ingredient expiredIngredient = new CommonIngredient("Milk", LocalDate.now().minusDays(1));
        Ingredient freshIngredient = new CommonIngredient("Eggs", LocalDate.now().plusDays(5));

        // Setting up in-memory data access with ingredients
        ExpiringIngredientDataAccess ingredientRepository = new ExpiringIngredientDataAccess();
        ingredientRepository.setIngredients(List.of(expiredIngredient, freshIngredient));

        // Creating interactor and controller
        CheckExpiredIngredientInteractor interactor = new CheckExpiredIngredientInteractor(ingredientRepository);
        ExpirationWarningController controller = new ExpirationWarningController(interactor);

        // Executing test
        List<Ingredient> expiredIngredients = controller.getExpiredIngredients();

        // Assertions
        assertEquals(1, expiredIngredients.size());
        assertEquals("Milk", expiredIngredients.get(0).getName());
    }

    @Test
    void testDeleteSelectedIngredients() {
        // Setting up ingredients - one expired, one not expired
        Ingredient expiredIngredient = new CommonIngredient("Milk", LocalDate.now().minusDays(1));
        Ingredient freshIngredient = new CommonIngredient("Eggs", LocalDate.now().plusDays(5));

        // Setting up in-memory data access with ingredients
        ExpiringIngredientDataAccess ingredientRepository = new ExpiringIngredientDataAccess();
        ingredientRepository.setIngredients(new ArrayList<>(List.of(expiredIngredient, freshIngredient)));

        // Creating interactor and controller
        CheckExpiredIngredientInteractor interactor = new CheckExpiredIngredientInteractor(ingredientRepository);
        ExpirationWarningController controller = new ExpirationWarningController(interactor);

        // Simulating user selection for deletion
        controller.deleteSelectedIngredients(List.of(expiredIngredient));

        // Assertions
        List<Ingredient> remainingIngredients = ingredientRepository.getAllIngredients();
        assertEquals(1, remainingIngredients.size());
        assertEquals("Eggs", remainingIngredients.get(0).getName());
    }
}
