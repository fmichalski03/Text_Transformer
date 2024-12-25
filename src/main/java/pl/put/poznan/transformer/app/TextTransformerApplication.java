package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("TextTransformer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        
        // Create a panel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        
        // Set the frame visibility
        frame.setVisible(true);
        SpringApplication.run(TextTransformerApplication.class, args);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Create a label
        JLabel userLabel = new JLabel("Enter Text:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // Create a text field
        JTextField textField = new JTextField(20);
        textField.setBounds(100, 20, 165, 25);
        panel.add(textField);

        // Create a button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(10, 80, 80, 25);
        panel.add(submitButton);

        // Add action listener to the button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                JOptionPane.showMessageDialog(panel, "You entered: " + inputText);
            }
        });
    }
}
