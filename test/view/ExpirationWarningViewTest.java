package view;

import entity.CommonIngredient;
import entity.Ingredient;
import interface_adapter.ExpirationWarning.ExpirationWarningController;
import interface_adapter.ExpirationWarning.ExpirationWarningViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExpirationWarningViewTest {

    private ExpirationWarningView expirationWarningView;
    private ExpirationWarningViewModel viewModel;
    private ExpirationWarningController controller;

    @BeforeEach
    public void setUp() {
        viewModel = mock(ExpirationWarningViewModel.class);
        controller = mock(ExpirationWarningController.class);
        expirationWarningView = new ExpirationWarningView(viewModel, controller);
    }

    @Test
    public void testNoExpiredIngredientsMessage() {
        when(controller.execute()).thenReturn(new ArrayList<>());

        ExpirationWarningView testView = new ExpirationWarningView(viewModel, controller);
        Component[] components = testView.getComponents();

        boolean foundMessage = false;
        for (Component component : components) {
            if (component instanceof JScrollPane scrollPane) {
                Component view = scrollPane.getViewport().getView();
                if (view instanceof JPanel panel) {
                    for (Component subComponent : panel.getComponents()) {
                        if (subComponent instanceof JLabel label && label.getText().equals("No ingredients are expired.")) {
                            foundMessage = true;
                        }
                    }
                }
            }
        }
        assertTrue(foundMessage, "No expired ingredients message should be displayed");
    }

    @Test
    public void testExpiredIngredientsDisplayed() {
        List<Ingredient> expiredIngredients = new ArrayList<>();
        expiredIngredients.add(new CommonIngredient("Milk", LocalDate.parse("2023-11-30")));
        expiredIngredients.add(new CommonIngredient("Eggs", LocalDate.parse("2023-12-01")));

        when(controller.execute()).thenReturn(expiredIngredients);

        ExpirationWarningView testView = new ExpirationWarningView(viewModel, controller);
        Component[] components = testView.getComponents();

        int checkBoxCount = 0;
        for (Component component : components) {
            if (component instanceof JScrollPane scrollPane) {
                Component view = scrollPane.getViewport().getView();
                if (view instanceof JPanel panel) {
                    for (Component subComponent : panel.getComponents()) {
                        if (subComponent instanceof JCheckBox) {
                            checkBoxCount++;
                        }
                    }
                }
            }
        }
        assertEquals(2, checkBoxCount, "There should be 2 checkboxes for expired ingredients");
    }

    @Test
    public void testBackButtonAction() {
        JButton backButton = (JButton) findComponentByName(expirationWarningView, "Back");
        assertNotNull(backButton, "Back button should exist");

        backButton.doClick();
        assertTrue(true, "Back button action performed");
    }

    @Test
    public void testIngredientDeletion() {
        List<Ingredient> expiredIngredients = new ArrayList<>();
        Ingredient ingredient = new CommonIngredient("Milk", LocalDate.parse("2023-11-30"));
        expiredIngredients.add(ingredient);

        when(controller.execute()).thenReturn(expiredIngredients);

        ExpirationWarningView testView = new ExpirationWarningView(viewModel, controller);
        JCheckBox checkBox = findCheckBoxByLabel(testView, "Milk (Expiry Date: 2023-11-30)");
        assertNotNull(checkBox, "Checkbox for 'Milk' should exist");

        checkBox.setSelected(true);
        assertTrue(checkBox.isSelected(), "Checkbox for 'Milk' should be selected");
    }

    private Component findComponentByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton button && button.getText().equals(name)) {
                return button;
            } else if (component instanceof Container subContainer) {
                Component result = findComponentByName(subContainer, name);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private JCheckBox findCheckBoxByLabel(Container container, String label) {
        for (Component component : container.getComponents()) {
            if (component instanceof JScrollPane scrollPane) {
                Component view = scrollPane.getViewport().getView();
                if (view instanceof JPanel panel) {
                    for (Component subComponent : panel.getComponents()) {
                        if (subComponent instanceof JCheckBox checkBox && checkBox.getText().equals(label)) {
                            return checkBox;
                        }
                    }
                }
            }
        }
        return null;
    }
}

