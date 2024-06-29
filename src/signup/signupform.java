package signup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Button;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;

public class signupform extends JFrame {

	private JPanel contentPane;
	private final JTextField textField = new JTextField();
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_2;
	 private void writeToFile(String name, String username, String email, String phone, String address) {
	        // File to store data
	        String fileName = "userdata.txt";

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
	            writer.write("Name: " + name + ", Username: " + username + ", Email: " + email + ", Phone: " + phone + ", Address: " + address);
	            writer.newLine();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(signupform.this, "Error writing data to file.");
	        }
	    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signupform frame = new signupform();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public signupform() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 605);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField.setBounds(129, 116, 86, 20);
		
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CREATE AN ACCOUNT");
		lblNewLabel.setBounds(261, 18, 442, 34);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel.setBackground(new Color(0, 128, 0));
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(34, 118, 85, 14);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(34, 167, 69, 14);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(34, 226, 69, 14);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(lblEmail);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(129, 167, 86, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("SignUp");
		btnNewButton.setBounds(129, 386, 89, 23);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
	                String username = textField.getText();  // Change to textField_1.getText()
	                char[] passwordChars = passwordField.getPassword();
	                String password = new String(passwordChars);
	                String email = textField_2.getText();
                   String Phone=textField_3.getText();
                  String address= textField_4.getText();
             //     String address1 = address_1.getText();
                   //String address =textField.getText();
                   String name=textField_1.getText();
	                // JDBC connection parameters
	                String url = "jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;";

	                String user = "ibo";
	                String pass = "admin";

	                try {
	                    // Load the JDBC driver
	                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

	                    // Establish the connection
	                    try (Connection connection = DriverManager.getConnection(url)) {
	                    	System.out.print("Connection establisehd");
	                        String query = "INSERT INTO users (name,username, password, email,phone,address) VALUES (?, ?, ?,?,?,?)";
	                        try (PreparedStatement statement = connection.prepareStatement(query)) {
	                        	  statement.setString(1, name);
	                        	statement.setString(2, username);
	                            statement.setString(3, password);
	                            statement.setString(4, email);
	                            statement.setString(5, Phone);
	                            statement.setString(6,address);
	                            int rowsInserted = statement.executeUpdate();
	                            if (rowsInserted > 0) {
	                                // Change to JOptionPane.showMessageDialog
	                                JOptionPane.showMessageDialog(signupform.this, "Signup successful!");
	                                LOGIN form1=new LOGIN();
	                                form1.setVisible(true);
	                                dispose();
	                                writeToFile(name, username, email, Phone, address);
	                            } else {
	                                // Change to JOptionPane.showMessageDialog
	                                JOptionPane.showMessageDialog(signupform.this, "Signup failed. Please try again.");
	                            }
	                        }
	                    }
	                } catch (ClassNotFoundException | SQLException ex) {
	                    ex.printStackTrace();  // Change to ex.printStackTrace()
	                    // Change to JOptionPane.showMessageDialog
	                    JOptionPane.showMessageDialog(signupform.this, "Error connecting to the database.");
	                }
	            }
	        });
	        contentPane.add(btnNewButton);
	        
	        textField_1 = new JTextField();
	        textField_1.setBounds(129, 75, 86, 20);
	        contentPane.add(textField_1);
	        textField_1.setColumns(10);
	        
	        Label label = new Label("Name");
	        label.setBounds(30, 75, 62, 22);
	        label.setBackground(new Color(240, 248, 255));
	        label.setFont(new Font("Arial", Font.BOLD, 13));
	        contentPane.add(label);
	        
	        textField_3 = new JTextField();
	        textField_3.setBounds(129, 257, 86, 20);
	        contentPane.add(textField_3);
	        textField_3.setColumns(10);
	        
	        Label label_1 = new Label("Phone\r\n");
	        label_1.setBounds(30, 255, 62, 22);
	        label_1.setBackground(new Color(240, 248, 255));
	        label_1.setFont(new Font("Arial", Font.BOLD, 14));
	        contentPane.add(label_1);
	        
	        Label label_2 = new Label("Address");
	        label_2.setBounds(30, 310, 62, 22);
	        label_2.setBackground(new Color(240, 248, 255));
	        label_2.setFont(new Font("Arial", Font.BOLD, 14));
	        contentPane.add(label_2);
	        
	        textField_4 = new JTextField();
	        textField_4.setBounds(129, 310, 86, 20);
	        contentPane.add(textField_4);
	        textField_4.setColumns(10);
	        
	        Button button = new Button("BACK");
	        button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		LOGIN form1=new LOGIN();
					 form1.setVisible(true);
					
					dispose();
				}
	        });
	        button.setFont(new Font("Baskerville Old Face", Font.BOLD, 13));
	        button.setBackground(new Color(128, 128, 128));
	        button.setBounds(768, 536, 70, 22);
	        contentPane.add(button);
	        
	        JPanel panel = new JPanel();
	        panel.setBounds(10, 63, 355, 467);
	        panel.setBackground(new Color(240, 248, 255));
	        contentPane.add(panel);
	        panel.setLayout(null);
	        
	        textField_2 = new JTextField();
	        textField_2.setBounds(116, 161, 86, 20);
	        panel.add(textField_2);
	        textField_2.setColumns(10);
	        
	        JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");
	        lblNewJgoodiesLabel.setIcon(new ImageIcon(signupform.class.getResource("/iamges/DALL·E 2023-12-03 20.23.01 - A background image for a farming management app. The image should depict a harmonious blend of technology and agriculture, featuring elements like a d (1).png")));
	        lblNewJgoodiesLabel.setBounds(375, 63, 463, 467);
	        contentPane.add(lblNewJgoodiesLabel);
	    }
}

