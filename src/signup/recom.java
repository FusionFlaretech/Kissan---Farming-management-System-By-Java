package signup;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class recom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					recom frame = new recom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public recom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1275, 539);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setForeground(new Color(204, 204, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JButton btnNewButton = new JButton("BEST CROPS");
		btnNewButton.setBounds(410, 11, 647, 66);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayBestCrops();  // Call a method to display random crops and reasons
			}
		});
		contentPane.setLayout(null);
		btnNewButton.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 29));
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.setForeground(new Color(0, 51, 0));
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("WE HAVE SHORTLISTED THE BEST CROPS");
		lblNewLabel.setBounds(437, 99, 578, 52);
		lblNewLabel.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 25));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ACCORDING TO THE WEATHER CONDITIONS ");
		lblNewLabel_1.setBounds(431, 162, 791, 52);
		lblNewLabel_1.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("AND MAX PROFITABILITY");
		lblNewLabel_2.setBounds(437, 213, 601, 34);
		lblNewLabel_2.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("PLEASE SELECT CASH OR FOOD CROP");
		lblNewLabel_3.setBounds(448, 258, 567, 41);
		lblNewLabel_3.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton_1 = new JButton("CASH CROPS");
		btnNewButton_1.setBounds(437, 319, 587, 35);
		btnNewButton_1.setForeground(new Color(51, 51, 255));
		btnNewButton_1.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayRandomCrops("Cash");  // Call a method to display random cash crops and reasons
			}
		});
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("FOOD CROPS");
		btnNewButton_2.setBounds(437, 370, 587, 34);
		btnNewButton_2.setForeground(new Color(255, 0, 0));
		btnNewButton_2.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayRandomCrops("Food");  // Call a method to display random food crops and reasons
			}
		});
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irr form1 =new irr();
				form1.show();
				
			}
		});
		btnNewButton_3.setBounds(176, 435, 89, 23);
		contentPane.add(btnNewButton_3);
	}

	private void displayRandomCrops(String cropType) {
		String randomCrops = generateRandomCrops();
		String reasons = generateReasons(randomCrops, cropType);
	//	cropInfoLabel.setText("Best " + cropType + " Crops: " + randomCrops + ". Reasons: " + reasons);
	}

	private String generateRandomCrops() {
		Random random = new Random();
		String[] crops = {"Rice", "Corn", "Wheat", "Soybeans", "Sugarcane"};
		return crops[random.nextInt(crops.length)];
	}

	private String generateReasons(String crop, String cropType) {
		// You can customize this method to provide reasons based on weather conditions or other factors.
		// For simplicity, a generic message is returned.
		return "This will be the best crop because of the weather and your profit goals.";
	}

	private void displayBestCrops() {
		// You can customize this method to provide specific best crops based on weather conditions.
		// For simplicity, a generic message is returned.
		//cropInfoLabel.setText("Best crops have been selected based on weather conditions and profitability.");
	}
}