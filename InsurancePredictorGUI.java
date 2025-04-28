import javax.swing.*;
import java.awt.*;

public class InsurancePredictorGUI extends JFrame 
{
    // dropdowns
    private JComboBox<String> ageDropdown;
    private JComboBox<String> jobDropdown, healthDropdown, marriedDropdown;
    private JTextArea resultsBox;
    
    // load window and data
    public InsurancePredictorGUI() 
    {
        // set up window
        setTitle("Insurance Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        
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
        marriedDropdown = new JComboBox<>(new String[]{"single", "married"});
        marriedDropdown.setSelectedItem("married");
        panel.add(marriedDropdown);
        
        // predict button
        JButton predictButton = new JButton("Predict");
        predictButton.addActionListener(e -> makeAPrediction());
        panel.add(predictButton);
        
        // add data button
        JButton addDataButton = new JButton("Add Data");
        addDataButton.addActionListener(e -> addNewData());
        panel.add(addDataButton);
        
        // results area
        resultsBox = new JTextArea(3, 20);
        resultsBox.setEditable(false); //prevents user box interaction
        panel.add(resultsBox);
        
        // add panel 
        add(panel);
        
        // loads data
        loadTheData();
    }
    
    private void loadTheData() 
    {
        // gets data from file
        InsurancePredictor.loadDataFromFile("insurance_dataset.csv");
        InsurancePredictor.teachTheProgram();
    }
    
    private void makeAPrediction() 
    {
        try 
        {
            // gets user selections
            String age = (String) ageDropdown.getSelectedItem();
            String job = (String) jobDropdown.getSelectedItem();
            String health = (String) healthDropdown.getSelectedItem();
            String married = (String) marriedDropdown.getSelectedItem();
            
            // prediction
            PredictionResult result = InsurancePredictor.makePrediction(age, job, health, married);
            
            // results
            resultsBox.setText("Prediction: " + result.getPrediction() + "\nProbability: " + (result.getProbability() * 100) + "%");
        } 
        catch (Exception error) 
        {
            resultsBox.setText("Error: " + error.getMessage());
        }
    }
    
    private void addNewData() 
    {
        try 
        {
            // selections from dropdowns
            String age = (String) ageDropdown.getSelectedItem();
            String job = (String) jobDropdown.getSelectedItem();
            String health = (String) healthDropdown.getSelectedItem();
            String married = (String) marriedDropdown.getSelectedItem();
            
            // pop up for yes or no value
            int answer = JOptionPane.showConfirmDialog(this, "Did this person get insured?", "Add New Line", JOptionPane.YES_NO_OPTION);
            
            // converts answer to yes or no
            String insured = (answer == JOptionPane.YES_OPTION) ? "yes" : "no";
            
            // adds data to hashmap
            InsurancePredictor.addNewData(age, job, health, married, insured);
            resultsBox.setText("New line added");
        } 
        catch (Exception error) 
        {
            resultsBox.setText("Error: " + error.getMessage());
        }
    }
    
    public static void main(String[] args) 
    {
        new InsurancePredictorGUI().setVisible(true);
    }
    
} 