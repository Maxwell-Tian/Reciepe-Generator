package view;

import entity.CommonIngredient;
import entity.Ingredient;
import interface_adapter.ExpirationWarning.ExpirationWarningController;
import use_case.expired_food.CheckExpiredIngredientInteractor;
import use_case.expired_food.CheckExpiredIngredientUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ExpirationWarningView extends JPanel {
    private final CheckExpiredIngredientInteractor interactor;
    private final CardLayout cardLayout;
    private final JPanel parentPanel;
    private final ExpirationWarningController controller;

    public ExpirationWarningView(CardLayout cardLayout, JPanel parentPanel, CheckExpiredIngredientInteractor interactor) {
        this.interactor = interactor;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.controller = new ExpirationWarningController(interactor);

        setLayout(new BorderLayout());
        setSize(400, 300);

        // Get expired ingredients
        List<Ingredient> expiredList = interactor.execute();

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
                            controller.deleteSelectedIngredients(List.of(ingredient)); // 使用控制器删除
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(parentPanel, "initial view");  // Navigate back to InitialView using CardLayout
            }
        });

        add(backButton, BorderLayout.SOUTH);
    }
}
