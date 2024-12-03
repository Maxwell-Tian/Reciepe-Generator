package view;

import entity.Ingredient;
import interface_adapter.ExpirationWarning.ExpirationWarningViewModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientController;

import interface_adapter.ExpirationWarning.ExpirationWarningController;
import interface_adapter.addorcancelingredient.AddorCancelIngredientState;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.ArrayList;

/**
 * ExpirationWarningView is a JPanel that displays a list of expired ingredients,
 * allowing users to delete ingredients or navigate back to the initial view.
 * It listens for property changes from the associated ViewModel to update the UI
 * dynamically based on changes in application state.
 */
public class ExpirationWarningView extends JPanel implements PropertyChangeListener {

    private final String viewName = "expiry warning";
    private ExpirationWarningController controller;
    private final ExpirationWarningViewModel viewModel;

    /**
     * Constructs an ExpirationWarningView with the specified ViewModel and Controller.
     * Sets up the UI components and binds event listeners for interaction.
     *
     * @param expirationWarningViewModel The ViewModel providing expired ingredient data.
     * @param expirationWarningController The Controller handling user actions.
     */
    public ExpirationWarningView(ExpirationWarningViewModel expirationWarningViewModel, ExpirationWarningController expirationWarningController) {

        this.controller = expirationWarningController;
        this.viewModel = expirationWarningViewModel;
        viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setSize(400, 300);

        // Get expired ingredients
        List<Ingredient> expiredList = controller.execute();

        // Create a panel for displaying expired ingredients with checkboxes
        JPanel expiredPanel = new JPanel();
        expiredPanel.setLayout(new BoxLayout(expiredPanel, BoxLayout.Y_AXIS));
        List<JCheckBox> checkBoxes = new ArrayList<>();

        if (expiredList.isEmpty()) {
            JLabel noExpiredLabel = new JLabel("No ingredients are expired.");
            expiredPanel.add(noExpiredLabel);
        } else {
            for (Ingredient ingredient : expiredList) {
                JCheckBox expiredCheckBox = new JCheckBox(ingredient.getName() + " (Expiry Date: " + ingredient.getExpiryDate() + ")");
                checkBoxes.add(expiredCheckBox);
                expiredPanel.add(expiredCheckBox);

                expiredCheckBox.addItemListener(e -> {
                    if (expiredCheckBox.isSelected()) {
                        int confirm = JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete " + ingredient.getName() + "?",
                                "Confirm Deletion",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            controller.deleteSelectedIngredients(ingredient); // 使用控制器删除
                            expiredPanel.remove(expiredCheckBox);
                            expiredPanel.revalidate();
                            expiredPanel.repaint();
                        } else {
                            expiredCheckBox.setSelected(false);  // Deselect if user cancels
                        }
                    }
                });
            }
        }

        add(new JScrollPane(expiredPanel), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        controller.switchToInitialView();
                    }
                }
        );

        add(backButton, BorderLayout.SOUTH);
    }

    /**
     * Sets a new controller for the ExpirationWarningView.
     *
     * @param controller The new ExpirationWarningController to set.
     */
    public void setExpirationWarningController(ExpirationWarningController controller) {
        this.controller = controller;
    }

    /**
     * Handles property changes in the associated ViewModel.
     * Displays error messages to the user if an error state is detected.
     *
     * @param evt The property change event containing the updated state.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddorCancelIngredientState state = (AddorCancelIngredientState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        }
    }

    public String getViewName() {
        return viewName;
    }
}
