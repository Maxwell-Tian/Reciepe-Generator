package interface_adapter.deleteingredient;

import data_access.InMemoryIngredientDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientViewModel;
import interface_adapter.initial.InitialViewModel;
import interface_adapter.recipemanagement.RecipeManagementViewModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import use_case.delete_ingredient.*;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class DeleteIngredientInterfaceAdapterTest {
    private InitialViewModel initialViewModel;
    private AddorCancelIngredientViewModel aocIngredientViewModel;
    private RecipeManagementViewModel recipeManagementViewModel;
    private ViewManagerModel viewManagerModel;

    private DeleteIngredientPresenter presenter;
    private DeleteIngredientInputBoundary deleteIngredientInteractor;
    private DeleteIngredientController controller;

    private InMemoryIngredientDataAccessObject ingredientDataAccessObject;

    @BeforeEach
    void setUp() {
        initialViewModel = new InitialViewModel();
        aocIngredientViewModel = new AddorCancelIngredientViewModel();
        recipeManagementViewModel = new RecipeManagementViewModel();
        viewManagerModel = new ViewManagerModel();
        ingredientDataAccessObject = new InMemoryIngredientDataAccessObject();

        presenter = new DeleteIngredientPresenter(
                initialViewModel,
                aocIngredientViewModel,
                recipeManagementViewModel,
                viewManagerModel
        );

        deleteIngredientInteractor = new DeleteIngredientInteractor(ingredientDataAccessObject, presenter);
        controller = new DeleteIngredientController(deleteIngredientInteractor);
    }

    @AfterEach
    void tearDown() {
        ingredientDataAccessObject = null;
    }

    @Test
    void testControllerExecuteDeletesIngredientSuccessfully() throws FileNotFoundException {
        IngredientFactory factory = new CommonIngredientFactory();
        Ingredient tomato = factory.create("tomato", LocalDate.parse("2026-12-11"));

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(tomato);
        controller.execute(ingredients, tomato);

        assertFalse(ingredientDataAccessObject.getCurrentIngredients().contains(tomato),
                "Ingredient 'tomato' should be deleted from the list");
    }

    @Test
    void testControllerSwitchToAddIngredientView() {
        controller.switchToAddIngredientView();
        assertEquals(aocIngredientViewModel.getViewName(), viewManagerModel.getState());
    }

    @Test
    void testControllerSwitchToRecipeView() {
        controller.switchToRecipeView();
        assertEquals(recipeManagementViewModel.getViewName(), viewManagerModel.getState());
    }


    @Test
    void testPrepareSuccessView() {
        IngredientFactory factory = new CommonIngredientFactory();
        Ingredient beef = factory.create("beef", LocalDate.parse("2027-11-11"));

        DeleteIngredientOutputData response = new
                DeleteIngredientOutputData(ingredientDataAccessObject.getAllIngredients(), beef, false);
        presenter.prepareSuccessView(response);

        assertFalse(ingredientDataAccessObject.getCurrentIngredients().contains(beef),
                "Ingredient should be deleted");

        assertEquals(initialViewModel.getViewName(), viewManagerModel.getState());
    }

    @Test
    void testPrepareFailView() {
        String errorMessage = "Ingredient not found";
        presenter.prepareFailView(errorMessage);

        assertEquals(initialViewModel.getState().getErrorMessage(), errorMessage);
    }

    @Test
    void testSwitchToAddIngredientView() {
        presenter.switchToAddIngredientView();

        assertEquals(aocIngredientViewModel.getViewName(), viewManagerModel.getState());
    }

    @Test
    void testSwitchToRecipeView() {
        presenter.switchToRecipeView();

        assertEquals(recipeManagementViewModel.getViewName(), viewManagerModel.getState());
    }

}