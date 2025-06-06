import java.util.ArrayList;
import java.util.List;

public class RentalManager {
    public List<Vehicle> vehicles;
    public List<Customer> customers;

    public RentalManager() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle " + vehicle.getVehicleId() + " added to the fleet.");
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer " + customer.getCustomerId() + " registered.");
    }
    
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    
    public List<Customer> getCustomers() {
        return customers;
    }

    public boolean processRental(Customer customer, Vehicle vehicle, int rentalDays) {
        // checks sind schon in der App, da input check
        
        double cost = vehicle.calculateRentalCost(rentalDays);
        vehicle.rentVehicle();
        System.out.println("Miete war erfolgreich: Kunde " + customer.getCustomerId() +
                           " mietet " + vehicle.getVehicleId() +
                           " für " + rentalDays + " Tag(e). Preis: €" + cost);
        return true;
    }

    public boolean finalizeReturn(Customer customer, Vehicle vehicle) {
        Vehicle rentedVehicle = null;
        if (vehicle != null) {
            rentedVehicle = vehicle;
        } else {
            for (Vehicle v : vehicles) {
                if (!v.isAvailable()) {
                    rentedVehicle = v;
                    break;
                }
            }
        }
        if (rentedVehicle != null) {
            rentedVehicle.returnVehicle();
            System.out.println("Return finalized for Customer " + customer.getCustomerId() + " on Vehicle " + rentedVehicle.getVehicleId() + ".");
            return true;
        } else {
            System.out.println("No rented vehicle found for Customer " + customer.getCustomerId ()+ ".");
            return false;
        }
    }
}

