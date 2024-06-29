package signup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JButton;

public class irr extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					irr frame = new irr();
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
	public irr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1274, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("YOUR IRRIGATION NEEDS");
		lblNewLabel.setForeground(new Color(0, 51, 51));
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(393, 11, 563, 50);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(373, 102, 689, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("WHEN DID U LAST IRRIGATED");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 102, 351, 58);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("your irrigation capacity in litres");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(10, 217, 382, 27);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(373, 204, 461, 42);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("ON A SCALE OF 1 TO 10 WHAT IS YOUR ARABILITY");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setBounds(10, 280, 550, 94);
		contentPane.add(lblNewLabel_3);
		
		JSpinner spinner = new JSpinner();
		spinner.setBackground(new Color(153, 255, 255));
		spinner.setForeground(new Color(204, 0, 0));
		spinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinner.setBounds(557, 285, 399, 94);
		contentPane.add(spinner);
		
		JSlider slider = new JSlider();
		slider.setBounds(540, 432, 200, 26);
		contentPane.add(slider);
		
		JLabel lblNewLabel_4 = new JLabel("SET THE YIELD PARAMETER");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_4.setBounds(21, 416, 462, 50);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("YIELD PARAMETER");
		lblNewLabel_5.setBounds(585, 470, 172, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("ENTER DETAILS");
		btnNewButton.setBounds(474, 495, 346, 27);
		contentPane.add(btnNewButton);
	}
}