package interface_adapter.inital;

import interface_adapter.ViewModel;

/**
 * The View Model for the Initial View.
 */
public class InitialViewModel extends ViewModel<InitialState> {

    public InitialViewModel() {
        super("initial");
        setState(new InitialState());
    }

}
