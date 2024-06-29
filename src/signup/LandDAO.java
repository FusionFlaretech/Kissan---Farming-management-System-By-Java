package signup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LandDAO {
    private String url;

    public LandDAO(String url) {
        this.url = url;
    }

    // Create a new land record in the database
    public boolean addLand(LAND land) {
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "INSERT INTO lands (landName, area, cropType) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, land.getLandName());
                statement.setDouble(2, land.getArea());
                statement.setString(3, land.getCropType());

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Retrieve all land records from the database
    public List<LAND> getAllLands() {
        List<LAND> lands = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM lands";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        int landId = resultSet.getInt("landId");
                        String landName = resultSet.getString("landName");
                        double area = resultSet.getDouble("area");
                        String cropType = resultSet.getString("cropType");

                        LAND land = new LAND(landId, landName, area, cropType);
                        lands.add(land);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lands;
    }

    // Update an existing land record in the database
    public boolean modifyLand(LAND land, boolean modifyLandName, boolean modifyArea, boolean modifyCropType) {
        try (Connection connection = DriverManager.getConnection(url)) {
            // Create the base query
            StringBuilder queryBuilder = new StringBuilder("UPDATE lands SET ");

            // Add conditions for fields to be modified
            List<String> modifications = new ArrayList<>();
            if (modifyLandName) {
                modifications.add("landName = ?");
            }
            if (modifyArea) {
                modifications.add("area = ?");
            }
            if (modifyCropType) {
                modifications.add("cropType = ?");
            }

            // Join modifications with commas
            queryBuilder.append(String.join(", ", modifications));

            // Add WHERE clause
            queryBuilder.append(" WHERE landId = ?");

            try (PreparedStatement statement = connection.prepareStatement(queryBuilder.toString())) {
                // Set parameters based on conditions
                int paramIndex = 1;
                if (modifyLandName) {
                    statement.setString(paramIndex++, land.getLandName());
                }
                if (modifyArea) {
                    statement.setDouble(paramIndex++, land.getArea());
                }
                if (modifyCropType) {
                    statement.setString(paramIndex++, land.getCropType());
                }

                // Set the landId for the WHERE clause
                statement.setInt(paramIndex, land.getLandId());

                // Execute the update
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    // Delete a land record from the database
    public boolean deleteLand(int landId) {
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "DELETE FROM lands WHERE landId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, landId);

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
