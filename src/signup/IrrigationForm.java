package signup;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IrrigationForm extends JFrame {

    private JLabel soilMoistureLabel;
    private JLabel cropTypeLabel;
    private JLabel temperatureLabel;
    private JLabel precipitationProbabilityLabel;
    private JLabel irrigationRecommendationLabel;
    private JPanel panel_3;
    private Button button;

    private ChartPanel chartPanel; // New chart component

    public IrrigationForm(IrrigationData irrigationData, double temperature, double precipitationProbability, double irrigationRecommendation) {
    	setBackground(Color.LIGHT_GRAY);
        getContentPane().setBackground(new Color(169, 169, 169));

        setTitle("Irrigation Information");
        setSize(966, 715);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Existing code...

        // Initialize the chart panel
        ChartPanel chartPanel = createChartPanel("Rice", 0.6, 25.0, 0.2);
        getContentPane().add(chartPanel);

        setVisible(true);
    }

    private ChartPanel createChartPanel(String cropType, double soilMoisture, double temperature, double precipitationProbability) {
        JFreeChart chart = createChart(createDataset(cropType, soilMoisture, temperature, precipitationProbability));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.LIGHT_GRAY);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setLayout(null);
        
        Button button_1 = new Button("BACK");
        button_1.setBackground(Color.GRAY);
        button_1.setFont(new Font("Berlin Sans FB", Font.BOLD | Font.ITALIC, 13));
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
            		admin form1 = new admin();
            	form1.setVisible(true);
            	dispose();
        	}
        });
        button_1.setBounds(870, 654, 70, 22);
        chartPanel.add(button_1);
        return chartPanel;
    }


    private CategoryDataset createDataset(String cropType, double soilMoisture, double temperature, double precipitationProbability) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        IrrigationController irrigationController = new IrrigationController(new WeatherAPI());
        double irrigationRecommendation = irrigationController.analyzeIrrigationNeeds(soilMoisture, temperature, precipitationProbability);

        // Add data for the specified crop type
        dataset.addValue(irrigationRecommendation, "Irrigation Recommendation", cropType);

        // Add data for additional crops (you can add up to 8)
        // Example data for other crops
        dataset.addValue(irrigationController.analyzeIrrigationNeeds(0.3, 25.0, 0.2), "Irrigation Recommendation", "Wheat");
        dataset.addValue(irrigationController.analyzeIrrigationNeeds(0.2, 30.0, 0.3), "Irrigation Recommendation", "Barley");
        dataset.addValue(irrigationController.analyzeIrrigationNeeds(0.6, 28.0, 0.0), "Irrigation Recommendation", "Corn");
        dataset.addValue(irrigationController.analyzeIrrigationNeeds(0.5, 30.0, 0.5), "Irrigation Recommendation", "Tomato");
        dataset.addValue(irrigationController.analyzeIrrigationNeeds(0.7, 25.0, 0.6), "Irrigation Recommendation", "Grapes");
        dataset.addValue(irrigationController.analyzeIrrigationNeeds(1.0, 18.0, 0.7), "Irrigation Recommendation", "Potato");

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Irrigation Recommendation",
                "Crop Type",
                "Recommendation",
                dataset
        );
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IrrigationData irrigationData = new IrrigationData(0.6, "Rice");
            WeatherAPI weatherAPI = new WeatherAPI();
            IrrigationController irrigationController = new IrrigationController(weatherAPI);

            // Fetch weather information and display irrigation needs
            irrigationController.monitorIrrigationNeeds(irrigationData);
        });
    }
}
