public class PredictionResult 
{
    private String prediction;
    private double probability;
    public PredictionResult(String prediction, double probability) 
    {
        this.prediction = prediction;
        this.probability = probability;
    }
    //to view the prediction
    public String getPrediction() 
    {
        return prediction;
    }
    
    // TO VIEW THE PROBABILITY
    public double getProbability() 
    {
        return probability;
    }
} 