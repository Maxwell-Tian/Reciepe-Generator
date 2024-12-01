package interface_adapter.recipemanagement;

import api.API_request;
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
        API_request request = new API_request(currentIngredient, "", "Asian", "");
        List<List<String>> rawRecipes = request.Searching_Recipe();
        currentlyGeneratedList = request.translater(rawRecipes);
    }

    public List<Recipe> getCurrentlyGeneratedList() {
        return currentlyGeneratedList;
    }
}