package use_case.recipe_management;

import entity.Recipe;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * The output data for the Recipe Management Use Case.
 */
public class RecipeManagementOutputData {

    private final List<Recipe> recipes;
    private final String message;
    private final Map<String, List<LocalDate>> recommendations;

    public RecipeManagementOutputData(List<Recipe> recipes, String message, Map<String, List<LocalDate>> recommendations) {
        this.recipes = recipes;
        this.message = message;
        this.recommendations = recommendations;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, List<LocalDate>> getRecommendations() {
        return recommendations;
    }

    @Override
    public String toString() {
        return "RecipeManagementOutputData{" +
                "recipes=" + recipes +
                ", message='" + message + '\'' +
                ", recommendations=" + recommendations +
                '}';
    }
}
