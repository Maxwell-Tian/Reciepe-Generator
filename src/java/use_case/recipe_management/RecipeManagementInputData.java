package use_case.recipe_management;

import entity.Recipe;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * The input data for the Recipe Management Use Case.
 */
public class RecipeManagementInputData {

    private List<Recipe> recipes;
    private String filterCategory;
    private Map<String, LocalDate> userIngredients;

    public RecipeManagementInputData(String filterCategory, Map<String, LocalDate> userIngredients) {
        this.filterCategory = filterCategory;
        this.userIngredients = userIngredients;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }


    public Map<String, LocalDate> getUserIngredients() {
        return userIngredients;
    }

}
