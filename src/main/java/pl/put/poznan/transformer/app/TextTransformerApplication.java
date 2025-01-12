package pl.put.poznan.transformer.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

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
        JTextArea textField = new JTextArea(5, 30);
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);
        textField.setMargin(new java.awt.Insets(5, 5, 5, 5));
        JScrollPane scrollPane = new JScrollPane(textField);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Dodaj przewijanie (opcjonalnie):
        // JScrollPane scrollPane = new JScrollPane(textField);
        panel.add(scrollPane, BorderLayout.CENTER);
        // panel.add(textField, BorderLayout.CENTER);
        
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
    
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Pobranie tekstu
                    String inputText = textField.getText();

                    // Zbieranie wybranych transformacji
                    List<String> transforms = new ArrayList<>();
                    if (inverseCheckbox.isSelected()) transforms.add("inverse");
                    if (upperCheckbox.isSelected()) transforms.add("upper");
                    if (lowerCheckbox.isSelected()) transforms.add("lower");
                    if (capitalizeCheckbox.isSelected()) transforms.add("capitalize");
                    if (reverseCheckbox.isSelected()) transforms.add("reverse");
                    if (removeCheckbox.isSelected()) transforms.add("remove");
                    if (toLatexCheckbox.isSelected()) transforms.add("tolatex");
                    if (shortenCheckbox.isSelected()) transforms.add("shorten");
                    if (expandCheckbox.isSelected()) transforms.add("expand");
                    if (verbaliseCheckbox.isSelected()) transforms.add("verbalise");

                    // Budowanie ciągu JSON (prosty przykład)
                    StringBuilder jsonBuilder = new StringBuilder();
                    jsonBuilder.append("{\"text\":\"").append(inputText).append("\",");
                    jsonBuilder.append("\"transforms\":[");
                    for (int i = 0; i < transforms.size(); i++) {
                        jsonBuilder.append("\"").append(transforms.get(i)).append("\"");
                        if (i < transforms.size() - 1) {
                            jsonBuilder.append(",");
                        }
                    }
                    jsonBuilder.append("]}");

                    // Wysyłanie zapytania POST (przykład z wbudowanym HttpClient w Java 11+)
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:8080/dummy")) // lub inny parametr {text}
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(jsonBuilder.toString()))
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    // Wyświetlenie odpowiedzi
                    // Parsowanie odpowiedzi JSON, aby uzyskać transformedText
                    String responseBody = response.body();
                    String transformedText = responseBody.substring(responseBody.indexOf("transformedText\":\"") + 18, responseBody.lastIndexOf("\""));
                    
                    // Wyświetlenie transformedText
                    JPanel panelWithLabel = new JPanel(new BorderLayout());
                    JLabel label = new JLabel("Odpowiedź z serwera:");
                    panelWithLabel.add(label, BorderLayout.NORTH);

                    JTextArea textArea = new JTextArea(transformedText);
                    textArea.setEditable(false);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 200));
                    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                    panelWithLabel.add(scrollPane, BorderLayout.CENTER);

                    JOptionPane.showMessageDialog(panel, panelWithLabel, "Odpowiedź", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Wystąpił błąd: " + ex.getMessage());
                }
            }
        });
    
        // Set the frame visibility
        frame.setVisible(true);
    }
    

}
