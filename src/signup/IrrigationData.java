package signup;
public class IrrigationData {
private double soilMoisture;
private String cropType;

public IrrigationData(double soilMoisture, String cropType) {
    this.soilMoisture = soilMoisture;
    this.cropType = cropType;
}

public double getSoilMoisture() {
    return soilMoisture;
}

public void setSoilMoisture(double soilMoisture) {
    this.soilMoisture = soilMoisture;
}

public String getCropType() {
    return cropType;
}

public void setCropType(String cropType) {
    this.cropType = cropType;
}
}