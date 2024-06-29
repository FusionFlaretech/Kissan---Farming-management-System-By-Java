package signup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.formdev.flatlaf.FlatDarkLaf;  // For a dark theme, you can use FlatDarkLaf
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.border.LineBorder;
public class LandForm extends JFrame implements LandObserver {
    private LandDAO landDAO;
    private JTable table;
    private DefaultTableModel tableModel;
 //   private JTextField landNameField;
    private JTextField areaField;
    private JTextField cropTypeField;
	private JTextArea textArea;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

    public LandForm(LandDAO landDAO, LandDataUpdater dataUpdater) {
    	this.landDAO = landDAO;
        dataUpdater.addObserver(this);

        // Set FlatLaf as the look and feel
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        initComponents();
        loadData();
    }

    private void initComponents() {
        getContentPane().setBackground(SystemColor.activeCaptionBorder);

      /*  landNameField = createStyledTextField();
        areaField = createStyledTextField();
        cropTypeField = createStyledTextField();
        inputPanel.setLayout(null);

        inputPanel.add(createLabel("Land Name:"));
        inputPanel.add(landNameField);
        inputPanel.add(createLabel("Area:"));
        inputPanel.add(areaField);
        inputPanel.add(createLabel("Crop Type:"));
        inputPanel.add(cropTypeField);
*/
        // Create a button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10, 567, 940, 32);
        JButton addButton = createStyledButton("Add Land");
        JButton refreshButton = createStyledButton("Refresh");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLand();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
        buttonPanel.setLayout(null);

        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);

