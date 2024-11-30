package view;

import entity.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The View for displaying recipe details.
 */
public class RecipeInfoView extends JPanel implements ActionListener {

    private final String viewName = "recipe info";
    private final CardLayout cardLayout;
    private final JPanel parentPanel;

    private final JLabel titleLabel = new JLabel();
    private final JTextArea detailsArea = new JTextArea();
    private final JButton backButton = new JButton("Back");

    public RecipeInfoView(CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(scrollPane);
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(200, 30));
        backButton.addActionListener(this);
        this.add(backButton);
    }

    public void showRecipeDetails(Recipe recipe) {
        titleLabel.setText("Recipe: " + recipe.getName());
        detailsArea.setText(
                "Description: " + recipe.getDescription() + "\n\n" +
                        "Category: " + recipe.getCategory() + "\n\n" +
                        "Ingredients:\n" + formatIngredients(recipe.getRecipeMap())
        );
        this.revalidate();
        this.repaint();
    }

    private String formatIngredients(java.util.Map<String, Integer> ingredients) {
        StringBuilder sb = new StringBuilder();
        for (var entry : ingredients.entrySet()) {
            sb.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(parentPanel, "recipe list");
    }

    public String getViewName() {
        return viewName;
    }
}
