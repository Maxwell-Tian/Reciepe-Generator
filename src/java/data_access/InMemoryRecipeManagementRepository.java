package data_access;

import entity.CommonRecipe;
import entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRecipeManagementRepository {
    private static List<Recipe> initializeRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(new CommonRecipe("Chocolate Cake",
                List.of("100 Flour", "50 Sugar", "10 Cocoa")));
        recipeList.add(new CommonRecipe("Caesar Salad",
                List.of("100 Lettuce", "50 Croutons", "30 Parmesan")));
        recipeList.add(new CommonRecipe("Pancakes",
                List.of("150 Flour", "200 Milk", "2 Eggs")));
        return recipeList;
    }

    public List<Recipe> getCurrentRecipes() {
        return initializeRecipes();
    }
}
