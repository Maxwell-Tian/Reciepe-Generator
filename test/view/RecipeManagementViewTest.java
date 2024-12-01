package view;

import entity.Recipe;
import interface_adapter.recipemanagement.RecipeInfoViewModel;
import interface_adapter.recipemanagement.RecipeManagementController;
import interface_adapter.recipemanagement.RecipeManagementViewModel;
import interface_adapter.recipemanagement.RecipeManagementState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeManagementViewTest {

    @Mock
    private RecipeManagementController controller;

    @Mock
    private RecipeInfoViewModel recipeInfoViewModel;

    @Mock
    private RecipeManagementViewModel recipeManagementViewModel;

    @Mock
    private RecipeManagementState recipeManagementState;

    private RecipeInfoView recipeInfoView;
    private RecipeListView recipeListView;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        MockitoAnnotations.openMocks(this);
        when(recipeManagementViewModel.getState()).thenReturn(recipeManagementState);
        when(recipeManagementState.getCurrentlyGeneratedList()).thenReturn(new ArrayList<>());
        recipeInfoView = new RecipeInfoView(recipeInfoViewModel);
        recipeInfoView.setRecipeManagementController(controller);
        recipeListView = new RecipeListView(recipeInfoView, recipeManagementViewModel);
        recipeListView.setRecipeManagementController(controller);
    }

    @Test
    public void testShowRecipeDetails() {
        Recipe recipe = mock(Recipe.class);
        when(recipe.getName()).thenReturn("Test Recipe");
        when(recipe.getRecipeList()).thenReturn(List.of("Ingredient 1", "Ingredient 2"));

        recipeInfoView.showRecipeDetails(recipe);

        JLabel titleLabel = (JLabel) recipeInfoView.getComponent(0);
        JScrollPane scrollPane = (JScrollPane) recipeInfoView.getComponent(2);
        JTextArea detailsArea = (JTextArea) scrollPane.getViewport().getView();

        assertEquals("Recipe: Test Recipe", titleLabel.getText());
        assertEquals("Ingredients:\n- Ingredient 1\n- Ingredient 2\n", detailsArea.getText());
    }

    @Test
    public void testBackButtonActionPerformed() {
        JButton backButton = (JButton) recipeInfoView.getComponent(4);
        assertNotNull(backButton, "Back button should exist");
        backButton.doClick();
        verify(controller, times(1)).switchToRecipeListView();
    }

    @Test
    public void testRecipeListViewInit() throws FileNotFoundException {
        Recipe recipe = mock(Recipe.class);
        when(recipe.getName()).thenReturn("Sample Recipe");

        when(recipeManagementState.getCurrentlyGeneratedList()).thenReturn(List.of(recipe));

        RecipeListView listView = new RecipeListView(recipeInfoView, recipeManagementViewModel);
        listView.setRecipeManagementController(controller);

        JButton recipeButton = findButtonByLabel(listView, "Sample Recipe");
        assertNotNull(recipeButton, "Button for 'Sample Recipe' should exist");
        recipeButton.doClick();
        verify(controller, times(1)).switchToRecipeInfoView();
    }

    @Test
    public void testRecipeListViewBackButton() {
        JButton backButton = findButtonByLabel(recipeListView, "Back");
        assertNotNull(backButton, "Back button should exist");
        backButton.doClick();
        verify(controller, times(1)).switchToInitialView();
    }

    @Test
    public void testPropertyChangeInRecipeInfoView() {
        PropertyChangeEvent event = new PropertyChangeEvent(recipeInfoViewModel, "property", null, null);
        recipeInfoView.propertyChange(event);
        assertTrue(true, "Property change event handled without exceptions");
    }

    @Test
    public void testPropertyChangeInRecipeListView() throws FileNotFoundException {
        when(recipeManagementState.getCurrentlyGeneratedList()).thenReturn(new ArrayList<>());
        PropertyChangeEvent event = new PropertyChangeEvent(recipeManagementViewModel, "property", null, null);
        recipeListView.propertyChange(event);
        verify(recipeManagementViewModel, atLeastOnce()).getState();
    }

    private JButton findButtonByLabel(Container container, String label) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton button && button.getText().equals(label)) {
                return button;
            } else if (component instanceof Container subContainer) {
                JButton result = findButtonByLabel(subContainer, label);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}
