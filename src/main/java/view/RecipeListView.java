package view;

import javax.swing.*;
import java.awt.*;


public class RecipeListView extends JFrame {
    private final String viewName = "RecipeListView";
    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    public RecipeListView() {
        setTitle("Recipe List View");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel recipeListPanel = createRecipeListPanel();
        JPanel recipe1InfoPanel = createRecipeInfoPanel("Recipe 1 Information");
        JPanel recipe2InfoPanel = createRecipeInfoPanel("Recipe 2 Information");
        JPanel recipe3InfoPanel = createRecipeInfoPanel("Recipe 3 Information");

        mainPanel.add(recipeListPanel, "RecipeListPanel");
        mainPanel.add(recipe1InfoPanel, "Recipe1InfoPanel");
        mainPanel.add(recipe2InfoPanel, "Recipe2InfoPanel");
        mainPanel.add(recipe3InfoPanel, "Recipe3InfoPanel");

        add(mainPanel);

        cardLayout.show(mainPanel, "RecipeListPanel");
    }

    private JPanel createRecipeListPanel() {
        JPanel recipeListPanel = new JPanel();
        recipeListPanel.setLayout(new BoxLayout(recipeListPanel, BoxLayout.Y_AXIS));

        JButton recipeButton1 = new JButton("Recipe 1");
        JButton recipeButton2 = new JButton("Recipe 2");
        JButton recipeButton3 = new JButton("Recipe 3");
        JButton returnButton = new JButton("Return");

        recipeButton1.addActionListener(e -> cardLayout.show(mainPanel, "Recipe1InfoPanel"));
        recipeButton2.addActionListener(e -> cardLayout.show(mainPanel, "Recipe2InfoPanel"));
        recipeButton3.addActionListener(e -> cardLayout.show(mainPanel, "Recipe3InfoPanel"));

        recipeListPanel.add(recipeButton1);
        recipeListPanel.add(recipeButton2);
        recipeListPanel.add(recipeButton3);
        recipeListPanel.add(returnButton);

        return recipeListPanel;
    }

    private JPanel createRecipeInfoPanel(String infoText) {
        JPanel infoPanel = new JPanel(new BorderLayout());
        JLabel infoLabel = new JLabel(infoText, SwingConstants.CENTER);
        JButton returnButton = new JButton("Return");

        returnButton.addActionListener(e -> cardLayout.show(mainPanel, "RecipeListPanel"));

        infoPanel.add(infoLabel, BorderLayout.CENTER);
        infoPanel.add(returnButton, BorderLayout.SOUTH);

        return infoPanel;
    }

    public String getViewName() {
        return viewName;
    }
}
