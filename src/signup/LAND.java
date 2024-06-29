package signup;

public class LAND {
    private int landId;
    private String landName;
    private double area;
    private String cropType;

    // Constructors
    public LAND() {
    }

    public LAND(int landId, String landName, double area, String cropType) {
        this.landId = landId;
        this.landName = landName;
        this.area = area;
        this.cropType = cropType;
    }

    // Getters and Setters
    public int getLandId() {
        return landId;
    }

    public void setLandId(int landId) {
        this.landId = landId;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    // toString() method for debugging and logging
    @Override
    public String toString() {
        return "Land{" +
                "landId=" + landId +
                ", landName='" + landName + '\'' +
                ", area=" + area +
                ", cropType='" + cropType + '\'' +
                '}';
    }
}
