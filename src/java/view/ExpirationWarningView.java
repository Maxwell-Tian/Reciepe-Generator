package view;

import entity.Ingredient;
import interface_adapter.expirationwarning.ExpirationWarningViewModel;

import interface_adapter.expirationwarning.ExpirationWarningController;
import interface_adapter.addorcancelingredient.AddorCancelIngredientState;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ExpirationWarningView extends JPanel implements PropertyChangeListener {

    private final String viewName = "expiry warning";
    private ExpirationWarningController controller;
    private final ExpirationWarningViewModel viewModel;

    public ExpirationWarningView(ExpirationWarningViewModel expirationWarningViewModel, ExpirationWarningController expirationWarningController) {
        this.controller = expirationWarningController;
        this.viewModel = expirationWarningViewModel;
        viewModel.addPropertyChangeListener(this);

        initializeView();
    }

    private void initializeView() {
        setLayout(new BorderLayout());
        setSize(400, 300);

        // Create panel to display expired ingredients
        JPanel expiredPanel = new JPanel();
        expiredPanel.setLayout(new BoxLayout(expiredPanel, BoxLayout.Y_AXIS));

        // Get expired ingredients from controller
        List<Ingredient> expiredList = controller.execute();
        System.out.println("Controller executed, checking expired ingredients...");

        if (expiredList.isEmpty()) {
            System.out.println("No expired ingredients found. Adding label...");

            // Highlighted Change: Wrap GUI update in SwingUtilities.invokeLater to ensure threading correctness
            SwingUtilities.invokeLater(() -> {
                JLabel noExpiredLabel = new JLabel("No ingredients are expired.");
                expiredPanel.add(noExpiredLabel);

                // Highlighted Change: Ensure the panel updates are properly rendered
                expiredPanel.revalidate();
                expiredPanel.repaint();
                System.out.println("Label added to panel. revalidate() and repaint() called.");
            });
        } else {
            System.out.println("Expired ingredients found. Adding to panel...");
            addExpiredIngredients(expiredList, expiredPanel);
        }

        add(new JScrollPane(expiredPanel), BorderLayout.CENTER);

        // Back button to return to the initial view
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            System.out.println("Back button pressed. Switching to initial view...");
            controller.switchToInitialView();
        });
        add(backButton, BorderLayout.SOUTH);
        this.setVisible(true); // For ExpirationWarningView - Ensures that the view itself is visible <-- Added
        expiredPanel.setVisible(true); // For the panel displaying expired items - Ensures the sub-panel is visible <-- Added
    }

    private void addExpiredIngredients(List<Ingredient> expiredList, JPanel expiredPanel) {
        for (Ingredient ingredient : expiredList) {
            System.out.println("Adding expired ingredient to panel: " + ingredient.getName());
            JCheckBox expiredCheckBox = new JCheckBox(ingredient.getName() + " (Expiry Date: " + ingredient.getExpiryDate() + ")");
            expiredPanel.add(expiredCheckBox);

            expiredCheckBox.addItemListener(e -> {
                if (expiredCheckBox.isSelected()) {
                    System.out.println("Checkbox selected for: " + ingredient.getName());

                    int confirm = JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete " + ingredient.getName() + "?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        System.out.println("Confirmed deletion of: " + ingredient.getName());
                        controller.deleteSelectedIngredients(List.of(ingredient));
                        expiredPanel.remove(expiredCheckBox);
                        expiredPanel.revalidate();
                        expiredPanel.repaint();
                        System.out.println("Checkbox removed for: " + ingredient.getName());
                    } else {
                        System.out.println("Deletion cancelled for: " + ingredient.getName());
                        expiredCheckBox.setSelected(false);  // Deselect if user cancels
                    }
                }
            });
        }
    }

    public void setExpirationWarningController(ExpirationWarningController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddorCancelIngredientState state = (AddorCancelIngredientState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            System.out.println("Error Message Received: " + state.getErrorMessage());
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        }
    }

    public String getViewName() {
        return viewName;
    }
}
