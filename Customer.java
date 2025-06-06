public class Customer {
    private String customerId;
    private String name;
    private String contactInfo;
    private boolean hasActiveRental;
    private Vehicle rentedVehicle;

    public Customer(String customerId, String name, String contactInfo) {
        this.customerId = customerId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.hasActiveRental = false;
        this.rentedVehicle = null;
    }

    public String getCustomerId() {
        return customerId;
    }

    public boolean hasActiveRental() {
        return hasActiveRental;
    }

    public Vehicle getRentedVehicle() {
        return rentedVehicle;
    }

    public void rentVehicle(Vehicle vehicle, RentalManager rentalManager, int days) {
        if (hasActiveRental) {
            System.out.println("Kunde " + customerId + " hat bereits eine aktive Miete.");
            return;
        }
        if (!vehicle.isAvailable()) {
            System.out.println("Fahrzeug " + vehicle.getVehicleId() + " ist gerade nicht verfügbar.");
            return;
        }

        boolean success = rentalManager.processRental(this, vehicle, days);
        if (success) {
            this.rentedVehicle = vehicle;
            this.hasActiveRental = true;
            System.out.println("Kunde " + customerId + " hat Fahrzeug " +
                               vehicle.getVehicleId() + " für " + days + " Tag(e) gemietet.");
        } else {
            System.out.println("Mietvorgang für Kunde " + customerId + " fehlgeschlagen.");
        }
    }

    public void returnVehicle(RentalManager rentalManager) {
        if (!hasActiveRental) {
            System.out.println("Kunde " + customerId + " hat keine aktive Miete.");
            return;
        }

        boolean success = rentalManager.finalizeReturn(this, rentedVehicle);
        if (success) {
            this.hasActiveRental = false;
            this.rentedVehicle = null;
            System.out.println("Kunde " + customerId + " hat das Fahrzeug zurückgegeben.");
        } else {
            System.out.println("Rückgabe für Kunde " + customerId + " fehlgeschlagen.");
        }
    }

    public String getCustomerInfo() {
        return "ID: " + customerId +
               ", Name: " + name +
               ", Kontakt: " + contactInfo +
               ", Aktive Miete: " + (hasActiveRental ? "Ja" : "Nein");
    }
}
