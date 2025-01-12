package pl.put.poznan.transformer.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    private JFrame frame;
    private JTextField textField;
    private JTextArea resultArea;
    private List<JCheckBox> checkboxes = new ArrayList<>();

    public static void main(String[] args) {
        // Create the frame
        TextTransformerApplication application = new TextTransformerApplication();
        application.createGUI();
        SpringApplication.run(TextTransformerApplication.class, args);
    }

    private void createGUI() {
        frame = new JFrame("TextTransformer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 320);
    
        // Create a panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);
    
        // Set the background color for the panel
        panel.setBackground(frame.getBackground());
    
        // Create a label
        JLabel userLabel = new JLabel("Enter Text:");
        panel.add(userLabel, BorderLayout.NORTH);
    
        // Create a text field and set its background and border
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(30, 30)); // Width: 150, Height: 30
        textField.setBackground(panel.getBackground());
        textField.setForeground(Color.BLUE);
        textField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panel.add(textField, BorderLayout.CENTER);
        
        // Create checkboxes for transformations
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        checkboxPanel.setBackground(panel.getBackground()); // Match the checkbox panel's background
        panel.add(checkboxPanel, BorderLayout.EAST);
    
        JCheckBox inverseCheckbox = new JCheckBox("Inverse");
        JCheckBox upperCheckbox = new JCheckBox("Upper");
        JCheckBox lowerCheckbox = new JCheckBox("Lower");
        JCheckBox capitalizeCheckbox = new JCheckBox("Capitalize");
        JCheckBox reverseCheckbox = new JCheckBox("Reverse");
        JCheckBox removeCheckbox = new JCheckBox("Remove");
        JCheckBox toLatexCheckbox = new JCheckBox("To LaTeX");
        JCheckBox shortenCheckbox = new JCheckBox("Shorten");
        JCheckBox expandCheckbox = new JCheckBox("Expand");
        JCheckBox verbaliseCheckbox = new JCheckBox("Verbalise");
    
        checkboxes.add(inverseCheckbox);
        checkboxes.add(upperCheckbox);
        checkboxes.add(lowerCheckbox);
        checkboxes.add(capitalizeCheckbox);
        checkboxes.add(reverseCheckbox);
        checkboxes.add(removeCheckbox);
        checkboxes.add(toLatexCheckbox);
        checkboxes.add(shortenCheckbox);
        checkboxes.add(expandCheckbox);
        checkboxes.add(verbaliseCheckbox);
    
        checkboxPanel.add(inverseCheckbox);
        checkboxPanel.add(upperCheckbox);
        checkboxPanel.add(lowerCheckbox);
        checkboxPanel.add(capitalizeCheckbox);
        checkboxPanel.add(reverseCheckbox);
        checkboxPanel.add(removeCheckbox);
        checkboxPanel.add(toLatexCheckbox);
        checkboxPanel.add(shortenCheckbox);
        checkboxPanel.add(expandCheckbox);
        checkboxPanel.add(verbaliseCheckbox);
    
        // Create a button
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton, BorderLayout.SOUTH);
    
        // Add action listener to the button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                JOptionPane.showMessageDialog(panel, "You entered: " + inputText);
            }
        });
    
        // Set the frame visibility
        frame.setVisible(true);
    }
    

}
