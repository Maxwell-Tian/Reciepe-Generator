package interface_adapter.recipemanagement;

import interface_adapter.ViewModel;

public class RecipeManagementViewModel extends ViewModel<RecipeManagementState> {
    public RecipeManagementViewModel() {
        super("generate recipe");
        setState(new RecipeManagementState());
    }
}
