package interface_adapter.ExpirationWarning;

import interface_adapter.ViewModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientState;

/**
 * ViewModel for add or cancel expiration warning View.
 */
public class ExpirationWarningViewModel extends ViewModel<ExpirationWarningState> {
    /**
     * The ExpirationWarningViewModel class serves as the ViewModel for the expiration warning view,
     * managing state and interacting with the presenter.
     */
    public ExpirationWarningViewModel() {
        super("expiry warning");
        setState(new ExpirationWarningState());
    }
}
