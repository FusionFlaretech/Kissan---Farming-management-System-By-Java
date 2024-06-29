package signup;

import java.util.ArrayList;
import java.util.List;

public class LandDataUpdater {
    private List<LandObserver> observers = new ArrayList<>();

    public void addObserver(LandObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LandObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(List<LAND> updatedLandData) {
        for (LandObserver observer : observers) {
            observer.updateLandData(updatedLandData);
        }
    }

    // Method to update land data (e.g., after adding, modifying, or deleting land)
    public void updateLandData(List<LAND> updatedLandData) {
        notifyObservers(updatedLandData);
    }
}

