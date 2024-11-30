package interface_adapter.recipemanagement;

import entity.Recipe;
import interface_adapter.ViewModel;

import java.util.List;

public class RecipeManagementViewModel extends ViewModel<RecipeManagementState> {
    private List<Recipe> recipes;

    public RecipeManagementViewModel() {
        super("recipe list");
        setState(new RecipeManagementState());
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
