import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InsurancePredictor 
{
    private static List<String[]> allData = new ArrayList<>();
    
    // hashmap counts each combination and how many yes or no's
    private static Map<String, Map<String, Integer>> countingTable = new HashMap<>();
    
    // reads data and inserts to list
    public static void loadDataFromFile(String fileName) 
    {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) 
        {
            // skips first line
            fileReader.readLine();
            
            // Read each line 
            String line;
            while ((line = fileReader.readLine()) != null) 
            {
                // splits the line by its commas
                String[] onePerson = line.split(",");
                allData.add(onePerson);
            }
        } 
        catch (IOException error) 
        {
            System.out.println("There was a problem reading the file: " + error.getMessage());
        }
    }
    
    // teaches program by counting how many times each combination appears
    public static void teachTheProgram() 
    {
        // clears old data
        countingTable.clear();
        
        // looks at each persons data
        for (String[] onePerson : allData) 
        {
            // gets each piece of information
            String personAge = onePerson[0];
            String personJob = onePerson[1];
            String personHealth = onePerson[2];
            String personMaritalStatus = onePerson[3];
            String didTheyBuyInsurance = onePerson[4];
            
            // makes a key string for each person
            String personKey = personAge + "," + personJob + "," + personHealth + "," + personMaritalStatus;
            
            // creates a new map if new combo
            if (!countingTable.containsKey(personKey)) 
            {
                countingTable.put(personKey, new HashMap<>());
            }
            
            // counts how many of this combo were insured
            Map<String, Integer> yesNoCounts = countingTable.get(personKey);
            yesNoCounts.put(didTheyBuyInsurance, yesNoCounts.getOrDefault(didTheyBuyInsurance, 0) + 1);
        }
    }
    
    //makes the prediction
    public static PredictionResult makePrediction(String age, String job, String health, String maritalStatus) 
    {
        // key string combines all info
        String personKey = age + "," + job + "," + health + "," + maritalStatus;
        
        // counts for that combo
        Map<String, Integer> yesNoCounts = countingTable.getOrDefault(personKey, new HashMap<>());
        
        // how many yes/nos for that combo
        int yesCount = yesNoCounts.getOrDefault("yes", 0);
        int noCount = yesNoCounts.getOrDefault("no", 0);
        
        // total times weve seen that combo
        int totalCount = yesCount + noCount;
        
        // probability of yes
        double probabilityOfYes = totalCount > 0 ? (double) yesCount / totalCount : 0.5;
        
        // decides insurance coverage based on which is higher
        String prediction = (yesCount >= noCount) ? "yes" : "no";
        
        // returns prediction and probability
        return new PredictionResult(prediction, probabilityOfYes);
    }
    
    // allows other parts to see the hashmap
    public static Map<String, Map<String, Integer>> getCountingTable() 
    {
        return countingTable;
    }
} 