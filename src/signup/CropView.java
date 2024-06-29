package signup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class CropView extends JFrame {
    private DefaultListModel<String> cropListModel;
    private JList<String> cropList;
    private JTextField profitTextField;
    private JButton recommendButton;
    private List<String> allCropNames;
    private Random random;
    private JTextArea explanationTextArea;
    private JPanel panel;

    public CropView(List<String> cropNames) {
    	setBackground(new Color(128, 128, 128));
    	getContentPane().setBackground(SystemColor.text);
        setTitle("Crop Recommendation System");
        setSize(945, 660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(new BackgroundPanel());
        getContentPane().setLayout(null);

        // Initialize components
        cropListModel = new DefaultListModel<>();
        profitTextField = new JTextField(10);
        allCropNames = cropNames;
        random = new Random();

        // Input panel
        panel = createPanel();
        panel.setBounds(27, 409, 945, 100); // Adjusted panel size
        panel.setBorder(BorderFactory.createTitledBorder("Crop Recommendation"));
        JLabel label = new JLabel("Enter Desired Profit: ");
        label.setBounds(26, 40, 155, 20);
        label.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 15));
        panel.add(label);
        profitTextField.setBounds(206, 43, 150, 20);
        panel.add(profitTextField);
        recommendButton = new JButton("Recommend Crops");
        recommendButton.setBounds(546, 38, 189, 27);
        recommendButton.setBackground(SystemColor.control);
        recommendButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        panel.add(recommendButton);
        getContentPane().add(panel);

        // Crop list panel
        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.LIGHT_GRAY);
        listPanel.setBounds(10, 110, 465, 200);
        listPanel.setBorder(BorderFactory.createTitledBorder("Available Crops"));
        listPanel.setLayout(null);
        JScrollPane listScrollPane = new JScrollPane();
        listScrollPane.setBounds(6, 16, 453, 178);
        listPanel.add(listScrollPane);
        cropList = new JList<>(cropListModel);
        cropList.setBackground(SystemColor.menu);
        listScrollPane.setViewportView(cropList);
        cropList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cropList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        getContentPane().add(listPanel);

        // Explanation panel
        JPanel explanationPanel = new JPanel();
        explanationPanel.setBackground(Color.LIGHT_GRAY);
        explanationPanel.setBounds(480, 110, 455, 200);
        explanationPanel.setBorder(BorderFactory.createTitledBorder("Why These Crops?"));
        explanationPanel.setLayout(new BorderLayout());
        JScrollPane explanationScrollPane = new JScrollPane();
        explanationPanel.add(explanationScrollPane);
        explanationTextArea = new JTextArea(5, 20);
        explanationTextArea.setBackground(SystemColor.text);
        explanationScrollPane.setViewportView(explanationTextArea);
        explanationTextArea.setEditable(false);
        explanationTextArea.setLineWrap(true);
        explanationTextArea.setWrapStyleWord(true);
        explanationTextArea.setOpaque(false);
        getContentPane().add(explanationPanel);
        
        Label label_1 = new Label("CROP RECOMMENDATIONS");
        label_1.setFont(new Font("Arial", Font.BOLD, 26));
        label_1.setBounds(237, 28, 649, 54);
        getContentPane().add(label_1);
        
        Button button = new Button("BACK");
        button.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
        button.setBackground(new Color(128, 128, 128));
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
            		admin form1 = new admin();
            	form1.setVisible(true);
            	dispose();
        	}
        });
        button.setBounds(814, 574, 70, 22);
        getContentPane().add(button);

        // Button action listener
        recommendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double desiredProfit = Double.parseDouble(profitTextField.getText());
                    cropListModel.clear();
                    explanationTextArea.setText(""); // Clear previous explanations
                    int maxCropsToShow = Math.min(6, allCropNames.size());
                    for (int i = 0; i < maxCropsToShow; i++) {
                        int randomIndex = random.nextInt(allCropNames.size());
                        String randomCrop = allCropNames.get(randomIndex);
                        cropListModel.addElement(randomCrop);
                        String explanation = getCropExplanation(randomCrop);
                        explanationTextArea.append("• " + randomCrop + ": " + explanation + "\n");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CropView.this, "Please enter a valid profit value.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private String getCropExplanation(String cropName) {
          switch (cropName.toLowerCase()) {
        case "wheat":
            return "Wheat is recommended due to its hardiness and adaptability to different soils.";
        case "corn":
            return "Corn is chosen for its high yield per acre and its uses in various food products.";
        case "soybeans":
            return "Soybeans are profitable for their oil and as a protein source in animal feed.";
        case "rice":
            return "Rice is ideal for areas with high rainfall and long, warm growing seasons.";
        case "potatoes":
            return "Potatoes are versatile, high in demand, and can be grown in various climates.";
        case "tomatoes":
            return "Tomatoes are recommended for their profitability in fresh markets and processing.";
        default:
            return "This crop is selected for its suitability to current market trends and climate conditions.";
    }
        
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(SystemColor.control);
        panel.setOpaque(false);
        return panel;
    }

    private class BackgroundPanel extends JComponent {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Load background image logic here
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw background image logic here
        }
    }
}
