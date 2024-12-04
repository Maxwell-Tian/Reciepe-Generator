package view;

import interface_adapter.NutritionViewModel.NutritionController;
import interface_adapter.NutritionViewModel.NutritionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NutritionView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "NutritionView";
    private final NutritionViewModel nutritionViewModel;
    private final JLabel titleLabel =  new JLabel("Nutrition Information", SwingConstants.CENTER);
    private final JTextArea nutritionTextArea = new JTextArea();
    private final JScrollPane scrollPane = new JScrollPane(nutritionTextArea);
    private NutritionController controller;

    public NutritionView(NutritionViewModel nutritionViewModel) {
        this.nutritionViewModel = nutritionViewModel;
        this.nutritionViewModel.addPropertyChangeListener(this);
        this.setLayout(new BorderLayout());

        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel, BorderLayout.NORTH);

        nutritionTextArea.setEditable(false);
        nutritionTextArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        scrollPane.setPreferredSize(new Dimension(400, 300));
        this.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(this);
        backButton.setMaximumSize(new Dimension(200, 30));
        this.add(backButton);
    }

    public void showNutritionInfo(String title, String nutritionInfo) {
        titleLabel.setText(title);
        nutritionTextArea.setText(nutritionInfo);
    }

    public String getViewName(){
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.switchToInitialView();
    }

    public void setController(NutritionController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("there is no property change");
    }
}
