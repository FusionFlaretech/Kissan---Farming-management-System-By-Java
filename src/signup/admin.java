package signup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class admin extends JFrame {

	private JPanel contentPane;
	private JTextField txtFarmerDashboard;

	/**
	 * Launch the application.
	 */public static CROPDAO createCropDAO(String url) {
	        return CROPDAO.getInstance(url);
	    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
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
	public admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("CROP MANAGEMENTS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CROPDAO cropDAO = createCropDAO("jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;");
				cropmanagment form1=new cropmanagment(cropDAO);
				form1.setSize(800, 600);
				form1.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(201, 68, 196, 23);
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("REVENUE ANALYTICS ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Transaction> transactions = TransactionDAO.getInstance("jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;").getAllTransactions();
				  // Set the look and feel
	            JFrame.setDefaultLookAndFeelDecorated(true);
				TransactionSummaryForm form1=new TransactionSummaryForm(transactions);
				
				form1.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(201, 356, 196, 23);
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CROP RECOMMENDATION ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 List<CropModel> crops = getCropsFromDatabase();
	                new croprecomcontroller(crops);
			}
		});
		
		btnNewButton_2.setBounds(201, 422, 217, 23);
		btnNewButton_2.setFont(new Font("SansSerif", Font.BOLD, 14));
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("LAND MANAGEMENT ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   LandDAO landDAO = new LandDAO("jdbc:sqlserver://IBRAHIM\\SQLEXPRESS;Database=test;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
	                LandDataUpdater dataUpdater = new LandDataUpdater();
				LandForm form1=new LandForm(landDAO,dataUpdater);
				form1.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(201, 124, 196, 23);
		btnNewButton_3.setFont(new Font("SansSerif", Font.BOLD, 14));
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("WEATHER FORECAST ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WeatherForm form1=new WeatherForm("london");
				form1.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(201, 178, 196, 23);
		btnNewButton_4.setFont(new Font("SansSerif", Font.BOLD, 14));
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("IRRIGATION SYSTEM");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IrrigationData irrigationData = new IrrigationData(0.6, "Rice");
	            WeatherAPI weatherAPI = new WeatherAPI();
	            IrrigationController irrigationController = new IrrigationController(weatherAPI);

	            // Fetch weather information and display irrigation needs
	            irrigationController.monitorIrrigationNeeds(irrigationData);
			
				
				dispose();
				
			}
		});
		btnNewButton_5.setBounds(201, 237, 196, 23);
		btnNewButton_5.setFont(new Font("SansSerif", Font.BOLD, 14));
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("ADD TRANSACTION");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionForm form1=new TransactionForm();
				form1.setSize(800, 600);
				form1.setVisible(true);
				dispose();
			}
		});
		btnNewButton_6.setBounds(201, 294, 196, 23);
		btnNewButton_6.setFont(new Font("SansSerif", Font.BOLD, 14));
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("BACK");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home form1=new Home();
				form1.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_7.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
		btnNewButton_7.setBackground(new Color(169, 169, 169));
		btnNewButton_7.setBounds(541, 524, 89, 23);
		contentPane.add(btnNewButton_7);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("");
		lblNewJgoodiesLabel.setBounds(0, 30, 647, 541);
		lblNewJgoodiesLabel.setIcon(new ImageIcon(admin.class.getResource("/iamges/DALL·E 2023-12-03 20.48.20 - A realistic and light-colored image representing modern farming technology. The picture should include elements like high-tech farm equipment, precisi (1).png")));
		contentPane.add(lblNewJgoodiesLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(169, 169, 169));
		panel.setBounds(0, 0, 647, 28);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtFarmerDashboard = new JTextField();
		txtFarmerDashboard.setBackground(new Color(169, 169, 169));
		txtFarmerDashboard.setEditable(false);
		txtFarmerDashboard.setBounds(178, 0, 360, 25);
		txtFarmerDashboard.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		txtFarmerDashboard.setText("FARMER DASHBOARD ");
		panel.add(txtFarmerDashboard);
		txtFarmerDashboard.setColumns(10);
	}
	 private static List<CropModel> getCropsFromDatabase() {
	        // Fetch crops from the database and return the list
	        // Implement database interaction here
	        return DatabaseHandlerecom.getCropsFromDatabase();
	    }
}
