package use_case.delete_ingredient;

import data_access.InMemoryIngredientDataAccessObject;
import entity.*;
import org.junit.Test;


import java.io.FileNotFoundException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteIngredientTest {

    @Test
    public void successTest() throws FileNotFoundException {
        InMemoryIngredientDataAccessObject ingredientRepository = new InMemoryIngredientDataAccessObject();

        IngredientFactory factory = new CommonIngredientFactory();
        Ingredient ingredient = factory.create("tomato", LocalDate.parse("2024-12-11"));
        ingredientRepository.save(ingredient);

        DeleteIngredientInputData inputData = new DeleteIngredientInputData(
                ingredientRepository.getCurrentIngredients(), ingredient);

        DeleteIngredientOutputBoundary successPresenter = new DeleteIngredientOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteIngredientOutputData outputData) {
                assertEquals(ingredientRepository.getCurrentIngredients(), outputData.getIngredients());
                assertEquals(ingredient, outputData.getIngredient());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToAddIngredientView() {
                // This is expected.
            }

            @Override
            public void switchToRecipeView() {
                // This is expected.
            }
        };
        DeleteIngredientInputBoundary interactor = new DeleteIngredientInteractor(ingredientRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void testSwitchToAddIngredientView() {
        class CustomPresenter implements DeleteIngredientOutputBoundary {
            private boolean addIngredientViewSwitched = false;

            @Override
            public void prepareSuccessView(DeleteIngredientOutputData outputData) {
                fail("prepareSuccessView should not be called in this test.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("prepareFailView should not be called in this test.");
            }

            @Override
            public void switchToAddIngredientView() {
                addIngredientViewSwitched = true;
            }

            @Override
            public void switchToRecipeView() {
                // Not expected in this test
            }

            public boolean isAddIngredientViewSwitched() {
                return addIngredientViewSwitched;
            }
        }

        CustomPresenter presenter = new CustomPresenter();
        DeleteIngredientInteractor interactor = new DeleteIngredientInteractor(
                new InMemoryIngredientDataAccessObject(),
                presenter
        );

        interactor.switchToAddIngredientView();

        assertTrue(presenter.isAddIngredientViewSwitched(), "Switch to Add Ingredient View was not invoked.");
    }

    @Test
    public void testSwitchToRecipeView() {
        class CustomPresenter implements DeleteIngredientOutputBoundary {
            private boolean recipeViewSwitched = false;

            @Override
            public void prepareSuccessView(DeleteIngredientOutputData outputData) {
                fail("prepareSuccessView should not be called in this test.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("prepareFailView should not be called in this test.");
            }

            @Override
            public void switchToAddIngredientView() {
                // Not expected in this test
            }

            @Override
            public void switchToRecipeView() {
                recipeViewSwitched = true;
            }

            public boolean isRecipeViewSwitched() {
                return recipeViewSwitched;
            }
        }

        CustomPresenter presenter = new CustomPresenter();
        DeleteIngredientInteractor interactor = new DeleteIngredientInteractor(
                new InMemoryIngredientDataAccessObject(),
                presenter
        );

        interactor.switchToRecipeView();

        assertTrue(presenter.isRecipeViewSwitched(), "Switch to Add Ingredient View was not invoked.");
    }
}
