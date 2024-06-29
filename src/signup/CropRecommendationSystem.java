package signup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CropRecommendationSystem {
    private List<CropModel> crops;
    private Map<String, Double> cropPrices; // Map to store crop prices
    private static final String JDBC_URL = "jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    public CropRecommendationSystem(List<CropModel> crops) {
        this.crops = crops;
        this.cropPrices = new HashMap<>();
        initializeCropPricesFromDatabase(); // Initialize crop prices from the database
    }

    private void initializeCropPricesFromDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL))
        		{
            String query = "SELECT cropName, pricePerUnit FROM crops";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String cropName = resultSet.getString("cropName");
                        double price = resultSet.getDouble("pricePerUnit");
                        cropPrices.put(cropName, price);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, Double> entry : cropPrices.entrySet()) {
            System.out.println("Crop: " + entry.getKey() + ", Price: " + entry.getValue());
        }
}
    public List<String> recommendCrops(double desiredProfit) {
        List<String> recommendedCrops = new ArrayList<>();

        for (CropModel crop : crops) {
            double cropPrice = getCropPrice(crop.getCropName());
            double profit = calculateProfit(crop, cropPrice);

            if (profit >= desiredProfit) {
                recommendedCrops.add(crop.getCropName());
            }
        }

        return recommendedCrops;
    }


    private double calculateProfit(CropModel crop, double cropPrice) {
        // Replace this with your actual profit calculation logic
        double moisture = crop.getIdealMoisture();
        
        // Example: Calculate profit based on crop price and moisture level
        // You should replace this with your specific formula
        double profit = (cropPrice - (moisture * 100)) / 10;

        return profit;
    }

    private double getCropPrice(String cropName) {
        // Replace this with your logic to fetch crop price from the cropPrices map
        return cropPrices.getOrDefault(cropName, 0.0); // Default to 0.0 if price is not found
    }
}
