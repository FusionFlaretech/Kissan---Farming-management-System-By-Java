package signup;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WeatherForm extends JFrame {

	;

	    private JLabel temperatureLabel;
	    private JLabel cityLabel;
	    private JLabel humidityLabel;
	    private JLabel  rainPredictionLabel;
	    private JLabel windSpeedLabel;
	    private JLabel windIntensityLabel;
	    private JPanel panel_1;
	    private Label label_1;
	    private Label label_2;
	    private Label label_3;
	    private JLabel rainPredictionLabel_1;
	    private JLabel rainPredictionLabel_2;
	    private JLabel rainPredictionLabel_3;
	    private JLabel rainPredictionLabel_4;
	    public WeatherForm(String city) {
	    	getContentPane().setBackground(new Color(169, 169, 169));
	        // Set the system look and feel
	        try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	            e.printStackTrace();
	        }

	        setTitle("Weather Information");
	        setSize(769, 509);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        JPanel infoPanel = new JPanel();
	        infoPanel.setBackground(new Color(50, 205, 50));
getContentPane().setLayout(null);
	        
	        JPanel panel = new JPanel();
	        panel.setBounds(10, 554, 637, -338);
	        getContentPane().add(panel);
	        
	        Label label = new Label("REAL TIME WEATHER INSIGHTS ");
	        label.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 28));
	        label.setAlignment(Label.CENTER);
	        label.setBounds(109, 44, 485, 22);
	        getContentPane().add(label);
	        
	        panel_1 = new JPanel();
	        panel_1.setBackground(new Color(169, 169, 169));
	        panel_1.setBounds(39, 84, 659, 363);
	        getContentPane().add(panel_1);
	        	        panel_1.setLayout(null);
	        
	        	        // Create labels
	        	        cityLabel = new JLabel(city);
	        	        cityLabel.setBounds(28, 21, 162, 31);
	        	        panel_1.add(cityLabel);
	        	        cityLabel.setFont(new Font("Goudy Old Style", Font.PLAIN, 26));
	        	        temperatureLabel = new JLabel();
	        	        temperatureLabel.setForeground(new Color(0, 0, 0));
	        	        temperatureLabel.setBounds(10, 63, 200, 53);
	        	        panel_1.add(temperatureLabel);
	        	        temperatureLabel.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 35));
	        	        windSpeedLabel = new JLabel(" N/A");
	        	        windSpeedLabel.setBounds(460, 49, 232, 53);
	        	        panel_1.add(windSpeedLabel);
	        	        windSpeedLabel.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 25));
	        	        windIntensityLabel = new JLabel(":N/A");
	        	        windIntensityLabel.setBounds(460, 147, 232, 53);
	        	        panel_1.add(windIntensityLabel);
	        	        windIntensityLabel.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 25));
	        	        humidityLabel =new JLabel("N/A");
	        	        humidityLabel.setBounds(470, 235, 246, 53);
	        	        panel_1.add(humidityLabel);
	        	        humidityLabel.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 25));
	        	        rainPredictionLabel =new JLabel("N/A");
	        	        rainPredictionLabel.setBounds(52, 198, 232, 53);
	        	        panel_1.add(rainPredictionLabel);
	        	        rainPredictionLabel.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 25));
	        	        
	        	        label_1 = new Label("WIND SPEED");
	        	        label_1.setFont(new Font("Dialog", Font.PLAIN, 17));
	        	        label_1.setBounds(446, 21, 113, 22);
	        	        panel_1.add(label_1);
	        	        
	        	        label_2 = new Label("Air Pressure");
	        	        label_2.setFont(new Font("Dialog", Font.PLAIN, 17));
	        	        label_2.setBounds(451, 108, 94, 22);
	        	        panel_1.add(label_2);
	        	        
	        	        label_3 = new Label("HUMIDITY ");
	        	        label_3.setFont(new Font("Dialog", Font.PLAIN, 17));
	        	        label_3.setBounds(453, 206, 92, 22);
	        	        panel_1.add(label_3);
	        	        
	        	        rainPredictionLabel_1 = new JLabel("");
	        	        rainPredictionLabel_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Downloads\\pngtree-light-rain-icon-image_1366071.jpg"));
	        	        rainPredictionLabel_1.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 25));
	        	        rainPredictionLabel_1.setBounds(42, 147, 75, 53);
	        	        panel_1.add(rainPredictionLabel_1);
	        	        
	        	        rainPredictionLabel_2 = new JLabel("");
	        	        rainPredictionLabel_2.setForeground(new Color(245, 255, 250));
	        	        rainPredictionLabel_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Downloads\\th.jpeg"));
	        	        rainPredictionLabel_2.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 25));
	        	        rainPredictionLabel_2.setBounds(572, 11, 60, 53);
	        	        panel_1.add(rainPredictionLabel_2);
	        	        
	        	        rainPredictionLabel_3 = new JLabel("");
	        	        rainPredictionLabel_3.setBounds(557, 96, 75, 53);
	        	        panel_1.add(rainPredictionLabel_3);
	        	        rainPredictionLabel_3.setIcon(new ImageIcon(WeatherForm.class.getResource("/iamges/png-clipart-atmospheric-pressure-atmosphere-computer-icons-line-pressure-angle-white.png")));
	        	        rainPredictionLabel_3.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 25));
	        	        
	        	        rainPredictionLabel_4 = new JLabel("N/A");
	        	        rainPredictionLabel_4.setBounds(565, 198, 84, 53);
	        	        panel_1.add(rainPredictionLabel_4);
	        	        rainPredictionLabel_4.setIcon(new ImageIcon(WeatherForm.class.getResource("/iamges/png-clipart-humidity-symbol-computer-icons-temperature-measurement-against-miscellaneous-angle.png")));
	        	        rainPredictionLabel_4.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 25));
	        	        
	        	        Button button = new Button("BACK");
	        	        button.addActionListener(new ActionListener() {
	        	        	public void actionPerformed(ActionEvent e) {
	        	        	
	        	            		admin form1 = new admin();
	        	            	form1.setVisible(true);
	        	            	dispose();
	        	        	}
	        	        });
	        	        button.setBackground(SystemColor.textInactiveText);
	        	        button.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 14));
	        	        button.setBounds(673, 448, 70, 22);
	        	        getContentPane().add(button);
	        try{// Fetch and display weather information
	        String weatherInfo = WeatherAPI.getWeatherInfo(city);
	        System.out.println("API Response: " + weatherInfo); // Print the entire JSON response for debugging

	        if (weatherInfo != null) {
	            JSONObject json = new JSONObject(weatherInfo);

	            // Check if "data" object exists in the JSON response
	            if (json.has("data")) {
	                JSONObject dataObject = json.getJSONObject("data");

	                // Display values
	                JSONObject valuesObject = dataObject.getJSONObject("values");

	                // Display temperature
	                if (valuesObject.has("temperature")) {
	                    double temperature = valuesObject.getDouble("temperature");
	                    temperatureLabel.setText(temperature + "°C");
	                }

	                // Display description from the "weather" array
	                

	                // Display humidity
	                if (valuesObject.has("humidity")) {
	                    double humidity = valuesObject.getDouble("humidity");
	                    humidityLabel.setText( humidity + "%");
	                }
	                if (valuesObject.has("windSpeed")) {
	                    double windSpeed = valuesObject.getDouble("windSpeed");
	                    windSpeedLabel.setText( windSpeed + " m/s");
	                } else {
	                    windSpeedLabel.setText("Wind Speed: N/A");
	                }

	                // Display wind intensity
	                if (valuesObject.has("pressureSurfaceLevel")) {
	                    double pressureSurfaceLevel = valuesObject.getDouble("pressureSurfaceLevel");
	                    windIntensityLabel.setText( pressureSurfaceLevel+"PS");
	                } else {
	                    windIntensityLabel.setText("Wind Intensity: N/A");
	                }
	                // Display rain prediction
	                if (valuesObject.has("precipitationProbability")) {
	                    double rainPrediction = valuesObject.getDouble("precipitationProbability");
	                    rainPredictionLabel.setText( (rainPrediction * 100) + "%");
	                }

	                // Update city label
	                cityLabel.setText( city);
	            } else {
	                System.err.println("Invalid weather information format. Expected 'data' object.");
	            }
	        } else {
	            System.err.println("Weather information is null.");
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	        System.err.println("Error parsing JSON: " + e.getMessage());
	    }

	        // Make the form visible
	        setVisible(true);
	    }

	    
    	
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String city = "London"; // Replace with the desired city
            new WeatherForm(city).setVisible(true);
        });
    }
}
