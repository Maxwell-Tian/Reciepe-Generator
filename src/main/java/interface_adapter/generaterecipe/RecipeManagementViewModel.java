package interface_adapter.generaterecipe;

import interface_adapter.ViewModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientState;

public class RecipeManagementViewModel extends ViewModel<RecipeManagementState> {
    public RecipeManagementViewModel() {
        super("generate recipe");
        setState(new RecipeManagementState());
    }
}
