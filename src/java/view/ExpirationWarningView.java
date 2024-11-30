package view;

import entity.CommonIngredient;
import entity.Ingredient;
import use_case.expired_food.CheckExpiredIngredientInteractor;
import use_case.expired_food.CheckExpiredIngredientUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ExpirationWarningView extends JFrame {
    private final CheckExpiredIngredientInteractor interactor;

    public ExpirationWarningView(CheckExpiredIngredientInteractor interactor) {
        this.interactor = interactor;
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

        // Create a button to delete expired ingredients
        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Ingredient> ingredientsToDelete = new ArrayList<>();
                for (int i = 0; i < checkBoxes.size(); i++) {
                    if (checkBoxes.get(i).isSelected()) {
                        ingredientsToDelete.add(expiredList.get(i));
                    }
                }
                interactor.deleteIngredients(ingredientsToDelete);
                dispose();
            }
        });
        add(deleteButton, BorderLayout.SOUTH);

        // Create a button to go back to the initial view
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(backButton, BorderLayout.NORTH);

        setVisible(true);
    }
    public static void main(String[] args) {
        // Example data access implementation
        CheckExpiredIngredientUserDataAccessInterface dataAccess = new CheckExpiredIngredientUserDataAccessInterface() {
            private List<Ingredient> ingredients = new ArrayList<>();

            {
                ingredients.add(new CommonIngredient("Tomato", LocalDate.of(2024, 11, 20)));
                ingredients.add(new CommonIngredient("Onion", LocalDate.of(2024, 11, 25)));
            }

            @Override
            public List<Ingredient> getAllIngredients() {
                return new ArrayList<>(ingredients);
            }

            @Override
            public void setIngredients(List<Ingredient> ingredients) {
                this.ingredients = new ArrayList<>(ingredients);
            }
        };

        CheckExpiredIngredientInteractor interactor = new CheckExpiredIngredientInteractor(dataAccess);
        SwingUtilities.invokeLater(() -> new ExpirationWarningView(interactor));
    }
}