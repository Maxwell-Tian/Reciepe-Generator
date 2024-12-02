package use_case.recipemanagement;

import entity.Recipe;
import entity.RecipeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.recipe_management.RecipeManagementInputData;
import use_case.recipe_management.RecipeManagementInteractor;
import use_case.recipe_management.RecipeManagementOutputBoundary;
import use_case.recipe_management.RecipeManagementOutputData;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeManagementTest {

    @Mock
    private RecipeManagementOutputBoundary outputBoundary;

    @Mock
    private RecipeFactory recipeFactory;

    @InjectMocks
    private RecipeManagementInteractor interactor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new RecipeManagementInteractor(outputBoundary);
    }

    @Test
    public void testSwitchToInitialView() {
        interactor.switchToInitialView();
        verify(outputBoundary, times(1)).switchToInitialView();
    }

    @Test
    public void testSwitchToRecipeListView() {
        interactor.switchToRecipeListView();
        verify(outputBoundary, times(1)).switchToRecipeListView();
    }

    @Test
    public void testSwitchToRecipeInfoView() {
        interactor.switchToRecipeInfoView();
        verify(outputBoundary, times(1)).switchToRecipeInfoView();
    }

    @Test
    public void testGetRecipesFromInputData() {
        RecipeManagementInputData inputData = new RecipeManagementInputData("Dessert", new HashMap<>());
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe1 = mock(Recipe.class);
        when(recipe1.getName()).thenReturn("Cake");
        recipes.add(recipe1);
        inputData.setRecipes(recipes);

        assertEquals(1, inputData.getRecipes().size());
        assertEquals("Cake", inputData.getRecipes().get(0).getName());
    }

    @Test
    public void testSetUserIngredients() {
        Map<String, LocalDate> ingredients = new HashMap<>();
        ingredients.put("Flour", LocalDate.now());

        RecipeManagementInputData inputData = new RecipeManagementInputData("Dessert", ingredients);
        assertEquals(1, inputData.getUserIngredients().size());
        assertTrue(inputData.getUserIngredients().containsKey("Flour"));
    }

    @Test
    public void testRecipeManagementOutputData() {
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = mock(Recipe.class);
        when(recipe.getName()).thenReturn("Pancake");
        recipes.add(recipe);
        String message = "Success";
        Map<String, List<LocalDate>> recommendations = new HashMap<>();

        RecipeManagementOutputData outputData = new RecipeManagementOutputData(recipes, message, recommendations);
        assertEquals("Success", outputData.getMessage());
        assertEquals(1, outputData.getRecipes().size());
        assertEquals("Pancake", outputData.getRecipes().get(0).getName());
    }

    @Test
    public void testPresentRecipes() {
        Recipe recipe = mock(Recipe.class);
        when(recipe.getName()).thenReturn("Bread");
        List<Recipe> recipes = Arrays.asList(recipe);
        outputBoundary.presentRecipes(recipes);
        verify(outputBoundary, times(1)).presentRecipes(recipes);
    }

    @Test
    public void testPresentSuccessMessage() {
        String message = "Recipe added successfully";
        outputBoundary.presentSuccessMessage(message);
        verify(outputBoundary, times(1)).presentSuccessMessage(message);
    }

    @Test
    public void testPresentErrorMessage() {
        String errorMessage = "Failed to add recipe";
        outputBoundary.presentErrorMessage(errorMessage);
        verify(outputBoundary, times(1)).presentErrorMessage(errorMessage);
    }

    @Test
    public void testPresentRecommendations() {
        Map<String, List<String>> recommendations = new HashMap<>();
        recommendations.put("Breakfast", Arrays.asList("Pancake", "Omelette"));
        outputBoundary.presentRecommendations(recommendations);
        verify(outputBoundary, times(1)).presentRecommendations(recommendations);
    }

    @Test
    public void testCreateRecipeUsingFactory() {
        List<String> ingredients = Arrays.asList("Flour", "Sugar", "Eggs");
        when(recipeFactory.create("Cake", ingredients)).thenReturn(mock(Recipe.class));

        Recipe recipe = recipeFactory.create("Cake", ingredients);
        assertNotNull(recipe);
        verify(recipeFactory, times(1)).create("Cake", ingredients);
    }
}
