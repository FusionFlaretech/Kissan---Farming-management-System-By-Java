package signup;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class croprecomcontroller {
    private CropView cropView;

    public croprecomcontroller(List<CropModel> crops) {
        // Extract crop names from CropModel objects and create a list of crop names
        List<String> cropNames = new ArrayList<>();
        for (CropModel crop : crops) {
            cropNames.add(crop.getCropName());
        }

        // Create the CropView with the list of crop names
        cropView = new CropView(cropNames);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                List<CropModel> crops = getCropsFromDatabase();
                new croprecomcontroller(crops);
            } catch (Exception e) {
                e.printStackTrace(); // Or handle the exception more gracefully
            }
        });
    }

    private static List<CropModel> getCropsFromDatabase() {
        // Fetch crops from the database and return the list
        // Implement database interaction here
        return DatabaseHandlerecom.getCropsFromDatabase();
    }
}
