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
import data_access.FileIngredientDataAccessObject;


public class InitialView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "initial";

    private InitialViewModel initialViewModel;
    private DeleteIngredientController deleteIngredientController;
    private FileIngredientDataAccessObject ingredientsDataAccessObject;
    private String deletedIngredient;

    JPanel ingredientsPanel;
    JPanel topPanel;
    final JLabel title;
    private final JButton addIngredient;
    private final JButton generateRecipe;

    public InitialView(InitialViewModel viewModel, FileIngredientDataAccessObject ingredientDataAccessObject) {

        this.initialViewModel = viewModel;
        this.initialViewModel.addPropertyChangeListener(this);
        this.ingredientsDataAccessObject = ingredientDataAccessObject;
        initialViewModel.getState().setIngredients(ingredientsDataAccessObject.getAllIngredients());

        title = new JLabel(initialViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel = new JPanel();
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

        ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));

        if (initialViewModel.getState().getIngredients() != null &&
                !initialViewModel.getState().getIngredients().isEmpty()) {
            for (Ingredient ingredient : initialViewModel.getState().getIngredients()) {
                JPanel ingredientPanel = new JPanel();
                JLabel ingredientName = new JLabel(ingredient.getName());
                JButton deleteButton = new JButton("delete");
                deleteButton.addActionListener(
                        evt -> {
                            InitialState currentState = initialViewModel.getState();
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
            JLabel noIngredient = new JLabel("No ingredients ");
            ingredientsPanel.add(noIngredient);
            noIngredient.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        InitialState state = (InitialState) evt.getNewValue();
        if ("ingredients".equals(evt.getPropertyName())) {
            System.out.println(deletedIngredient + " has been deleted!");
        }

        ingredientsPanel.removeAll();
        if (state.getIngredients() != null &&
                !state.getIngredients().isEmpty()) {
            for (Ingredient ingredient : state.getIngredients()) {
                JPanel ingredientPanel = new JPanel();
                JLabel ingredientName = new JLabel(ingredient.getName());
                JButton deleteButton = new JButton("delete");
                deleteButton.addActionListener(
                        evt1 -> {
                            InitialState currentState = initialViewModel.getState();
                            this.deleteIngredientController.execute(currentState.getIngredients(), ingredient);
                            deletedIngredient = ingredient.getName();
                        }
                );
                ingredientPanel.add(ingredientName);
                ingredientPanel.add(deleteButton);
                ingredientsPanel.add(ingredientPanel);
            }
        } else {JLabel noIngredient = new JLabel("No ingredients ");
            ingredientsPanel.add(noIngredient);
            noIngredient.setAlignmentX(Component.CENTER_ALIGNMENT);}
        ingredientsPanel.repaint();
        ingredientsPanel.revalidate();
    }

    public String getViewName(){ return viewName;}

    public void setDeleteIngredientController(DeleteIngredientController controller) {
        this.deleteIngredientController = controller;
    }
}
