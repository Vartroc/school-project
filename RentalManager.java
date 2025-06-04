import java.util.ArrayList;
import java.util.HashMap;

public class RentalManager {
    // Attribute
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Customer> customers;
    private HashMap<String, String> activeRentals;

    // Konstruktor
    public RentalManager() {
        // ...
    }

    // Methoden
    public void addVehicle(Vehicle vehicle) {
        // ...
    }

    public void registerCustomer(Customer customer) {
        // ...
    }

    public boolean processRental(Customer customer, Vehicle vehicle, int rentalDays) {
        // ...
        return false;
    }

    public void finalizeReturn(Customer customer, Vehicle vehicle) {
        // ...
    }

    public Vehicle findVehicleByCustomer(Customer customer) {
        // ...
        return null;
    }

    public void displayAvailableVehicles() {
        // ...
    }
}
