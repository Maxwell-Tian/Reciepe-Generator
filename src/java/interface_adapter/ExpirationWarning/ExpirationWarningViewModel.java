package interface_adapter.ExpirationWarning;

import interface_adapter.ViewModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientState;

/**
 * ViewModel for add or cancel expiration warning View.
 */
public class ExpirationWarningViewModel extends ViewModel<ExpirationWarningState> {
    public ExpirationWarningViewModel() {
        super("expiration warning");
        setState(new ExpirationWarningState());
    }
}
