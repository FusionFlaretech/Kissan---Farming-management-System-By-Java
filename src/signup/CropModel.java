package signup;

public class CropModel {
    private String cropName;
    private double idealMoisture;

    public CropModel(String cropName, double idealMoisture) {
        this.cropName = cropName;
        this.idealMoisture = idealMoisture;
    }

    public String getCropName() {
        return cropName;
    }

    public double getIdealMoisture() {
        return idealMoisture;
    }
}