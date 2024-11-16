package interface_adapter.addorcancelingredient;

import interface_adapter.ViewModel;

/**
 * ViewModel for add or cancel ingredient View.
 */
public class AddorCancelIngredientViewModel extends ViewModel<AddorCancelIngredientState> {

    public AddorCancelIngredientViewModel() {
        super("add ingredient");
        setState(new AddorCancelIngredientState());
    }
}
