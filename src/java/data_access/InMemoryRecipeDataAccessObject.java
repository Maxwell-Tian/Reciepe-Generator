package data_access;

import entity.CommonRecipe;
import entity.Recipe;
import use_case.recipe_management.RecipeManagementUserDataAccessInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing ingredient data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryRecipeDataAccessObject implements RecipeManagementUserDataAccessInterface {
    private static final List<Recipe> allRecipes = new ArrayList<>();

    @Override
    public List<Recipe> getCurrentRecipes(){
        return allRecipes;
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        allRecipes.add(recipe);
    }

    @Override
    public void saveUserIngredients(Map<String, LocalDate> userIngredients) {

    }

    @Override
    public Map<String, LocalDate> getUserIngredients() {
        return Map.of();
    }

    public void populateAllRecipes() {
        allRecipes.add(new CommonRecipe("KungPowChicken", List.of("2 chicken", "3 tomato")));
        allRecipes.add(new CommonRecipe("Chocolate Cake",
                List.of("100 Flour", "50 Sugar", "10 Cocoa")));
        allRecipes.add(new CommonRecipe("Caesar Salad",
                List.of("100 Lettuce", "50 Croutons", "30 Parmesan")));
        allRecipes.add(new CommonRecipe("Pancakes",
                List.of("150 Flour", "200 Milk", "2 Eggs")));
    }
}