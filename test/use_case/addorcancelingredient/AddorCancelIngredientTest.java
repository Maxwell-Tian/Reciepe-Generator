package use_case.addorcancelingredient;

import data_access.InMemoryIngredientDataAccessObject;
import entity.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
//                assertEquals(true, outputdata)
//                assertTrue(ingredientRepository.existsByIngredientName("tomato"));
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
//        interactor.execute(inputData);
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
//        interactor.execute(inputData);
    }

    @Test
    public void failureUserExistsTest() {
        AddorCancelIngredientInputData inputData = new AddorCancelIngredientInputData("tomato", "2024-12-05");
        assertTrue("Expected getValid() to return true for a valid date", inputData.getValid());
        assertEquals("tomato", inputData.getIngredientname());
        assertEquals(LocalDate.parse("2024-12-05"), inputData.getExpirydate());

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
//        interactor.execute(inputData);

    }
    @Test
    public void switchToInitialViewTest() {
        AddorCancelIngredientOutputBoundary mockPresenter = mock(AddorCancelIngredientOutputBoundary.class);
        AddorCancelIngredientIngredientDataAccessInterface mockRepository = new InMemoryIngredientDataAccessObject();
        IngredientFactory factory = new CommonIngredientFactory();

        AddorCancelIngredientInputBoundary interactor = new AddorCancelIngredientInteractor(mockRepository, mockPresenter, factory);

        AddorCancelIngredientInputData inputData = new AddorCancelIngredientInputData("tomato", "2025-02-22");

        // Act
        interactor.switchToInitialView();

        // Assert
        verify(mockPresenter).switchToInitialView();
    }

    @Test
    public void testGetIngredient() {
        // Arrange: Create a sample Ingredient
        Ingredient ingredient = new CommonIngredientFactory().create("tomato", LocalDate.of(2025, 2, 22));

        // Act: Create AddorCancelIngredientOutputData with the Ingredient
        AddorCancelIngredientOutputData outputData = new AddorCancelIngredientOutputData(ingredient, false);

        // Assert: Verify the getIngredient method returns the correct Ingredient
        assertEquals(ingredient, outputData.getIngredient());
    }

    @Test
    public void testIngredientExists() throws FileNotFoundException {
        AddorCancelIngredientInputData inputData = new AddorCancelIngredientInputData("tomato", "2025-02-22");
        AddorCancelIngredientIngredientDataAccessInterface ingredientRepository = new InMemoryIngredientDataAccessObject();

        // Add "tomato" to the repository first
        IngredientFactory factory = new CommonIngredientFactory();
        Ingredient ingredient = factory.create("tomato", LocalDate.parse("2025-02-22"));
        ingredientRepository.save(ingredient);

        AddorCancelIngredientOutputBoundary failurePresenter = new AddorCancelIngredientOutputBoundary() {
            @Override
            public void prepareSuccessView(AddorCancelIngredientOutputData outputdata) {
                fail("Should not reach success view");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Ingredient already exists.", error);
            }

            @Override
            public void switchToInitialView() {}
        };

        AddorCancelIngredientInputBoundary interactor = new AddorCancelIngredientInteractor(ingredientRepository, failurePresenter, new CommonIngredientFactory());
        interactor.execute(inputData);  // This will hit the ingredient exists branch
    }

    @Test
    public void testInvalidDate() throws FileNotFoundException {
        AddorCancelIngredientInputData inputData = new AddorCancelIngredientInputData("tomato", "invalid-date");
        AddorCancelIngredientIngredientDataAccessInterface ingredientRepository = new InMemoryIngredientDataAccessObject();

        AddorCancelIngredientOutputBoundary failurePresenter = new AddorCancelIngredientOutputBoundary() {
            @Override
            public void prepareSuccessView(AddorCancelIngredientOutputData outputdata) {
                fail("Should not reach success view");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid date.", error);
            }

            @Override
            public void switchToInitialView() {}
        };

        AddorCancelIngredientInputBoundary interactor = new AddorCancelIngredientInteractor(ingredientRepository, failurePresenter, new CommonIngredientFactory());
        interactor.execute(inputData);  // This will hit the invalid date branch
    }

    @Test
    public void testExpiryDatePassed() throws FileNotFoundException {
        AddorCancelIngredientInputData inputData = new AddorCancelIngredientInputData("tomato", "2020-01-01");
        AddorCancelIngredientIngredientDataAccessInterface ingredientRepository = new InMemoryIngredientDataAccessObject();

        AddorCancelIngredientOutputBoundary failurePresenter = new AddorCancelIngredientOutputBoundary() {
            @Override
            public void prepareSuccessView(AddorCancelIngredientOutputData outputdata) {
                fail("Should not reach success view");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Expiry date has already passed.", error);
            }

            @Override
            public void switchToInitialView() {}
        };

        AddorCancelIngredientInputBoundary interactor = new AddorCancelIngredientInteractor(ingredientRepository, failurePresenter, new CommonIngredientFactory());
        interactor.execute(inputData);  // This will hit the expiry date passed branch
    }

    @Test
    public void testSuccess() throws FileNotFoundException {
        AddorCancelIngredientInputData inputData = new AddorCancelIngredientInputData("tomato", "2025-02-22");
        AddorCancelIngredientIngredientDataAccessInterface ingredientRepository = new InMemoryIngredientDataAccessObject();

        AddorCancelIngredientOutputBoundary successPresenter = new AddorCancelIngredientOutputBoundary() {
            @Override
            public void prepareSuccessView(AddorCancelIngredientOutputData outputdata) {
                assertEquals("tomato", outputdata.getIngredientname());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Should not reach fail view");
            }

            @Override
            public void switchToInitialView() {}
        };

        AddorCancelIngredientInputBoundary interactor = new AddorCancelIngredientInteractor(ingredientRepository, successPresenter, new CommonIngredientFactory());
        interactor.execute(inputData);  // This will hit the success branch
    }

}
