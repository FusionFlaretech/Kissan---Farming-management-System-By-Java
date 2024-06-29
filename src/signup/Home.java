package signup;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Home frame = new Home();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Home() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 885, 784);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        BackgroundLabel lblBackground = new BackgroundLabel(new ImageIcon(Home.class.getResource("/iamges/DALL·E 2023-12-03 19.18.41 - Modify the background image for the homepage of a farming management system app named 'Kissan'. The image should depict a scenic rural landscape with .png")));
        lblBackground.setBounds(0, 0, 885, 784); // Adjust size as needed
        contentPane.add(lblBackground);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LOGIN form1=new LOGIN();
				 form1.setVisible(true);
				
				dispose();
			}
        	
        });
        btnLogin.setBackground(new Color(34, 139, 34));
        btnLogin.setFont(new Font("Sitka Text", Font.BOLD, 18));
        btnLogin.setBounds(168, 572, 185, 40); // Adjust position and size as needed
        contentPane.add(btnLogin);
        contentPane.setComponentZOrder(btnLogin, 0); // Bring the button to the front
        
        JButton btnSignup = new JButton("SIGNUP");
        btnSignup.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		signupform form1=new signupform();
				 form1.setVisible(true);
				
				dispose();
			}
        	
        });
        btnSignup.setFont(new Font("Sitka Text", Font.BOLD, 18));
        btnSignup.setBackground(new Color(34, 139, 34));
        btnSignup.setBounds(557, 572, 185, 40);
        contentPane.add(btnSignup);
        contentPane.setComponentZOrder(btnSignup, 0); // Send the label to the back
    }

    private class BackgroundLabel extends JLabel {
        Image backgroundImage;

        public BackgroundLabel(ImageIcon icon) {
            super(icon);
            backgroundImage = icon.getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
