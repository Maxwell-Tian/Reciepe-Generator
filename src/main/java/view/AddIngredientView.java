package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The view panel for adding ingredient in app. You can add ingredient on this page and 
 * choose your preference such as "Are you Halal?". After finish your choice, you can save
 * and return your choice.
 */
public class AddIngredientView extends JFrame {
    public AddIngredientView() {
        setTitle("Recipe App");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        JPanel addIngredientPanel = new JPanel();
        addIngredientPanel.setLayout(new BoxLayout(addIngredientPanel, BoxLayout.Y_AXIS));
        JTextField ingredientNameField = new JTextField("Name of ingredient");
        JCheckBox halalCheckBox = new JCheckBox("Are you Halal?");

        JButton saveButton = new JButton("save");
        JButton backButton = new JButton("return");

        addIngredientPanel.add(ingredientNameField);
        addIngredientPanel.add(halalCheckBox);
        addIngredientPanel.add(saveButton);
        addIngredientPanel.add(backButton);
    }

}
