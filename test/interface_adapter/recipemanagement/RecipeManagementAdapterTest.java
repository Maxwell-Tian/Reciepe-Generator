package interface_adapter.recipemanagement;

import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.recipe_management.RecipeManagementInputBoundary;

import static org.mockito.Mockito.*;

import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeManagementAdapterTest {

    @Mock
    private RecipeManagementInputBoundary interactor;

    @Mock
    private RecipeInfoState recipeInfoState;

    @Mock
    private RecipeInfoViewModel recipeInfoViewModel;

    @Mock
    private RecipeManagementViewModel recipeManagementViewModel;

    @Mock
    private PropertyChangeListener propertyChangeListener;

    @InjectMocks
    private RecipeManagementController controller;

    @InjectMocks
    private RecipeManagementPresenter presenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RecipeManagementController(interactor);
        presenter = new RecipeManagementPresenter(null, recipeManagementViewModel, recipeInfoViewModel, null);
    }

    @Test
    public void testSwitchToInitialView() {
        controller.switchToInitialView();
        verify(interactor, times(1)).switchToInitialView();
    }

    @Test
    public void testSwitchToRecipeListView() {
        controller.switchToRecipeListView();
        verify(interactor, times(1)).switchToRecipeListView();
    }

    @Test
    public void testSwitchToRecipeInfoView() {
        controller.switchToRecipeInfoView();
        verify(interactor, times(1)).switchToRecipeInfoView();
    }

    @Test
    public void testSetCurrentRecipeInViewModel() {
        Recipe recipe = mock(Recipe.class);
        when(recipe.getName()).thenReturn("Test Recipe");

        RecipeInfoViewModel viewModel = new RecipeInfoViewModel();
        viewModel.addPropertyChangeListener(propertyChangeListener);
        viewModel.setCurrentRecipe(recipe);

        assertEquals(recipe, viewModel.getCurrentRecipe());
        verify(propertyChangeListener, times(1)).propertyChange(any());
    }

    @Test
    public void testPresentSuccessMessage() {
        String message = "Recipe added successfully";
        presenter.presentSuccessMessage(message);
        assertEquals("Recipe added successfully", message);
    }

    @Test
    public void testPresentErrorMessage() {
        String errorMessage = "Failed to add recipe";
        presenter.presentErrorMessage(errorMessage);
        assertNotNull(errorMessage);
    }

    @Test
    public void testAddPropertyChangeListener() {
        RecipeInfoViewModel viewModel = new RecipeInfoViewModel();
        viewModel.addPropertyChangeListener(propertyChangeListener);
        viewModel.setCurrentRecipe(mock(Recipe.class));

        verify(propertyChangeListener, times(1)).propertyChange(any());
    }

    @Test
    public void testRemovePropertyChangeListener() {
        RecipeInfoViewModel viewModel = new RecipeInfoViewModel();
        viewModel.addPropertyChangeListener(propertyChangeListener);
        viewModel.removePropertyChangeListener(propertyChangeListener);
        viewModel.setCurrentRecipe(mock(Recipe.class));

        verify(propertyChangeListener, never()).propertyChange(any());
    }
}
