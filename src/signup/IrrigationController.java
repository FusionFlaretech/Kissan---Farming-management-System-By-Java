package signup;

import javax.swing.SwingUtilities;

import org.json.JSONException;
import org.json.JSONObject;

public class IrrigationController {

    private WeatherAPI weatherAPI;

    public IrrigationController(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    public void monitorIrrigationNeeds(IrrigationData irrigationData) {
        double soilMoisture = irrigationData.getSoilMoisture();
        String cropType = irrigationData.getCropType();
        double irrigationRecommendation = 0.0;  // Initialize here
        double precipitationProbability = 0.0;  // Initialize here
        double temperature = 0.0;  // Initialize here

        String city = "Sydney";  // Replace with the desired city
        String weatherInfo = WeatherAPI.getWeatherInfo(city);
        System.out.println("API Response: " + weatherInfo); // Print the entire JSON response for debugging

        if (weatherInfo != null) {
            try {
                JSONObject json = new JSONObject(weatherInfo);

                // Check if "data" object exists in the JSON response
                if (json.has("data")) {
                    JSONObject dataObject = json.getJSONObject("data");

                    // Display values
                    JSONObject valuesObject = dataObject.getJSONObject("values");

                    // Display temperature
                    if (valuesObject.has("temperature")) {
                        temperature = valuesObject.getDouble("temperature");
                        System.out.println("Temperature: " + temperature + "°C");
                        // Now you can use temperature in the analyzeIrrigationNeeds method
                        irrigationRecommendation = analyzeIrrigationNeeds(soilMoisture, temperature, precipitationProbability);
                    } else {
                        System.out.println("Temperature not found in JSON.");
                    }

                    // Display precipitation probability
                    if (valuesObject.has("precipitationProbability")) {
                        precipitationProbability = valuesObject.getDouble("precipitationProbability");
                        System.out.println("Precipitation Probability: " + (precipitationProbability * 100) + "%");
                    } else {
                        System.out.println("Precipitation Probability not found in JSON.");
                    }

                    // Other information...

                } else {
                    System.err.println("Invalid weather information format. Expected 'data' object.");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                System.err.println("Error parsing JSON: " + e.getMessage());
            }
        } else {
            System.err.println("Weather information is null.");
        }

        // Now you can use irrigationRecommendation outside the if block
        displayIrrigationInformation(irrigationData, temperature, precipitationProbability, irrigationRecommendation);
    }

    double analyzeIrrigationNeeds(double soilMoisture, double temperature, double precipitationProbability) {
        // Implement your irrigation needs analysis logic here
        // This is a placeholder, replace it with your actual algorithm.
        if (soilMoisture < 0.3) {
            // If soil moisture is very low, high irrigation needs
            return 1.0;
        } else if (soilMoisture < 0.6 && precipitationProbability < 0.3) {
            // If soil moisture is moderate and low precipitation probability, moderate irrigation needs
            return 0.7;
        } else if (temperature > 30 && precipitationProbability > 0.5) {
            // If high temperature and high precipitation probability, low irrigation needs
            return 0.3;
        } else {
            // Default case
            return 0.5;
        }
    }

    private void displayIrrigationInformation(
            IrrigationData irrigationData,
            double temperature,
            double precipitationProbability,
            double irrigationRecommendation) {
        SwingUtilities.invokeLater(() -> {
            new IrrigationForm(irrigationData, temperature, precipitationProbability, irrigationRecommendation).setVisible(true);
        });
    }
}
