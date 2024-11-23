package view;

import entity.Ingredient;
import use_case.expired_food.CheckExpiredIngredientInteractor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class ExpirationWarningView extends JFrame {
    public ExpirationWarningView(CheckExpiredIngredientInteractor interactor) {
        setTitle("Expiration Warning");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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
            }
        }

        add(new JScrollPane(expiredPanel), BorderLayout.CENTER);

        // Create a button to go back to the initial view
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}