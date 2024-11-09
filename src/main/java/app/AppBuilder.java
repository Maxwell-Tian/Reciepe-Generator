package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppBuilder extends JFrame {
    public AppBuilder() {
        setTitle("Recipe App");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        // Panel 1: Main Panel
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        JButton viewRecipesButton = new JButton("Check recipes");
        JButton addIngredientButton = new JButton("Add ingredient");
        JButton settingsButton = new JButton("Settings");

        mainPanel.add(viewRecipesButton);
        mainPanel.add(addIngredientButton);
        mainPanel.add(settingsButton);

        add(mainPanel);

        // Panel 2: Panel of adding ingredient;
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

        // Panel 3: panel of checking recipe;
        JPanel recipePanel = new JPanel(new GridLayout(4, 1));
        recipePanel.add(new JLabel("Recipe 1"));
        recipePanel.add(new JLabel("Recipe 2"));
        recipePanel.add(new JLabel("Recipe 3"));
        JButton recipeBackButton = new JButton("return");

        recipePanel.add(recipeBackButton);

        //
        viewRecipesButton.addActionListener(e -> {
            getContentPane().removeAll();
            getContentPane().add(recipePanel);
            revalidate();
            repaint();
        });

        addIngredientButton.addActionListener(e -> {
            getContentPane().removeAll();
            getContentPane().add(addIngredientPanel);
            revalidate();
            repaint();
        });

        settingsButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "设置界面 (待实现)"));

        backButton.addActionListener(e -> {
            getContentPane().removeAll();
            getContentPane().add(mainPanel);
            revalidate();
            repaint();
        });

        recipeBackButton.addActionListener(e -> {
            getContentPane().removeAll();
            getContentPane().add(mainPanel);
            revalidate();
            repaint();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppBuilder().setVisible(true));
    }
}
