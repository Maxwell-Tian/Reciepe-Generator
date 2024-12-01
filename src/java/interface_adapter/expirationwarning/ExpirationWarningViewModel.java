package interface_adapter.expirationwarning;

import interface_adapter.ViewModel;

/**
 * ViewModel for add or cancel expiration warning View.
 */
public class ExpirationWarningViewModel extends ViewModel<ExpirationWarningState> {
    public ExpirationWarningViewModel() {
        super("expiration warning");
        setState(new ExpirationWarningState());
    }
}
