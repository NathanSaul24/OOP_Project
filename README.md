OOP Machine Learning Project

-- Insurance Predictor --

This project is a machine learning model which reads information in from a dataset.
It creates a hashmap to determine how many of each attribute combination is present, with both its yes and no count. 
It then allows the user to select one of each of the four attributes in the GUI panel, and returns both the prediction of insurance and probability in a percentage. 

The core functionality of the code consists of a few main functions:

- loadDataFromFile reads the data in from the dataset and puts it into a hashmap
- teachTheProgram counts how many yes and nos there are for each combination of characteristics
- makePrediction which counts the yes and no count for the chosen combo and calculates the probability
- addNewData which takes the currently chosen combo, and adds it into the hashmap for prediction

A few small details:
- Both the prediction and probability results are encapsulated in the PredictionResult.java file so they can be accessed from anywhere
- The frequecny table is store in a hashmap. Each attribute combo has its own key in which the yes and no count values are stored

Frequency Table:

Age,Employment,Health,Marital_status,yes_count,no_count
young,employed,healthy,single,8,2
young,employed,healthy,married,9,1
young,employed,chronic,single,4,6
young,employed,chronic,married,5,5
young,unemployed,healthy,single,6,4
young,unemployed,healthy,married,7,3
young,unemployed,chronic,single,3,7
young,unemployed,chronic,married,4,6
elderly,employed,healthy,single,7,3
elderly,employed,healthy,married,8,2
elderly,employed,chronic,single,2,8
elderly,employed,chronic,married,3,7
elderly,unemployed,healthy,single,5,5
elderly,unemployed,healthy,married,6,4
elderly,unemployed,chronic,single,1,9
elderly,unemployed,chronic,married,2,8 

