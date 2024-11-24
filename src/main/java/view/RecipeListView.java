package view;

import entity.CommonRecipe;
import entity.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeListView extends JFrame implements ActionListener, PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final RecipeInfoView recipeInfoView;

    private final List<Recipe> recipes;

    public RecipeListView() {
        setTitle("Recipe List View");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize recipes
        recipes = initializeRecipes();

        // Set up card layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create the RecipeInfoView panel
        recipeInfoView = new RecipeInfoView(cardLayout, mainPanel);

        // Add RecipeListPanel and RecipeInfoView to the main panel
        JPanel recipeListPanel = createRecipeListPanel();
        mainPanel.add(recipeListPanel, "RecipeListPanel");
        mainPanel.add(recipeInfoView, "RecipeInfoView");

        // Show the recipe list panel by default
        add(mainPanel);
        cardLayout.show(mainPanel, "RecipeListPanel");
    }

    /**
     * Creates the panel showing the list of all recipes.
     */
    private JPanel createRecipeListPanel() {
        JPanel recipeListPanel = new JPanel();
        recipeListPanel.setLayout(new BoxLayout(recipeListPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Recipe List");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        recipeListPanel.add(titleLabel);

        // Create a button for each recipe
        for (Recipe recipe : recipes) {
            JButton recipeButton = new JButton(recipe.getName());
            recipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            recipeButton.addActionListener(e -> {
                recipeInfoView.showRecipeDetails(recipe); // Pass recipe to RecipeInfoView
                cardLayout.show(mainPanel, "RecipeInfoView"); // Switch to RecipeInfoView
            });
            recipeListPanel.add(recipeButton);
        }

        // Add a back button (if needed for returning elsewhere)
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> System.out.println("Returning to main menu..."));
        recipeListPanel.add(backButton);

        return recipeListPanel;
    }

    /**
     * Initializes sample recipes for testing.
     */
    private List<Recipe> initializeRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(new CommonRecipe("Chocolate Cake", List.of("Delicious chocolate dessert", "Dessert"),
                Map.of("Flour", 200, "Sugar", 100, "Cocoa", 50)));
        recipeList.add(new CommonRecipe("Caesar Salad", List.of("Classic Caesar salad", "Appetizer"),
                Map.of("Lettuce", 100, "Croutons", 50, "Parmesan", 30)));
        recipeList.add(new CommonRecipe("Pancakes", List.of("Fluffy breakfast pancakes", "Breakfast"),
                Map.of("Flour", 150, "Milk", 200, "Eggs", 2)));
        return recipeList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecipeListView recipeListView = new RecipeListView();
            recipeListView.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
