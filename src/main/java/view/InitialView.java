package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import entity.Ingredient;
import interface_adapter.deleteingredient.DeleteIngredientController;
import interface_adapter.initial.InitialState;
import interface_adapter.initial.InitialViewModel;


public class InitialView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Initial View";

    private final InitialViewModel viewModel;
    private DeleteIngredientController deleteIngredientController;
    private String deletedIngredient;

    private final JButton addIngredient;
    private final JButton generateRecipe;

    public InitialView(InitialViewModel viewModel) {

        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(viewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel topPanel = new JPanel();
        addIngredient = new JButton(viewModel.ADD_INGREDIENT_BUTTON_LABEL);
        topPanel.add(addIngredient);
        generateRecipe = new JButton(viewModel.GENERATE_RECIPE_BUTTON_LABEL);
        topPanel.add(generateRecipe);
        setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        addIngredient.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        deleteIngredientController.switchToAddIngredientView();}
                }
        );

        generateRecipe.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        deleteIngredientController.switchToRecipeView();}
                }
        );

        JPanel ingredientsPanel = new JPanel(new FlowLayout());
        if (viewModel.getState().getIngredients() != null) {
            for (Ingredient ingredient : viewModel.getState().getIngredients()) {
                JPanel ingredientPanel = new JPanel();
                JLabel ingredientName = new JLabel(ingredient.getName());
                JButton deleteButton = new JButton("delete");
                deleteButton.addActionListener(
                        evt -> {
                            InitialState currentState = viewModel.getState();
                            this.deleteIngredientController.execute(currentState.getIngredients(), ingredient);
                            deletedIngredient = ingredient.getName();
                        }
                );
                ingredientPanel.add(ingredientName);
                ingredientPanel.add(deleteButton);
                ingredientsPanel.add(ingredientPanel);
            }
        }
        else {
            ingredientsPanel.add(new JLabel("No ingredients"));
        }

        JScrollPane ingredientsScroll = new JScrollPane(ingredientsPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(topPanel);
        this.add(ingredientsScroll);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if ("ingredients".equals(evt.getPropertyName())) {
            System.out.println(deletedIngredient + " has been deleted!");

            JPanel ingredientsPanel = new JPanel(new FlowLayout());
            if (viewModel.getState().getIngredients() != null) {
                for (Ingredient ingredient : viewModel.getState().getIngredients()) {
                    JPanel ingredientPanel = new JPanel();
                    JLabel ingredientName = new JLabel(ingredient.getName());
                    JButton deleteButton = new JButton("delete");
                    deleteButton.addActionListener(
                            evt1 -> {
                                InitialState currentState = viewModel.getState();
                                this.deleteIngredientController.execute(currentState.getIngredients(), ingredient);
                                deletedIngredient = ingredient.getName();
                            }
                    );
                    ingredientPanel.add(ingredientName);
                    ingredientPanel.add(deleteButton);
                    ingredientsPanel.add(ingredientPanel);
                }
            }
            else {ingredientsPanel.add(new JLabel("No ingredients"));}
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setDeleteIngredientController(DeleteIngredientController controller) {
        this.deleteIngredientController = controller;
    }
}
