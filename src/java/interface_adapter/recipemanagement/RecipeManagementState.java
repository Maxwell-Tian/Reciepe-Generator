package interface_adapter.recipemanagement;

import api.RecipeRequest;
import data.txtConnector;
import entity.Ingredient;
import entity.Recipe;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RecipeManagementState {
    private List<Recipe> currentlyGeneratedList = new ArrayList<>();
    private final txtConnector ingredientDataAccessObject = new txtConnector();

    public void regenerateList() throws FileNotFoundException {
        List<Ingredient> currentIngredient = ingredientDataAccessObject.getCurrentIngredients();
        RecipeRequest request = new RecipeRequest(currentIngredient);
        List<List<String>> rawRecipes = request.Searching_Recipe();
        currentlyGeneratedList = request.translator(rawRecipes);
    }

    public List<Recipe> getCurrentlyGeneratedList() {
        return currentlyGeneratedList;
    }
}