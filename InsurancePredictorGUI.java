import javax.swing.*;
import java.awt.*;

public class InsurancePredictorGUI extends JFrame 
{
    // dropdowns
    private JComboBox<String> ageDropdown;
    private JComboBox<String> jobDropdown, healthDropdown, maritalStatusDropdown;
    private JTextArea resultsBox;
    
    // loads window and data
    public InsurancePredictorGUI() 
    {
        makeTheWindow();
        loadTheData();
    }
    
    private void makeTheWindow() 
    {
        setTitle("Insurance Predictor"); //title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close
        setLayout(new BorderLayout());
        
        // choice panel
        JPanel choicesPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        choicesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // age
        choicesPanel.add(new JLabel("Age:"));
        ageDropdown = new JComboBox<>(new String[]{"young", "middle", "elderly"});
        choicesPanel.add(ageDropdown);
        
        // job
        choicesPanel.add(new JLabel("Job:"));
        jobDropdown = new JComboBox<>(new String[]{"employed", "unemployed"});
        choicesPanel.add(jobDropdown);
        
        // health
        choicesPanel.add(new JLabel("Health:"));
        healthDropdown = new JComboBox<>(new String[]{"healthy", "chronic"});
        choicesPanel.add(healthDropdown);
        
        // marital status
        choicesPanel.add(new JLabel("Marital Status:"));
        maritalStatusDropdown = new JComboBox<>(new String[]{"single", "married"});
        choicesPanel.add(maritalStatusDropdown);
        
        // choices 
        add(choicesPanel, BorderLayout.NORTH);
        
        // results box
        resultsBox = new JTextArea();
        resultsBox.setEditable(false);
        resultsBox.setRows(5);
        JScrollPane scrollBox = new JScrollPane(resultsBox);
        add(scrollBox, BorderLayout.CENTER);
        
        // predictbutton 
        JPanel buttonPanel = new JPanel();
        JButton predictButton = new JButton("Predict");
        
        predictButton.addActionListener(e -> makeAPrediction());
        
        buttonPanel.add(predictButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack(); //makes window the right size
    }
    
    private void loadTheData() 
    {
        // loads data 
        InsurancePredictor.loadDataFromFile("insurance_dataset.csv");
        InsurancePredictor.teachTheProgram();
    }
    
    private void makeAPrediction() 
    {
        try 
        {
            // get user selections
            String age = (String) ageDropdown.getSelectedItem();
            String job = (String) jobDropdown.getSelectedItem();
            String health = (String) healthDropdown.getSelectedItem();
            String maritalStatus = (String) maritalStatusDropdown.getSelectedItem();
            
            // calls program
            PredictionResult result = InsurancePredictor.makePrediction(age, job, health, maritalStatus);
            
            // displays results
            resultsBox.setText(String.format("Prediction: %s\nprobability: %.2f%%", 
                result.getPrediction(), result.getProbability() * 100));
        } 
        catch (Exception error) 
        {
            resultsBox.setText("There was a problem making the prediction: " + error.getMessage());
        }
    }
    
    // starts program
    public static void main(String[] args) 
    {
        new InsurancePredictorGUI().setVisible(true);
    }
    
} 