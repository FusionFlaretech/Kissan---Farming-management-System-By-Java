package signup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private static String url;
    private static TransactionDAO instance;

    public TransactionDAO(String url) {
        this.url = url;
    }

    public static synchronized TransactionDAO getInstance(String url) {
        if (instance == null) {
            instance = new TransactionDAO(url);
        }
        return instance;
    }

    public boolean addTransaction(Transaction transaction) {
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "INSERT INTO Transactions (CropId, TransactionType, Quantity, TransactionDate,  Amount) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, transaction.getCropId());
                statement.setString(2, transaction.getTransactionType());
                statement.setDouble(3, transaction.getquantity());
                statement.setTimestamp(4, Timestamp.valueOf(transaction.getTransactionDate()));
             
                statement.setDouble(5, transaction.getAmount()); // Set the new field

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM Transactions";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        Transaction transaction = new Transaction(
                                resultSet.getInt("TransactionId"),
                                resultSet.getInt("CropId"),
                                resultSet.getDouble("Amount"),
                                resultSet.getString("TransactionType"),
                                resultSet.getInt("quantity"),
                                resultSet.getTimestamp("TransactionDate").toLocalDateTime()
                              
                        );
                        transactions.add(transaction);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return transactions;
    }

    // Additional methods for updating, deleting, or querying transactions can be added as needed

    // Method to retrieve all transactions for a specific crop and land from the database
    public List<Transaction> getAllTransactions(crop crop, LAND landInstance) {
        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM Transactions WHERE CropId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, crop.getCropId());
             

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Transaction transaction = new Transaction(
                                resultSet.getInt("TransactionId"),
                                resultSet.getInt("CropId"),
                                resultSet.getString("TransactionType"),
                                resultSet.getDouble("quantity"),
                                resultSet.getTimestamp("TransactionDate").toLocalDateTime(),
                                resultSet.getString("Description")
                        );
                        transactions.add(transaction);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return transactions;
    }

    public static List<crop> getAllCrops1() {
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
}
