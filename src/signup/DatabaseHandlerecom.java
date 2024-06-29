package signup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandlerecom {
	private static final String JDBC_URL = "jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    public static List<CropModel> getCropsFromDatabase() {
        List<CropModel> crops = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            String query = "SELECT cropName, ideal_moisture FROM crops";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String cropName = resultSet.getString("cropName");
                        double idealMoisture = resultSet.getDouble("ideal_moisture");
                        crops.add(new CropModel(cropName, idealMoisture));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return crops;
    }
}

