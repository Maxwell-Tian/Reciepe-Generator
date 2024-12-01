package interface_adapter.addorcancelingredient;

import entity.CommonIngredientFactory;
import data_access.InMemoryIngredientDataAccessObject;
import entity.Ingredient;
import entity.IngredientFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialViewModel;
import interface_adapter.recipemanagement.RecipeManagementViewModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.addorcancelingredient.AddorCancelIngredientInputBoundary;
import use_case.addorcancelingredient.AddorCancelIngredientInteractor;
import use_case.addorcancelingredient.AddorCancelIngredientOutputData;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddorCancelIngredientInterfaceAdapterTest {
    private AddorCancelIngredientViewModel aocIngredientViewModel;
    private AddorCancelIngredientState aocIngredientState;
    private InitialViewModel initialViewModel;
    private RecipeManagementViewModel recipeManagementViewModel;
    private ViewManagerModel viewManagerModel;

    private AddorCancelIngredientPresenter aocIngredientPresenter;
    private AddorCancelIngredientInputBoundary aocIngredientInteractor;
    private AddorCancelIngredientController aocIngredientController;

    private InMemoryIngredientDataAccessObject ingredientDataAccessObject;
    private IngredientFactory ingredientFactory;

    @BeforeEach
    void setUp() {
        aocIngredientViewModel = new AddorCancelIngredientViewModel();
        aocIngredientState = new AddorCancelIngredientState();
        aocIngredientViewModel.setState(aocIngredientState);

        initialViewModel = new InitialViewModel();
        recipeManagementViewModel = new RecipeManagementViewModel();

        viewManagerModel = new ViewManagerModel();
        ingredientDataAccessObject = new InMemoryIngredientDataAccessObject();
        ingredientFactory = new CommonIngredientFactory();

        aocIngredientPresenter = new AddorCancelIngredientPresenter(aocIngredientViewModel,
                initialViewModel,recipeManagementViewModel, viewManagerModel);

        aocIngredientInteractor = new AddorCancelIngredientInteractor(ingredientDataAccessObject,
                aocIngredientPresenter, ingredientFactory);

        aocIngredientController = new AddorCancelIngredientController(aocIngredientInteractor);
    }

    @AfterEach
    void tearDown() {
        ingredientDataAccessObject = null;
        aocIngredientState.setIngredientname(null);
    }

    @Test
    void testControllerExecuteAddIngredientSuccessfully() throws FileNotFoundException {
        aocIngredientController.execute("milk", "2029-04-11");

        assertTrue(ingredientDataAccessObject.existsByIngredientName("milk"));
    }

    @Test
    void testControllerSwitchToInitialView() {
        aocIngredientController.switchToInitialView();

        assertEquals(initialViewModel.getViewName(), viewManagerModel.getState());
    }

    @Test
    void testPrepareSuccessView() throws FileNotFoundException {
        Ingredient onion = ingredientFactory.create("onion", LocalDate.parse("2026-03-20"));

        AddorCancelIngredientOutputData response = new AddorCancelIngredientOutputData(onion, false);
        aocIngredientPresenter.prepareSuccessView(response);

        assertTrue(initialViewModel.getState().getIngredients().contains(onion));
        assertEquals(initialViewModel.getViewName(), viewManagerModel.getState());
    }

    @Test
    void testPrepareFailView() {
        String errorMessage = "Ingredient cannot be added";
        aocIngredientPresenter.prepareFailView(errorMessage);

        assertEquals(errorMessage, aocIngredientViewModel.getState().getErrorMessage());
    }

    @Test
    void testSwitchToInitialView() {
        aocIngredientPresenter.switchToInitialView();

        assertEquals(initialViewModel.getViewName(), viewManagerModel.getState());
    }

    @Test
    void testSetIngredientName() {
        aocIngredientState.setIngredientname("banana");
        assertEquals("banana", aocIngredientState.getIngredientname());
    }

    @Test
    void testSetExpirayDate() {
        String date =  "2031-10-01";
        aocIngredientState.setExpirydate(date);

        assertEquals(date, aocIngredientState.getExpirydate());
    }

    @Test
    void testSetError() {
        aocIngredientState.setError("Test error");

        assertEquals("Test error", aocIngredientState.getErrorMessage());
    }

    @Test
    void TestToString() {
        aocIngredientState.setIngredientname("banana");
        aocIngredientState.setExpirydate("2026-03-20");

        assertEquals("AddorCancelIngredientState{ingredientname='banana', expirydate='2026-03-20'}",
                aocIngredientState.toString());
    }
}
