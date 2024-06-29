package signup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CROPDAO {
    private static CROPDAO instance;
    private String url;

    private CROPDAO(String url) {
        this.url = url;
    }

    public static synchronized CROPDAO getInstance(String url) {
        if (instance == null) {
            instance = new CROPDAO(url);
        }
        return instance;
    }

    public String getUrl() {
        return url;
    }

    public boolean addCrop(crop crop) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection connection = DriverManager.getConnection(url)) {
                String query = "INSERT INTO crops (cropName, cropType, pricePerUnit, quantityinStock) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, crop.getCropName());
                    statement.setString(2, crop.getCropType());
                    statement.setDouble(3, crop.getPricePerUnit());
                    statement.setInt(4, crop.getQuantityInStock());

                    int rowsInserted = statement.executeUpdate();
                    return rowsInserted > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteCrop(int cropId) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection connection = DriverManager.getConnection(url)) {
                String query = "DELETE FROM crops WHERE cropId = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, cropId);

                    int rowsDeleted = statement.executeUpdate();
                    return rowsDeleted > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean modifyCrop(crop crop) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection connection = DriverManager.getConnection(url)) {
                String query = "UPDATE crops SET cropName = ?, cropType = ?, pricePerUnit = ?, quantityInStock = ? WHERE cropId = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, crop.getCropName());
                    statement.setString(2, crop.getCropType());
                    statement.setDouble(3, crop.getPricePerUnit());
                    statement.setInt(4, crop.getQuantityInStock());
                    statement.setInt(5, crop.getCropId());

                    int rowsUpdated = statement.executeUpdate();
                    return rowsUpdated > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<crop> getAllCrops() {
        List<crop> crops = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection connection = DriverManager.getConnection(url)) {
                String query = "SELECT * FROM crops";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int cropId = resultSet.getInt("cropId");
                            String cropName = resultSet.getString("cropName");
                            String cropType = resultSet.getString("cropType");
                            double pricePerUnit = resultSet.getDouble("pricePerUnit");
                            int quantityInStock = resultSet.getInt("quantityInstock");

                            crop crop = new crop(cropId, cropName, cropType, pricePerUnit, quantityInStock);
                            crops.add(crop);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return crops;
    }

    public static crop getCropByName(String selectedCropName) {
        crop selectedCrop = null;

        try (Connection connection = DriverManager.getConnection(instance.url)) {
            String query = "SELECT * FROM Crops WHERE CropName = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, selectedCropName);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        selectedCrop = new crop(
                                resultSet.getInt("CropId"),
                                resultSet.getString("CropName"),
                                resultSet.getString("CropType"),
                                resultSet.getDouble("PricePerUnit"),
                                resultSet.getInt("QuantityInStock")
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return selectedCrop;
    }
    public double getCropPriceByName(String cropName) {
        double cropPrice = 0.0;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection connection = DriverManager.getConnection(url)) {
                String query = "SELECT pricePerUnit FROM crops WHERE cropName = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, cropName);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            cropPrice = resultSet.getDouble("pricePerUnit");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return cropPrice;
    }

}
