public class Vehicle {
    private String vehicleId;
    private String make;
    private String model;
    private double rentalPricePerDay;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String make, String model, double rentalPricePerDay) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
        this.isAvailable = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rentVehicle() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Vehicle " + vehicleId + " wurde vermietet.");
        } else {
            System.out.println("Vehicle " + vehicleId + " ist nicht verfügbar.");
        }
    }

    public void returnVehicle() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Vehicle " + vehicleId + " wurde zurückgegeben.");
        } else {
            System.out.println("Vehicle " + vehicleId + " war nicht vermietet.");
        }
    }

    public double calculateRentalCost(int days) {
        return rentalPricePerDay * days;
    }

    public String getVehicleInfo() {
        return "ID: " + vehicleId +
               ", Marke: " + make +
               ", Modell: " + model +
               ", Preis/Tag: " + rentalPricePerDay +
               ", verfügbar: " + (isAvailable ? "Ja" : "Nein");
    }
}

