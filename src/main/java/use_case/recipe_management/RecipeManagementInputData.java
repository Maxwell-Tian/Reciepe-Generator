package use_case.recipe_management;

import entity.Recipe;
import java.util.List;
import java.util.Map;

/**
 * The input data for the Recipe Management Use Case.
 */
public class RecipeManagementInputData {

    private List<Recipe> recipes;
    private String filterCategory;
    private Map<String, Integer> userIngredients; // 用户输入的原料及数量

    public RecipeManagementInputData(List<Recipe> recipes, String filterCategory, Map<String, Integer> userIngredients) {
        this.recipes = recipes;
        this.filterCategory = filterCategory;
        this.userIngredients = userIngredients;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getFilterCategory() {
        return filterCategory;
    }

    public void setFilterCategory(String filterCategory) {
        this.filterCategory = filterCategory;
    }

    public Map<String, Integer> getUserIngredients() {
        return userIngredients;
    }

    public void setUserIngredients(Map<String, Integer> userIngredients) {
        this.userIngredients = userIngredients;
    }
}
