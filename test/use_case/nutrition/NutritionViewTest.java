package use_case.nutrition;

import interface_adapter.NutritionViewModel.NutritionController;
import interface_adapter.NutritionViewModel.NutritionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.NutritionView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NutritionViewTest {
    private NutritionView view;
    private NutritionViewModel mockViewModel;
    private NutritionController mockController;

    @BeforeEach
    void setUp() {
        mockViewModel = mock(NutritionViewModel.class);
        mockController = mock(NutritionController.class);
        view = new NutritionView(mockViewModel);
        view.setController(mockController);
    }

    @Test
    void testInitialSetup() {
        // Check if title label is correctly initialized
        JLabel titleLabel = (JLabel) view.getComponent(0);
        assertEquals("Nutrition Information", titleLabel.getText());

        // Check if JTextArea is not editable
        JTextArea textArea = (JTextArea) ((JScrollPane) view.getComponent(1)).getViewport().getView();
        assertEquals(false, textArea.isEditable());
    }

    @Test
    void testShowNutritionInfoUpdatesUI() {
        String title = "Apple Nutrition";
        String nutritionInfo = "Calories: 52\nProtein: 0.3g\nFat: 0.2g";

        view.showNutritionInfo(title, nutritionInfo);

        // Check if title and text area are updated
        JLabel titleLabel = (JLabel) view.getComponent(0);
        JTextArea textArea = (JTextArea) ((JScrollPane) view.getComponent(1)).getViewport().getView();

        assertEquals(title, titleLabel.getText());
        assertEquals(nutritionInfo, textArea.getText());
    }

    @Test
    void testActionPerformedSwitchesToInitialView() {
        // Simulate clicking the "Back" button
        JButton backButton = (JButton) view.getComponent(2);
        backButton.doClick();

        // Verify the controller switches to the initial view
        verify(mockController).switchToInitialView();
    }

    @Test
    void testPropertyChangePrintsMessage() {
        PropertyChangeEvent mockEvent = new PropertyChangeEvent(this, "state", null, null);
        view.propertyChange(mockEvent);

    }
}

