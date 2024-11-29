package use_case.addorcancelingredient;

import data_access.InMemoryIngredientDataAccessObject;
import entity.*;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class AddorCancelIngredientTest {

    @Test
    public void successTest() {
        AddorCancelIngredientInputData inputData = new AddorCancelIngredientInputData("tomato", "2025-02-22");
        AddorCancelIngredientIngredientDataAccessInterface ingredientRepository = new InMemoryIngredientDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        AddorCancelIngredientOutputBoundary successPresenter = new AddorCancelIngredientOutputBoundary() {
            @Override
            public void prepareSuccessView(AddorCancelIngredientOutputData outputdata) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("tomato", outputdata.getIngredientname());
                assertTrue(ingredientRepository.existsByIngredientName("tomato"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToInitialView() {
                // This is expected
            }
        };

        AddorCancelIngredientInputBoundary interactor = new AddorCancelIngredientInteractor(ingredientRepository, successPresenter, new CommonIngredientFactory());
        interactor.execute(inputData);
    }

    @Test
    public void failureExpiredTest() {
        AddorCancelIngredientInputData inputData = new AddorCancelIngredientInputData("tomato", "2005-02-22");
        AddorCancelIngredientIngredientDataAccessInterface ingredientRepository = new InMemoryIngredientDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        AddorCancelIngredientOutputBoundary failurePresenter = new AddorCancelIngredientOutputBoundary() {
            @Override
            public void prepareSuccessView(AddorCancelIngredientOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Expiry date has already passed.", error);
            }

            @Override
            public void switchToInitialView() {
                // This is expected
            }
        };

        AddorCancelIngredientInputBoundary interactor = new AddorCancelIngredientInteractor(ingredientRepository, failurePresenter, new CommonIngredientFactory());
        interactor.execute(inputData);
    }

    @Test
    public void failureUserExistsTest() {
        AddorCancelIngredientInputData inputData = new AddorCancelIngredientInputData("tomato", "2024-12-5");
        AddorCancelIngredientIngredientDataAccessInterface ingredientRepository = new InMemoryIngredientDataAccessObject();

        // Add Paul to the repo so that when we check later they already exist
        IngredientFactory factory = new CommonIngredientFactory();
        Ingredient ingredient = factory.create("tomato", LocalDate.parse("2024-12-06"));
        ingredientRepository.save(ingredient);

        // This creates a presenter that tests whether the test case is as we expect.
        AddorCancelIngredientOutputBoundary failurePresenter = new AddorCancelIngredientOutputBoundary() {
            @Override
            public void prepareSuccessView(AddorCancelIngredientOutputData ingredient) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Ingredient already exists.", error);
            }

            @Override
            public void switchToInitialView() {
                // This is expected
            }
        };

        AddorCancelIngredientInputBoundary interactor = new AddorCancelIngredientInteractor(ingredientRepository, failurePresenter, new CommonIngredientFactory());
        interactor.execute(inputData);
    }
}
