package signup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Button;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class LOGIN extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGIN frame = new LOGIN();
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
	public LOGIN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 50, 424, 348);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Label label_1 = new Label("UserName");
		label_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		label_1.setBounds(81, 86, 102, 22);
		panel.add(label_1);
		
		Label label_2 = new Label("Password");
		label_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		label_2.setBounds(81, 174, 102, 22);
		panel.add(label_2);
		
		TextField textField = new TextField();
		textField.setBounds(219, 86, 120, 22);
		panel.add(textField);
		
		Button button = new Button("LOGIN");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // JDBC connection parameters
                String url = "jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;";
                String user = "ibo";
                String pass = "admin";

                try {
                    // Load the JDBC driver
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                    // Establish the connection
                    try (Connection connection = DriverManager.getConnection(url)) {
                        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setString(1, username);
                            statement.setString(2, password);

                            try (ResultSet resultSet = statement.executeQuery()) {
                                if (resultSet.next()) {
                                    // Login successful
                                    JOptionPane.showMessageDialog(LOGIN.this, "Login successful!");
                                    admin form1=new admin();
                                    form1.setVisible(true);
                                    dispose();
                                } else {
                                    // Login failed
                                    JOptionPane.showMessageDialog(LOGIN.this, "Login failed. Invalid username or password.");
                                }
                            }
                        }
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ((Throwable) ex).printStackTrace();
                    JOptionPane.showMessageDialog(LOGIN.this, "Error connecting to the database.");
                }
           
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 16));
		button.setBounds(228, 233, 70, 22);
		panel.add(button);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(219, 174, 120, 20);
		panel.add(passwordField);
		
		Label label_3 = new Label("Create Account");
		label_3.setFont(new Font("Dialog", Font.ITALIC, 12));
		label_3.setBounds(120, 316, 102, 22);
		panel.add(label_3);
		
		Button button_1 = new Button("Signup");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signupform form1=new signupform();
				 form1.setVisible(true);
				
				dispose();
			}
		});
		button_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		button_1.setBounds(228, 316, 70, 22);
		panel.add(button_1);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setBounds(351, 421, 89, 23);
		panel.add(btnNewButton);
		btnNewButton.setBackground(new Color(128, 128, 128));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		
		JList list = new JList();
		list.setBounds(58, 38, 1, 1);
		panel.add(list);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("LOGIN");
		lblNewJgoodiesLabel.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 28));
		lblNewJgoodiesLabel.setBounds(318, 0, 377, 44);
		contentPane.add(lblNewJgoodiesLabel);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel_1.setIcon(new ImageIcon(LOGIN.class.getResource("/iamges/DALL·E 2023-12-03 19.59.00 - A background image for a farming management app. The image should be tranquil and nature-themed, featuring elements like green fields, blue skies, and (1).png")));
		lblNewJgoodiesLabel_1.setBounds(446, 23, 392, 418);
		contentPane.add(lblNewJgoodiesLabel_1);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home as=new Home();
				as.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(105, 105, 105));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(749, 418, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
