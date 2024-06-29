package signup;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionForm extends JFrame {

    private DefaultTableModel tableModel;
    private JComboBox<String> cropComboBox;
    private JTextField quantityTextField;
    private JTextField transactionTypeTextField;
    private TransactionDAO transactionDAO;

    public static TransactionDAO createTransactionDAO(String url) {
        return TransactionDAO.getInstance(url);
    }

    public TransactionForm() {
    	getContentPane().setBackground(new Color(192, 192, 192));
        // Initialize your DAO classes
        transactionDAO = new TransactionDAO("jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");

        // Create the table model
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Crop ID");
        tableModel.addColumn("Transaction Type");
        tableModel.addColumn("Amount");
        getContentPane().setLayout(null);

        // Create the table using the model
        JTable transactionTable = new JTable(tableModel);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        scrollPane.setBounds(66, 239, 584, 156);
        getContentPane().add(scrollPane);

        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(145, 0, 434, 49);

        getContentPane().add(controlPanel);
        controlPanel.setLayout(null);
        
        Label label = new Label("WELCOME TO TRANSACTION DASHBOARD");
        label.setBackground(new Color(192, 192, 192));
        label.setBounds(-126, 0, 692, 49);
        label.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 19));
        label.setAlignment(Label.CENTER);
        controlPanel.add(label);
        JButton addButton = new JButton("Add Transaction");
        addButton.setFont(new Font("Garamond", Font.BOLD | Font.ITALIC, 14));
        
                // Add action listener to the button
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addTransaction();
                    }
                });
                
                        JPanel buttonPanel = new JPanel();
                        buttonPanel.setBounds(66, 196, 584, 49);
                        getContentPane().add(buttonPanel);
                        buttonPanel.setBackground(SystemColor.textInactiveText);
                        buttonPanel.add(addButton);
                        
                                // Create other UI components (e.g., labels, text fields, buttons) as needed
                                JLabel cropLabel = new JLabel("Select Crop:");
                                cropLabel.setBounds(10, 11, 202, 33);
                                cropLabel.setFont(new Font("Garamond", Font.BOLD | Font.ITALIC, 16));
                                cropComboBox = new JComboBox<>();
                                cropComboBox.setBackground(new Color(169, 169, 169));
                                cropComboBox.setBounds(222, 11, 202, 33);
                                JLabel quantityLabel = new JLabel("Quantity:");
                                quantityLabel.setBounds(10, 54, 202, 33);
                                quantityLabel.setFont(new Font("Garamond", Font.BOLD | Font.ITALIC, 16));
                                quantityTextField = new JTextField();
                                quantityTextField.setBackground(new Color(169, 169, 169));
                                quantityTextField.setBounds(222, 54, 202, 33);
                                JLabel transactionTypeLabel = new JLabel("Transaction Type:");
                                transactionTypeLabel.setBounds(10, 98, 202, 33);
                                transactionTypeLabel.setFont(new Font("Garamond", Font.BOLD | Font.ITALIC, 16));
                                transactionTypeTextField = new JTextField();
                                transactionTypeTextField.setBackground(new Color(169, 169, 169));
                                transactionTypeTextField.setBounds(222, 97, 202, 33);
                                
                                        // Layout the components using BorderLayout
                                        JPanel inputPanel = new JPanel();
                                        inputPanel.setBounds(66, 60, 584, 141);
                                        getContentPane().add(inputPanel);
                                        inputPanel.setBackground(SystemColor.window);
                                        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                                        inputPanel.setLayout(null);
                                        inputPanel.add(cropLabel);
                                        inputPanel.add(cropComboBox);
                                        inputPanel.add(quantityLabel);
                                        inputPanel.add(quantityTextField);
                                        inputPanel.add(transactionTypeLabel);
                                        inputPanel.add(transactionTypeTextField);
                                        
                                        Button button = new Button("BACK");
                                        button.setFont(new Font("Bauhaus 93", Font.BOLD, 14));
                                        button.addActionListener(new ActionListener() {
                                        	public void actionPerformed(ActionEvent e) {
                                        		admin form1=new admin();
                                        		form1.setVisible(true);
                                        		dispose();
                                        	}
                                        });
                                        button.setBounds(663, 399, 94, 22);
                                        getContentPane().add(button);

        // Load initial data into the table
        loadTransactionData();

        // Load crop data and populate the combo box
        loadCropData();
    }

    private void loadCropData() {
        // Assuming you have methods in CROPDAO to get crop details
        List<crop> crops = CROPDAO.getInstance("jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;").getAllCrops();
        for (crop crop : crops) {
            cropComboBox.addItem(crop.getCropName());
        }
    }

    private void loadTransactionData() {
        // Assuming your TransactionDAO has a method to get transactions with all the required information
        List<Transaction> transactions = transactionDAO.getAllTransactions();

        // Clear the table before updating
        tableModel.setRowCount(0);

        // Display the transaction data in the table
        for (Transaction transaction : transactions) {
            Object[] rowData = {
                    transaction.getCropId(),
                    transaction.getTransactionType(),
                    transaction.getAmount()
            };
            tableModel.addRow(rowData);
        }
    }

    private void addTransaction() {
        try {
            // Get the selected crop from the combo box
            String selectedCropName = (String) cropComboBox.getSelectedItem();

            // Get other transaction details from text fields
            int quantity = Integer.parseInt(quantityTextField.getText());
            String transactionType = transactionTypeTextField.getText();double totalAmount = quantity * CROPDAO.getInstance("jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;").getCropPriceByName(selectedCropName);

            // Assuming you have a method in CROPDAO to get crop details by name
            crop selectedCrop = CROPDAO.getCropByName(selectedCropName);

            // Get additional transaction details (description, transaction time)
            // Replace with your method to get description
          //  String description = getDescriptionFromUser();
            
            // Assuming you want to record the current time
            LocalDateTime transactionTime = LocalDateTime.now();

            // Create a Transaction object with the selected crop details
            Transaction newTransaction = new Transaction(0, selectedCrop.getCropId(),totalAmount, transactionType, quantity, transactionTime);

            // Add the transaction to the database
            if (transactionDAO.addTransaction(newTransaction)) {
                JOptionPane.showMessageDialog(this, "Transaction added successfully!");
                loadTransactionData(); // Refresh the data after adding
                clearTransactionFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add transaction.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the quantity.");
        }
    }

    private void clearTransactionFields() {
        quantityTextField.setText("");
        transactionTypeTextField.setText("");
    }
}
