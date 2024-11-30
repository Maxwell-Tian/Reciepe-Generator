package view;


import interface_adapter.addorcancelingredient.AddorCancelIngredientController;
import interface_adapter.addorcancelingredient.AddorCancelIngredientState;
import interface_adapter.addorcancelingredient.AddorCancelIngredientViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

/**
 * The View for the add ingredient Use Case.
 */
public class AddIngredientView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "add ingredient";
    private final AddorCancelIngredientViewModel viewModel;
    private final JTextField ingredientnameInputField = new JTextField(15);
    private final JTextField expirydateInputField = new JTextField(10);
    private AddorCancelIngredientController controller;

    private final JButton add;
    private final JButton cancel;

    public AddIngredientView(AddorCancelIngredientViewModel addorCancelIngredientViewModel) {
        this.viewModel = addorCancelIngredientViewModel;
        viewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Add Ingredient");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel ingredientnameInfo = new LabelTextPanel(
                new JLabel("Enter Ingredient Name"), ingredientnameInputField);
        final LabelTextPanel expirydateInfo = new LabelTextPanel(
                new JLabel("Enter Expiry Date in YYYY-MM-DD format"), expirydateInputField);

        final JPanel buttons = new JPanel();
        add = new JButton("add");
        buttons.add(add);
        cancel = new JButton("cancel");
        buttons.add(cancel);

        add.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(add)) {
                            final AddorCancelIngredientState currentState = viewModel.getState();

                            try {
                                controller.execute(
                                        currentState.getIngredientname(),
                                        currentState.getExpirydate()
                                );
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        controller.switchToInitialView();
                    }
                }
        );

        addIngredientnameListener();
        addExpirydateListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(ingredientnameInfo);
        this.add(expirydateInfo);
        this.add(buttons);
    }

    private void addIngredientnameListener() {
        ingredientnameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final AddorCancelIngredientState currentState = viewModel.getState();
                currentState.setIngredientname(ingredientnameInputField.getText());
                viewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addExpirydateListener() {
        expirydateInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final AddorCancelIngredientState currentState = viewModel.getState();
                currentState.setExpirydate(expirydateInputField.getText());
                viewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddorCancelIngredientState state = (AddorCancelIngredientState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setAddorCancelIngredientController(AddorCancelIngredientController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "oops, im out.");
    }
}
