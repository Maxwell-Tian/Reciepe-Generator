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
    private final List<Recipe> allRecipes = new ArrayList<>();

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
        allRecipes.add(new CommonRecipe("KungPowChicken", List.of("this is a traditional Chinese dish", "Chinese"), Map.of("chicken", 2, "tomato", 3)));
        allRecipes.add(new CommonRecipe("Chocolate Cake", List.of("Delicious chocolate dessert", "Dessert"),
                Map.of("Flour", 200, "Sugar", 100, "Cocoa", 50)));
        allRecipes.add(new CommonRecipe("Caesar Salad", List.of("Classic Caesar salad", "Appetizer"),
                Map.of("Lettuce", 100, "Croutons", 50, "Parmesan", 30)));
        allRecipes.add(new CommonRecipe("Pancakes", List.of("Fluffy breakfast pancakes", "Breakfast"),
                Map.of("Flour", 150, "Milk", 200, "Eggs", 2)));
    }
}