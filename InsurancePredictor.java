import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InsurancePredictor 
{
    private static List<String[]> allData = new ArrayList<>();

    private static Map<String, Map<String, Integer>> countingTable = new HashMap<>(); // some basic principles taken from https://www.geeksforgeeks.org/hashmap-values-method-in-java/
    
    // reads data and inserts to array
    public static void loadDataFromFile(String fileName) 
    {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) 
        {
            // skips first line
            fileReader.readLine();
            
            // read each line 
            String line;
            while ((line = fileReader.readLine()) != null) 
            {
                // splits line by commas
                String[] onePerson = line.split(",");
                allData.add(onePerson);
            }
        } 
        catch (IOException error) 
        {
            System.out.println("There was a problem reading the file: " + error.getMessage());
        }
    }
    
    public static void teachTheProgram() // some basic principles taken from https://www.geeksforgeeks.org/hashmap-values-method-in-java/
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
            String personMarried = onePerson[3];
            String didTheyGetInsured = onePerson[4];
            
            // makes a key string for each person
            String personKey = personAge + "," + personJob + "," + personHealth + "," + personMarried;
            
            // creates a new map if new combo
            if (!countingTable.containsKey(personKey)) 
            {
                countingTable.put(personKey, new HashMap<>());
            }
            
            // counts how many of this combo were insured
            Map<String, Integer> yesNoCounts = countingTable.get(personKey);
            yesNoCounts.put(didTheyGetInsured, yesNoCounts.getOrDefault(didTheyGetInsured, 0) + 1);
        }
    }
    
    // makes the prediction
    public static PredictionResult makePrediction(String age, String job, String health, String married) 
    {
        // make a key for this person's details
        String personKey = age + "," + job + "," + health + "," + married;
        
        // get the counts for this combination
        Map<String, Integer> counts = countingTable.get(personKey);
        
        // get yes and no count from hashmap
        int yesCount = counts.getOrDefault("yes", 0);
        int noCount = counts.getOrDefault("no", 0);
        
        // calculate total and probability
        int total = yesCount + noCount;
        double probability = (double)yesCount / total;
        
        // decide prediction
        String prediction;
        if (yesCount >= noCount) 
        {
            prediction = "yes";
        } 
        else 
        {
            prediction = "no";
        }
        
        return new PredictionResult(prediction, probability);
    }
    
    // allows other parts to see the hashmap
    public static Map<String, Map<String, Integer>> getCountingTable() 
    {
        return countingTable;
    }
    
    public static void addNewData(String age, String job, String health, String married, String insured) 
    {
        // create new data line
        String[] newData = {age, job, health, married, insured};
        allData.add(newData);
        
        // retrain with new data
        teachTheProgram();
    }
} 