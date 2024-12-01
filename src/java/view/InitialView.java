package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import entity.Ingredient;
import interface_adapter.deleteingredient.DeleteIngredientController;
import interface_adapter.initial.InitialState;
import interface_adapter.initial.InitialViewModel;


public class InitialView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "initial";

    private final InitialViewModel initialViewModel;
    private DeleteIngredientController deleteIngredientController;
    private String deletedIngredient;

    private final JButton addIngredient;
    private final JButton generateRecipe;

    public InitialView(InitialViewModel viewModel) {

        this.initialViewModel = viewModel;
        this.initialViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(initialViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel topPanel = new JPanel();
        addIngredient = new JButton(initialViewModel.ADD_INGREDIENT_BUTTON_LABEL);
        topPanel.add(addIngredient);
        generateRecipe = new JButton(initialViewModel.GENERATE_RECIPE_BUTTON_LABEL);
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

        JPanel ingredientsPanel = new JPanel();
        try {
            initialViewModel.getState().populateIngredients();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (initialViewModel.getState().getIngredients() != null) {
            for (Ingredient ingredient : initialViewModel.getState().getIngredients()) {
                System.out.println(ingredient.getName());
                JPanel ingredientPanel = new JPanel();
                JLabel ingredientName = new JLabel(ingredient.getName());
                JButton deleteButton = new JButton("delete");
                deleteButton.addActionListener(
                        evt -> {
                            InitialState currentState = initialViewModel.getState();
                            try {
                                this.deleteIngredientController.execute(currentState.getIngredients(), ingredient);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
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
        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));

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
        }

        JPanel ingredientsPanel = new JPanel();

        if (initialViewModel.getState().getIngredients() != null) {
            for (Ingredient ingredient : initialViewModel.getState().getIngredients()) {
                JPanel ingredientPanel = new JPanel();
                JLabel ingredientName = new JLabel(ingredient.getName());
                JButton deleteButton = new JButton("delete");
                deleteButton.addActionListener(
                        evt1 -> {
                            InitialState currentState = initialViewModel.getState();
                            try {
                                this.deleteIngredientController.execute(currentState.getIngredients(), ingredient);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            deletedIngredient = ingredient.getName();
                            }
                    );
                ingredientPanel.add(ingredientName);
                ingredientPanel.add(deleteButton);
                ingredientsPanel.add(ingredientPanel);
            }
        } else {ingredientsPanel.add(new JLabel("No ingredients"));}

        JScrollPane ingredientsScroll = new JScrollPane(ingredientsPanel);
        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));

        this.remove(2);
        this.add(ingredientsScroll);

        this.revalidate();
        this.repaint();
    }

    public String getViewName(){ return viewName;}

    public void setDeleteIngredientController(DeleteIngredientController controller) {
        this.deleteIngredientController = controller;
    }
}
