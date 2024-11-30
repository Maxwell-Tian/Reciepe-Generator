package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorInfoView {
    public void ShowErrorView(String message) {
        JFrame frame = new JFrame("Error Detected");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JLabel label = new JLabel(message);
        JButton button = new JButton("Close window");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);
        gbc.gridy = 1;
        panel.add(button, gbc);
        frame.add(panel);
        frame.setVisible(true);
    }
}
