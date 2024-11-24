package view;

import entity.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class RecipeInfoView extends JPanel {
    private final CardLayout cardLayout;
    private final JPanel parentPanel;

    public RecipeInfoView(CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        setLayout(new BorderLayout());
    }

    /**
     * Displays the details of the selected recipe.
     * @param recipe the recipe to display
     */
    public void showRecipeDetails(Recipe recipe) {
        removeAll(); // Clear previous content

        // Title
        JLabel titleLabel = new JLabel("Recipe: " + recipe.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Recipe details
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setText(
                "Description: " + recipe.getDescription() + "\n" +
                        "Category: " + recipe.getCategory() + "\n" +
                        "Ingredients:\n" + formatIngredients(recipe.getRecipeMap())
        );

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "RecipeListPanel"));

        // Add components to the layout
        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    /**
     * Formats the ingredients map into a readable string.
     */
    private String formatIngredients(Map<String, Integer> ingredients) {
        StringBuilder sb = new StringBuilder();
        for (var entry : ingredients.entrySet()) {
            sb.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