        // Create a table with a DefaultTableModel
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Land ID");
        tableModel.addColumn("Land Name");
        tableModel.addColumn("Area");
        tableModel.addColumn("Crop Type");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 273, 950, 283);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        getContentPane().setLayout(null);
        getContentPane().add(scrollPane);
        getContentPane().add(buttonPanel);
        
        JButton btnNewButton = new JButton("DELETE");
        btnNewButton.setForeground(SystemColor.textHighlightText);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		deleteLand();
        	}
        });
        btnNewButton.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        btnNewButton.setBounds(413, 0, 89, 34);
        buttonPanel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("MODIFY");
        btnNewButton_1.setForeground(SystemColor.textHighlightText);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {modifyLand();
        	}
        	
        });
        btnNewButton_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        btnNewButton_1.setBounds(670, 0, 89, 34);
        buttonPanel.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("BACK");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		admin form1 = new admin();
        	form1.setVisible(true);
        	dispose();        	}
        });
        btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnNewButton_2.setBounds(816, 6, 81, 22);
        buttonPanel.add(btnNewButton_2);
        
        textField_2 = new JTextField();
        textField_2.setBounds(170, 169, 142, 20);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);
        
        textField_3 = new JTextField();
        textField_3.setBounds(170, 223, 142, 20);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);
        
        Label label_1 = new Label("CROP TYPE");
        label_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        label_1.setBounds(46, 169, 106, 22);
        getContentPane().add(label_1);
        
        Label label_2 = new Label("AREA");
        label_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        label_2.setBounds(56, 223, 62, 22);
        getContentPane().add(label_2);
        
        Label label_4 = new Label("WELCOME TO LAND MANAGEMENT DASHBOARD");
        label_4.setAlignment(Label.CENTER);
        label_4.setBounds(179, 27, 559, 46);
        getContentPane().add(label_4);
        label_4.setFont(new Font("HelveticaNeueLT Std", Font.BOLD | Font.ITALIC, 22));
        
        textField_1 = new JTextField();
        textField_1.setBounds(170, 108, 146, 20);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        Label label = new Label(" LAND NAME");
        label.setBounds(46, 108, 107, 22);
        getContentPane().add(label);
        label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));

        setTitle("LAND Management");
        setSize(973, 648);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setBounds(20, 51, 544, 21);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBackground(new Color(204, 255, 204)); // Set background color to light green
        textField.setBorder(BorderFactory.createLineBorder(new Color(102, 153, 102), 2)); // Set border color
        return textField;
    }

    private JLabel createLabel(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setBounds(20, 24, 544, 21);
        label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        label.setForeground(new Color(0, 0, 0)); // Set text color to dark green
        return label;
    }

    private JButton createStyledButton(String buttonText) {
        JButton btnAdd = new JButton("Add ");
        btnAdd.setBounds(119, 0, 94, 34);
        btnAdd.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        btnAdd.setBackground(SystemColor.controlDkShadow); // Set background color to dark green
        btnAdd.setForeground(SystemColor.textHighlightText);
        return btnAdd;
    }

   
    private void loadData() {
        List<LAND> lands = landDAO.getAllLands();
        displayData(lands);
        saveToFile(lands);
    }

    private void displayData(List<LAND> lands) {
        // Clear the table before updating
        tableModel.setRowCount(0);

        // Display the data in the table
     // Display the data in the table
        for (LAND land : lands) {
            Object[] rowData = {
            		land.getLandId(),
                    land.getLandName(),
                    land.getArea(),
                    land.getCropType()
            };
            tableModel.addRow(rowData);
        }

    }
    private void saveToFile(List<LAND> lands) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("land_data.txt"))) {
            for (LAND land : lands) {
                writer.write(land.getLandId() + "," + land.getLandName() + "," + land.getArea() + "," + land.getCropType());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addLand() {
        try {
            String landName = textField_1.getText();
            double area = Double.parseDouble(textField_3.getText());
            String cropType = textField_2.getText();

            LAND newLand = new LAND(0, landName, area, cropType); // 0 for auto-generated ID

            if (landDAO.addLand(newLand)) {
                JOptionPane.showMessageDialog(this, "Land added successfully!");
                loadData(); // Refresh the data after adding
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add land.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the area.");
        }
    }

    private void deleteLand() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Get the value as a String
        	 int landIdToDelete = (int) table.getValueAt(selectedRow, 0);

          
                

                if (landDAO.deleteLand(landIdToDelete)) {
                    JOptionPane.showMessageDialog(this, "Land deleted successfully!");
                    loadData(); // Refresh the data after deleting
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete land.");
                }
            } 
            
         else {
            JOptionPane.showMessageDialog(this, "Please select a land to delete.");
        }
    }

    private void modifyLand() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            try {
                int landIdToModify = (int) table.getValueAt(selectedRow, 0);

                // Get the existing values from the selected row
                String existingLandName = table.getValueAt(selectedRow, 1).toString();
                double existingArea = Double.parseDouble(table.getValueAt(selectedRow, 2).toString());
                String existingCropType = table.getValueAt(selectedRow, 3).toString();

                // Get the modified values from the text fields
                String modifiedLandName = textField_1.getText().isEmpty() ? existingLandName : textField_1.getText();
                double modifiedArea = textField_3.getText().isEmpty() ? existingArea : Double.parseDouble(textField_3.getText());
                String modifiedCropType = textField_2.getText().isEmpty() ? existingCropType : textField_2.getText();

                // Create a LAND object with the modified fields
                LAND modifiedLand = new LAND(landIdToModify, modifiedLandName, modifiedArea, modifiedCropType);

                // Specify which fields to modify (true for modified, false for unchanged)
                boolean modifyLandName = ! textField_1.getText().isEmpty();
                boolean modifyArea = !textField_3.getText().isEmpty();
                boolean modifyCropType = !textField_2.getText().isEmpty();

                // Call the modifyLand method with the specified modifications
                if (landDAO.modifyLand(modifiedLand, modifyLandName, modifyArea, modifyCropType)) {
                    JOptionPane.showMessageDialog(this, "Land modified successfully!");
                    loadData(); // Refresh the data after modification
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to modify land.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for the modified fields.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a land to modify.");
        }
    }



    private void clearFields() {
        textField_1.setText("");
        textField_3.setText("");
        textField_2.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Use the factory method to create LandDAO instance
                LandDAO landDAO = new LandDAO("jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
                LandDataUpdater dataUpdater = new LandDataUpdater();

                LandForm landForm = new LandForm(landDAO, dataUpdater);
                landForm.setSize(973, 648);
                landForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                landForm.setLocationRelativeTo(null); // Center the frame on the screen
                landForm.setVisible(true);
            }
        });
    }

	@Override
	public void updateLandData(List<LAND> updatedLandData) {
		// TODO Auto-generated method stub
		  displayData(updatedLandData);
		  saveToFile(updatedLandData);
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
