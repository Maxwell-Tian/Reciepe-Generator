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
import java.io.FileNotFoundException;
import java.util.List;

/**
 * The View for displaying a list of recipes.
 */
public class RecipeListView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "recipe list";
    private final RecipeManagementViewModel recipeManagementViewModel;
    private final RecipeInfoView recipeInfoView;
    private RecipeManagementController controller;

    public RecipeListView(RecipeInfoView recipeInfoView, RecipeManagementViewModel recipeManagementViewModel) throws FileNotFoundException {
        this.recipeInfoView = recipeInfoView;
        this.recipeManagementViewModel = recipeManagementViewModel;
        this.recipeManagementViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Recipe List");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));


        RecipeManagementState state = recipeManagementViewModel.getState();
        state.regenerateList();
        List<Recipe> recipes = state.getCurrentlyGeneratedList();

        for (Recipe recipe : recipes) {
            JButton recipeButton = new JButton(recipe.getName());
            recipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            recipeButton.setMaximumSize(new Dimension(200, 30));
            recipeButton.addActionListener(e -> {
                recipeInfoView.showRecipeDetails(recipe);
                controller.switchToRecipeInfoView();
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

    public void setRecipeManagementController(RecipeManagementController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        List<Recipe> recipes = recipeManagementViewModel.getState().getCurrentlyGeneratedList();
//        for (Recipe recipe : recipes) {
//            JButton recipeButton = new JButton(recipe.getName());
//            recipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//            recipeButton.setMaximumSize(new Dimension(200, 30));
//            recipeButton.addActionListener(e -> {
//                recipeInfoView.showRecipeDetails(recipe);
//                controller.switchToRecipeInfoView();
//            });
//            this.add(recipeButton);\
//            this.add(Box.createRigidArea(new Dimension(0, 10)));
//        }
//        //TODO: property change
    }
}
