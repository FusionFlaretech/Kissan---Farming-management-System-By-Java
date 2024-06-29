package signup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Button;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class cropmanagment extends JFrame {
    private CROPDAO cropDAO;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTable table;
    private DefaultTableModel tableModel;

    // Factory Method to create CropDAO instance
    public static CROPDAO createCropDAO(String url) {
        return CROPDAO.getInstance(url);
    }

    public cropmanagment(CROPDAO cropDAO) {
        this.cropDAO = cropDAO;

        Panel panel = new Panel();
        panel.setBackground(new Color(211, 211, 211));
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(66, 33, 677, 440);
        panel_1.setBackground(new Color(255, 255, 255));
        panel.add(panel_1);
        SpringLayout sl_panel_1 = new SpringLayout();
        panel_1.setLayout(sl_panel_1);

        Label label = new Label("NAME");
        sl_panel_1.putConstraint(SpringLayout.NORTH, label, 83, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, label, 78, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.SOUTH, label, 105, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, label, 140, SpringLayout.WEST, panel_1);
        label.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(label);

        Label label_1 = new Label("TYPE");
        sl_panel_1.putConstraint(SpringLayout.NORTH, label_1, 128, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, label_1, 78, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.SOUTH, label_1, 150, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, label_1, 140, SpringLayout.WEST, panel_1);
        label_1.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(label_1);

        Label label_2 = new Label("PRICE PER UNIT");
        sl_panel_1.putConstraint(SpringLayout.NORTH, label_2, 174, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, label_2, 48, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.SOUTH, label_2, 196, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, label_2, 175, SpringLayout.WEST, panel_1);
        label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(label_2);

        Label label_3 = new Label("QUANTITY ");
        sl_panel_1.putConstraint(SpringLayout.NORTH, label_3, 230, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, label_3, 60, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.SOUTH, label_3, 252, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, label_3, 152, SpringLayout.WEST, panel_1);
        label_3.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(label_3);

        textField = new JTextField();
        sl_panel_1.putConstraint(SpringLayout.NORTH, textField, 83, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, textField, 182, SpringLayout.WEST, panel_1);
        panel_1.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        sl_panel_1.putConstraint(SpringLayout.NORTH, textField_1, 128, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, textField_1, 182, SpringLayout.WEST, panel_1);
        panel_1.add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        sl_panel_1.putConstraint(SpringLayout.NORTH, textField_2, 174, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, textField_2, 182, SpringLayout.WEST, panel_1);
        panel_1.add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        sl_panel_1.putConstraint(SpringLayout.NORTH, textField_3, 230, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, textField_3, 182, SpringLayout.WEST, panel_1);
        panel_1.add(textField_3);
        textField_3.setColumns(10);

        JButton btnNewButton = new JButton("ADD");
        sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton, 350, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton, 10, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.SOUTH, btnNewButton, 373, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, btnNewButton, 99, SpringLayout.WEST, panel_1);
        btnNewButton.setBackground(new Color(30, 144, 255));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCrop();
            }
        });
        panel_1.add(btnNewButton);
        
        // Create a DefaultTableModel with column names
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Type");
        tableModel.addColumn("Price Per Unit");
        tableModel.addColumn("Quantity");

        // Create the JTable with the DefaultTableModel
        table = new JTable(tableModel);

        // Create a JScrollPane and add the JTable to it
        JScrollPane scrollPane = new JScrollPane(table);
        sl_panel_1.putConstraint(SpringLayout.NORTH, scrollPane, 83, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, scrollPane, 326, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.SOUTH, scrollPane, 318, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, scrollPane, 653, SpringLayout.WEST, panel_1);

        // Add the JScrollPane to the panel
        panel_1.add(scrollPane);
        
        JButton btnNewButton_1 = new JButton("DELETE ");
        sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton_1, 350, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton_1, 151, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 373, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, btnNewButton_1, 251, SpringLayout.WEST, panel_1);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		deleteCrop();
        	}
        });
        btnNewButton_1.setBackground(new Color(100, 149, 237));
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        panel_1.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("MODIFY");
        sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton_2, 350, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton_2, 294, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.SOUTH, btnNewButton_2, 373, SpringLayout.NORTH, panel_1);
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modifyCrop();
        	}
        });
        btnNewButton_2.setBackground(new Color(100, 149, 237));
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        panel_1.add(btnNewButton_2);
        
        JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("");
        lblNewJgoodiesLabel.setBounds(0, 0, 824, 748);
        lblNewJgoodiesLabel.setIcon(new ImageIcon(cropmanagment.class.getResource("/iamges/DALL·E 2023-12-03 21.46.48 - A simple and serene image of a traditional farm. The scene includes a classic red barn, a green tractor, fields of crops, and a clear blue sky. The st (1).png")));
        panel.add(lblNewJgoodiesLabel);
        
        Label label_4 = new Label("WELCOME TO CROP MANAGEMENT DASHBOARD");
        label_4.setBounds(160, 10, 501, 22);
        panel.add(label_4);
        sl_panel_1.putConstraint(SpringLayout.NORTH, label_4, 10, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, label_4, 93, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.SOUTH, label_4, 32, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, label_4, 594, SpringLayout.WEST, panel_1);
        
        JButton btnNewButton_3 = new JButton("BACK");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		admin form1=new admin();
        		form1.setVisible(true);
        		dispose();
        	}
        });
        btnNewButton_3.setFont(new Font("Stencil", Font.BOLD, 14));
        sl_panel_1.putConstraint(SpringLayout.SOUTH, btnNewButton_3, -10, SpringLayout.SOUTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, btnNewButton_3, -77, SpringLayout.EAST, panel_1);
        panel_1.add(btnNewButton_3);
        label_4.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));

        // Initialize your form components and layout
        loadData();
    }

    private void addCrop() {
        String cropName = textField.getText();
        String cropType = textField_1.getText();
        double pricePerUnit = Double.parseDouble(textField_2.getText());
        int quantityInStock = Integer.parseInt(textField_3.getText());

        crop crop = new crop(cropName, cropType, pricePerUnit, quantityInStock);

        if (cropDAO.addCrop(crop)) {
            JOptionPane.showMessageDialog(cropmanagment.this, "Crop added successfully!");
            loadData(); // Refresh the table after adding a crop
        } else {
            JOptionPane.showMessageDialog(cropmanagment.this, "Failed to add crop.");
        }
    }

    private void loadData() {
        try {
            // Fetch crops data from the database
            List<crop> crops = cropDAO.getAllCrops();

            // Clear the table before updating
            tableModel.setRowCount(0);

            // Display the data in the table
            for (crop crop : crops) {
                Object[] rowData = {
                		crop.getCropId(),
                        crop.getCropName(),
                        crop.getCropType(),
                        crop.getPricePerUnit(),
                        crop.getQuantityInStock()
                };
                tableModel.addRow(rowData);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(cropmanagment.this, "Error loading data from the database.");
        }
    }


  

    private void deleteCrop() {
        // Check if a row is selected
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(cropmanagment.this, "Please select a row to delete.");
            return;
        }

        // Get the crop ID from the selected row
        int cropId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());

        // Perform the deletion
        if (cropDAO.deleteCrop(cropId)) {
            JOptionPane.showMessageDialog(cropmanagment.this, "Crop deleted successfully!");
            loadData(); // Refresh the table after deleting a crop
        } else {
            JOptionPane.showMessageDialog(cropmanagment.this, "Failed to delete crop.");
        }
    }


    private void modifyCrop() {
        // Check if a row is selected
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(cropmanagment.this, "Please select a row to modify.");
            return;
        }

        // Get the crop ID from the selected row
        int cropId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());

        // Get the existing values from the selected row
        String existingName = tableModel.getValueAt(selectedRow, 1).toString();
        String existingType = tableModel.getValueAt(selectedRow, 2).toString();
        Double existingPricePerUnit = Double.parseDouble(tableModel.getValueAt(selectedRow, 3).toString());
        Integer existingQuantityInStock = Integer.parseInt(tableModel.getValueAt(selectedRow, 4).toString());

        // Get the modified values from the text fields
        String modifiedName = textField.getText().isEmpty() ? existingName : textField.getText();
        String modifiedType = textField_1.getText().isEmpty() ? existingType : textField_1.getText();
        Double modifiedPricePerUnit = textField_2.getText().isEmpty() ? existingPricePerUnit : Double.parseDouble(textField_2.getText());
        Integer modifiedQuantityInStock = textField_3.getText().isEmpty() ? existingQuantityInStock : Integer.parseInt(textField_3.getText());

        // Create a modified crop object
        crop modifiedCrop = new crop(cropId, modifiedName, modifiedType, modifiedPricePerUnit, modifiedQuantityInStock);

        // Perform the modification
        if (cropDAO.modifyCrop(modifiedCrop)) {
            JOptionPane.showMessageDialog(cropmanagment.this, "Crop modified successfully!");
            loadData(); // Refresh the table after modifying a crop
        } else {
            JOptionPane.showMessageDialog(cropmanagment.this, "Failed to modify crop.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Use the factory method to create CropDAO instance
                CROPDAO cropDAO = createCropDAO("jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;");
                cropmanagment cropManagementForm = new cropmanagment(cropDAO);
                cropManagementForm.setSize(600, 500);
                cropManagementForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                cropManagementForm.setVisible(true);
            }
        });
    }
}