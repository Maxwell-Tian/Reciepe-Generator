package view;

import entity.Recipe;
import interface_adapter.recipemanagement.RecipeManagementController;
import interface_adapter.recipemanagement.RecipeManagementState;
import interface_adapter.recipemanagement.RecipeManagementViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The View for displaying a list of recipes.
 */
public class RecipeListView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "recipe list";
    private final RecipeManagementViewModel recipeManagementViewModel;
    private final RecipeInfoView recipeInfoView;
    private RecipeManagementController controller;

    public RecipeListView(List<Recipe> recipes, RecipeInfoView recipeInfoView, RecipeManagementViewModel recipeManagementViewModel) {
        this.recipeInfoView = recipeInfoView;
        this.recipeManagementViewModel = recipeManagementViewModel;
        this.recipeManagementViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Recipe List");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        for (Recipe recipe : recipes) {
            JButton recipeButton = new JButton(recipe.getName());
            recipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            recipeButton.setMaximumSize(new Dimension(200, 30));
            recipeButton.addActionListener(e -> {
                recipeInfoView.showRecipeDetails(recipe);
                controller.switchToRecipeInfoView();
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(recipeButton)) {
////                            final RecipeManagementState currentState = recipeManagementViewModel.getState();
//                            recipeInfoView.showRecipeDetails(recipe);
//                            System.out.println("actionheard");
////                            controller.switchInfoView(currentState.getInfo());
//                        }
//                    }
//                };
//                recipeInfoView.showRecipeDetails(recipe);
//                cardLayout.show(parentPanel, recipeInfoView.getViewName());
            });
            this.add(recipeButton);
            this.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(this);
        backButton.setMaximumSize(new Dimension(200, 30));
        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.switchToInitialView();
    }

    public String getViewName() {
        return viewName;
    }

//    private static List<Recipe> initializeRecipes() {
//        List<Recipe> recipeList = new ArrayList<>();
//        recipeList.add(new CommonRecipe("Chocolate Cake", List.of("Delicious chocolate dessert", "Dessert"),
//                Map.of("Flour", 200, "Sugar", 100, "Cocoa", 50)));
//        recipeList.add(new CommonRecipe("Caesar Salad", List.of("Classic Caesar salad", "Appetizer"),
//                Map.of("Lettuce", 100, "Croutons", 50, "Parmesan", 30)));
//        recipeList.add(new CommonRecipe("Pancakes", List.of("Fluffy breakfast pancakes", "Breakfast"),
//                Map.of("Flour", 150, "Milk", 200, "Eggs", 2)));
//        return recipeList;
//    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Recipe Management");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(400, 300);
//
//            CardLayout cardLayout = new CardLayout();
//            JPanel parentPanel = new JPanel(cardLayout);
//
//            RecipeInfoView recipeInfoView = new RecipeInfoView(cardLayout, parentPanel);
//
//            RecipeListView recipeListView = new RecipeListView(initializeRecipes(), recipeInfoView, cardLayout, parentPanel);
//
//            parentPanel.add(recipeListView, recipeListView.getViewName());
//            parentPanel.add(recipeInfoView, recipeInfoView.getViewName());
//
//            frame.add(parentPanel);
//            cardLayout.show(parentPanel, recipeListView.getViewName());
//
//            frame.setVisible(true);
//        });
//}

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        final RecipeManagementState state = (RecipeManagementState) evt.getNewValue();
//        if (state.getErrorMessage() != null) {
//            JOptionPane.showMessageDialog(this, state.getErrorMessage());
//        }
//    }

    public void setRecipeManagementController(RecipeManagementController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final RecipeManagementState state = (RecipeManagementState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        }
    }
}
