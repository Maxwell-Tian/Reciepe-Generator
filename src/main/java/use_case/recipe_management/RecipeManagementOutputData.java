package use_case.recipe_management;

import entity.Recipe;
import java.util.List;

/**
 * The output data for the Recipe Management Use Case.
 */
public class RecipeManagementOutputData {

    private final List<Recipe> recipes;
    private final String message;

    public RecipeManagementOutputData(List<Recipe> recipes, String message) {
        this.recipes = recipes;
        this.message = message;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "RecipeManagementOutputData{" +
                "recipes=" + recipes +
                ", message='" + message + '\'' +
                '}';
    }
}