import javax.swing.*;
import java.awt.*;

public class InsurancePredictorGUI extends JFrame 
{
    // dropdowns
    private JComboBox<String> ageDropdown;
    private JComboBox<String> jobDropdown, healthDropdown, maritalStatusDropdown;
    private JTextArea resultsBox;
    
    // load window and data
    public InsurancePredictorGUI() 
    {
        // Set up window
        setTitle("Insurance Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        
        // age dropdown
        panel.add(new JLabel("Age:"));
        ageDropdown = new JComboBox<>(new String[]{"young", "middle", "elderly"});
        panel.add(ageDropdown);
        
        // job dropdown
        panel.add(new JLabel("Job:"));
        jobDropdown = new JComboBox<>(new String[]{"employed", "unemployed"});
        panel.add(jobDropdown);
        
        // health dropdown
        panel.add(new JLabel("Health:"));
        healthDropdown = new JComboBox<>(new String[]{"healthy", "chronic"});
        panel.add(healthDropdown);
        
        // marital status dropdown
        panel.add(new JLabel("Marital Status:"));
        maritalStatusDropdown = new JComboBox<>(new String[]{"single", "married"});
        panel.add(maritalStatusDropdown);
        
        // predict button
        JButton predictButton = new JButton("Predict");
        predictButton.addActionListener(e -> makeAPrediction());
        panel.add(predictButton);
        
        // results area
        resultsBox = new JTextArea(3, 20);
        resultsBox.setEditable(false);
        panel.add(resultsBox);
        
        // ddd panel 
        add(panel);
        
        // load data
        loadTheData();
    }
    
    private void loadTheData() 
    {
        // Load data from file
        InsurancePredictor.loadDataFromFile("insurance_dataset.csv");
        InsurancePredictor.teachTheProgram();
    }
    
    private void makeAPrediction() 
    {
        try 
        {
            // Get user selections
            String age = (String) ageDropdown.getSelectedItem();
            String job = (String) jobDropdown.getSelectedItem();
            String health = (String) healthDropdown.getSelectedItem();
            String maritalStatus = (String) maritalStatusDropdown.getSelectedItem();
            
            // Make prediction
            PredictionResult result = InsurancePredictor.makePrediction(age, job, health, maritalStatus);
            
            // Display results
            resultsBox.setText("Prediction: " + result.getPrediction() + 
                             "\nProbability: " + (result.getProbability() * 100) + "%");
        } 
        catch (Exception error) 
        {
            resultsBox.setText("Error: " + error.getMessage());
        }
    }
    
    // Start program
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> {
            new InsurancePredictorGUI().setVisible(true);
        });
    }
} 