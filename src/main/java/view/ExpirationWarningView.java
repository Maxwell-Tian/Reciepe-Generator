package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpirationWarningView extends JFrame {
    public ExpirationWarningView() {
        setTitle("Expiration Warning");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel warningLabel = new JLabel("Your ingredient is about to expire.");
        JButton throwAwayButton = new JButton("Throw Away");
        throwAwayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ingredient deleted.");
                dispose();
            }
        });

        JButton ignoreButton = new JButton("Ignore");
        ignoreButton.addActionListener(e -> {
            // Logic to ignore the warning and go back to InitialView without deleting
            System.out.println("Ingredient ignored.");
            dispose();
        });

        add(warningLabel);
        add(throwAwayButton);
        add(ignoreButton);

        setVisible(true);
    }
}
