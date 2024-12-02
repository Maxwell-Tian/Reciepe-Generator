package use_case.recipe_management;

import entity.Recipe;
import entity.RecipeFactory;
import entity.CommonIngredientFactory;
import entity.Ingredient;
import entity.IngredientFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeManagementTest {

    @Mock
    private RecipeManagementOutputBoundary outputBoundary;

    @Mock
    private RecipeFactory recipeFactory;

    @Mock
    private RecipeManagementUserDataAccessInterface userDataAccess;

    @InjectMocks
    private RecipeManagementInteractor interactor;

    private RecipeManagementInputData inputData1;
    private RecipeManagementInputData inputData2;
    private RecipeManagementOutputData outputData1;
    private RecipeManagementOutputData outputData2;

    private IngredientFactory ingredientFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new RecipeManagementInteractor(outputBoundary);

        // Setup Ingredient Factory
        ingredientFactory = new CommonIngredientFactory();

        // Setup input and output data for full coverage
        Map<String, LocalDate> userIngredients1 = new HashMap<>();
        Ingredient flour = ingredientFactory.create("Flour", LocalDate.now().plusDays(10));
        userIngredients1.put(flour.getName(), flour.getExpiryDate());
        inputData1 = new RecipeManagementInputData("Dessert", userIngredients1);

        Map<String, LocalDate> userIngredients2 = new HashMap<>();
        Ingredient butter = ingredientFactory.create("Butter", LocalDate.now().plusDays(20));
        userIngredients2.put(butter.getName(), butter.getExpiryDate());
        inputData2 = new RecipeManagementInputData("Snack", userIngredients2);

        List<Recipe> recipes1 = new ArrayList<>();
        Recipe recipe1 = mock(Recipe.class);
        when(recipe1.getName()).thenReturn("Pancake");
        recipes1.add(recipe1);

        List<Recipe> recipes2 = new ArrayList<>();
        Recipe recipe2 = mock(Recipe.class);
        when(recipe2.getName()).thenReturn("Cookie");
        recipes2.add(recipe2);

        String message1 = "Success";
        String message2 = "Failure";

        Map<String, List<LocalDate>> recommendations1 = new HashMap<>();
        recommendations1.put("Breakfast", Arrays.asList(LocalDate.now(), LocalDate.now().plusDays(1)));
        outputData1 = new RecipeManagementOutputData(recipes1, message1, recommendations1);

        Map<String, List<LocalDate>> recommendations2 = new HashMap<>();
        recommendations2.put("Dinner", Arrays.asList(LocalDate.now().plusDays(2), LocalDate.now().plusDays(3)));
        outputData2 = new RecipeManagementOutputData(recipes2, message2, recommendations2);
    }

    @Test
    public void testCreateAndSaveIngredient() {
        Ingredient ingredient = ingredientFactory.create("Tomato", LocalDate.parse("2024-12-11"));
        assertNotNull(ingredient, "Ingredient should not be null");
        assertEquals("Tomato", ingredient.getName());
        assertEquals(LocalDate.parse("2024-12-11"), ingredient.getExpiryDate());
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
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = mock(Recipe.class);
        when(recipe.getName()).thenReturn("Cake");
        recipes.add(recipe);
        inputData1.setRecipes(recipes);

        assertEquals(1, inputData1.getRecipes().size());
        assertEquals("Cake", inputData1.getRecipes().get(0).getName());
    }

    @Test
    public void testSetUserIngredients() {
        Map<String, LocalDate> ingredients = new HashMap<>();
        Ingredient milk = ingredientFactory.create("Milk", LocalDate.now().plusDays(15));
        ingredients.put(milk.getName(), milk.getExpiryDate());
        assertEquals(1, inputData1.getUserIngredients().size());
        assertFalse(inputData1.getUserIngredients().containsKey("Milk"));
    }

    @Test
    public void testRecipeManagementOutputData() {
        assertEquals("Success", outputData1.getMessage());
        assertEquals(1, outputData1.getRecipes().size());
        assertEquals("Pancake", outputData1.getRecipes().get(0).getName());
        assertEquals(1, outputData1.getRecommendations().size());
        assertTrue(outputData1.getRecommendations().containsKey("Breakfast"));
    }

    @Test
    public void testOutputDataToString() {
        String output = outputData1.toString();
        assertTrue(output.contains("Success"));
        assertTrue(output.contains("recipes"));
        assertTrue(output.contains("recommendations"));
    }

    @Test
    public void testInputDataToString() {
        String input = inputData1.toString();
        assertFalse(input.contains("Dessert"));
        assertFalse(input.contains("Flour"));
    }
}
