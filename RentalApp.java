import java.util.List;
import java.util.Scanner;

public class RentalApp {
    private RentalManager rentalManager;
    private Scanner scanner;

    public RentalApp() {
        rentalManager = new RentalManager();
        scanner = new Scanner(System.in);
        seedData();
    }

    public static void main(String[] args) {
        RentalApp app = new RentalApp();
        app.startRentProcess();
    }

    private void seedData() {
        rentalManager.addVehicle(new Vehicle("V001", "Toyota", "Corolla", 45.0));
        rentalManager.addVehicle(new Vehicle("V002", "Honda", "Civic", 70.0));
        rentalManager.addVehicle(new Vehicle("V003", "Ford", "Focus", 40.0));

        rentalManager.registerCustomer(new Customer("C001", "Alice Müller", "alice@example.com"));
        rentalManager.registerCustomer(new Customer("C002", "Bob Schmidt", "bob@example.com"));
    }

    public void startRentProcess() {
        while (true) {
            System.out.println("\n--- Miet-Menü ---");
            System.out.println("1. Fahrzeuge anzeigen");
            System.out.println("2. Kunden anzeigen");
            System.out.println("3. Fahrzeug mieten");
            System.out.println("4. Fahrzeug zurückgeben");
            System.out.println("5. Exit");
            System.out.print("Bitte wählen (1–5): ");

            int choice = readIntInRange(1, 5);
            switch (choice) {
                case 1:
                    displayAvailableVehicles();
                    break;
                case 2:
                    displayAllCustomers();
                    break;
                case 3:
                    handleRentFlow();
                    break;
                case 4:
                    handleReturnFlow();
                    break;
                case 5:
                    System.out.println("Programm beendet. Auf Wiedersehen!");
                    scanner.close();
                    return;
            }
        }
    }

    private int readIntInRange(int min, int max) {
        int val;
        while (true) {
            if (scanner.hasNextInt()) {
                val = scanner.nextInt();
                scanner.nextLine(); // Newline verwerfen
                if (val >= min && val <= max) {
                    return val;
                }
            } else {
                scanner.nextLine(); // Eingabe verwerfen
            }
            System.out.print("Ungültige Eingabe. Bitte noch einmal (zwischen " + min + " und " + max + "): ");
        }
    }

    private void displayAvailableVehicles() {
        System.out.println("\n--- Fahrzeuge ---");
        for (Vehicle v : rentalManager.getVehicles()) {
            System.out.println(v.getVehicleInfo());
        }
    }

    private void displayAllCustomers() {
        System.out.println("\n--- Kunden ---");
        List<Customer> allCustomers = rentalManager.getCustomers();
        if (allCustomers.isEmpty()) {
            System.out.println("Keine Kunden registriert.");
        } else {
            for (Customer c : allCustomers) {
                System.out.println(c.getCustomerInfo());
            }
        }
    }

    private void handleRentFlow() {
        System.out.print("\nKunden-ID eingeben: ");
        String custId = scanner.nextLine();
        Customer customer = findCustomerById(custId);
        if (customer == null) {
            System.out.println("Kunde nicht gefunden.");
            return;
        }
        if (customer.hasActiveRental()) {
            System.out.println("Dieser Kunde hat bereits eine aktive Miete.");
            return;
        }

        System.out.print("Fahrzeug-ID zum Mieten eingeben: ");
        String vehId = scanner.nextLine();
        Vehicle vehicle = findVehicleById(vehId);
        if (vehicle == null) {
            System.out.println("Fahrzeug nicht gefunden.");
            return;
        }
        if (!vehicle.isAvailable()) {
            System.out.println("Dieses Fahrzeug ist gerade nicht verfügbar.");
            return;
        }

        System.out.print("Anzahl der Miettage eingeben: ");
        int days = readPositiveInt();

        customer.rentVehicle(vehicle, rentalManager, days);
    }

    private void handleReturnFlow() {
        System.out.print("\nKunden-ID eingeben: ");
        String custId = scanner.nextLine().trim();
        Customer customer = findCustomerById(custId);
        if (customer == null) {
            System.out.println("Kunde nicht gefunden.");
            return;
        }
        if (!customer.hasActiveRental()) {
            System.out.println("Dieser Kunde hat keine aktive Miete.");
            return;
        }
        
        customer.returnVehicle(rentalManager);
    }

    private int readPositiveInt() {
        int val;
        while (true) {
            if (scanner.hasNextInt()) {
                val = scanner.nextInt();
                scanner.nextLine();
                if (val > 0) {
                    return val;
                }
            } else {
                scanner.nextLine();
            }
            System.out.print("Ungültige Eingabe. Bitte eine positive Zahl eingeben: ");
        }
    }

    private Customer findCustomerById(String id) {
        for (Customer c : rentalManager.getCustomers()) {
            if (c.getCustomerId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    private Vehicle findVehicleById(String id) {
        for (Vehicle v : rentalManager.getVehicles()) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }
}

