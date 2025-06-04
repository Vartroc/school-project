public class Customer {
    // Attribute
    private String customerId;
    private String name;
    private String contactInfo;
    private boolean hasActiveRental;

    // Konstruktor
    public Customer(String customerId, String name, String contactInfo) {
        // ...
    }

    // Methoden
    public void rentVehicle(Vehicle vehicle, RentalManager rentalManager) {
        // ...
    }

    public void returnVehicle(RentalManager rentalManager) {
        // ...
    }

    public String getCustomerInfo() {
        // ...
        return "";
    }

    public boolean hasActiveRental() {
        // ...
        return false;
    }

    public String getCustomerId() {
        // ...
        return "";
    }
}
