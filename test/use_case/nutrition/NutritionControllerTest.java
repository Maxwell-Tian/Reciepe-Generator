package use_case.nutrition;

import entity.Ingredient;
import interface_adapter.NutritionViewModel.NutritionController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.searchNutrition.SearchNutritionInputBoundary;

import static org.mockito.Mockito.verify;

class NutritionControllerTest {
    private NutritionController controller;
    private SearchNutritionInputBoundary mockInteractor;
    private Ingredient mockIngredient;

    @BeforeEach
    void setUp() {
        mockInteractor = Mockito.mock(SearchNutritionInputBoundary.class);
        controller = new NutritionController(mockInteractor);
        mockIngredient = Mockito.mock(Ingredient.class);
    }

    @Test
    void testExecuteCallsInteractorWithIngredient() {
        controller.execute(mockIngredient);
        verify(mockInteractor).getNutrition(mockIngredient);
    }

    @Test
    void testSwitchToNutritionView() {
        controller.switchToNutritionView();
        verify(mockInteractor).switchToNutritionView();
    }

    @Test
    void testSwitchToInitialView() {
        controller.switchToInitialView();
        verify(mockInteractor).switchToInitialView();
    }
}
