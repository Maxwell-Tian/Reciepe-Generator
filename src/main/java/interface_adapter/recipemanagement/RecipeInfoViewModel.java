package interface_adapter.recipemanagement;

import entity.Recipe;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the RecipeInfoView.
 * Manages the state of the RecipeInfoView and notifies it of updates.
 */
public class RecipeInfoViewModel {
    private final PropertyChangeSupport support;
    private final RecipeInfoState state;

    public RecipeInfoViewModel() {
        this.support = new PropertyChangeSupport(this);
        this.state = new RecipeInfoState(); // 初始化状态
    }

    /**
     * Sets the current recipe in the state and notifies listeners of the change.
     * @param recipe the new recipe to display
     */
    public void setCurrentRecipe(Recipe recipe) {
        Recipe oldRecipe = state.getCurrentRecipe();
        state.setCurrentRecipe(recipe);
        support.firePropertyChange("currentRecipe", oldRecipe, recipe);
    }

    /**
     * Gets the current recipe from the state.
     * @return the current recipe
     */
    public Recipe getCurrentRecipe() {
        return state.getCurrentRecipe();
    }

    /**
     * Adds a PropertyChangeListener to listen for updates.
     * @param listener the listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener.
     * @param listener the listener to remove
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Gets the view name for navigation purposes.
     * @return the view name
     */
    public String getViewName() {
        return "RecipeInfoView";
    }

    public void showRecipeDetails(Recipe recipe) {
    }
}
