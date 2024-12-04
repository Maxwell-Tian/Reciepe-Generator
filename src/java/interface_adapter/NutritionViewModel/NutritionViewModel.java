package interface_adapter.NutritionViewModel;

import entity.Ingredient;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NutritionViewModel extends ViewModel<NutritionInfoState> {
    private final PropertyChangeSupport support;
    private final NutritionInfoState state;

    public NutritionViewModel() {
        super("Nutrition info");
        setState(new NutritionInfoState());
        this.support = new PropertyChangeSupport(this);
        this.state = new NutritionInfoState();
    }

    public Ingredient getIngredient() {
        return state.getIngredient();
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
