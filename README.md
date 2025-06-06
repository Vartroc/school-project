# Fahrzeugverleih-Anwendung (Java)

*Eine kurze Erklärung der Andwendung. Geschrieben mit KI-Unterstützung*

Dieses Projekt besteht aus vier Klassen, die gemeinsam einen einfachen Fahrzeugverleih umsetzen:

## 1. Vehicle
- **Attribute:**  
  - `vehicleId` (String)  
  - `make`, `model` (String)  
  - `rentalPricePerDay` (double)  
  - `isAvailable` (boolean)  

- **Methoden:**  
  - `rentVehicle()`: Markiert das Fahrzeug als vermietet (`isAvailable = false`).  
  - `returnVehicle()`: Markiert das Fahrzeug als verfügbar (`isAvailable = true`).  
  - `calculateRentalCost(int days)`: Berechnet Preis = `rentalPricePerDay * days`.  
  - `getVehicleInfo()`: Gibt alle Attribute als formatierten String zurück.

## 2. Customer
- **Attribute:**  
  - `customerId`, `name`, `contactInfo` (String)  
  - `hasActiveRental` (boolean)  
  - `rentedVehicle` (Referenz auf aktuelles Vehicle oder `null`)  

- **Methoden:**  
  - `rentVehicle(Vehicle vehicle, RentalManager manager, int days)`:  
    Prüft, ob Kunde noch keine aktive Miete hat und das Fahrzeug verfügbar ist. Ruft `manager.processRental(...)` auf und setzt danach `rentedVehicle` sowie `hasActiveRental = true`.  
  - `returnVehicle(RentalManager manager)`:  
    Prüft, ob der Kunde ein Fahrzeug gemietet hat, und ruft `manager.finalizeReturn(...)` auf. Setzt anschließend `rentedVehicle = null` und `hasActiveRental = false`.  
  - `getCustomerInfo()`: Gibt alle Kundenattribute als Zeichenkette aus.

## 3. RentalManager
- **Attribute:**  
  - `List<Vehicle> vehicles` (alle Fahrzeuge)  
  - `List<Customer> customers` (alle Kunden)  
  - `Map<Customer, Vehicle> activeRentals` (welcher Kunde welches Fahrzeug aktuell hat)  

- **Methoden:**  
  - `addVehicle(Vehicle vehicle)`: Fügt ein neues Fahrzeug zur Flotte hinzu.  
  - `registerCustomer(Customer customer)`: Fügt einen neuen Kunden hinzu.  
  - `processRental(Customer customer, Vehicle vehicle, int rentalDays)`:  
    1. Prüft, ob das Fahrzeug verfügbar ist und der Kunde keine aktive Miete hat.  
    2. Berechnet Mietkosten, ruft `vehicle.rentVehicle()` auf, trägt (`customer`→`vehicle`) in `activeRentals` ein.  
    3. Gibt `true` bei Erfolg, sonst `false`.  
  - `finalizeReturn(Customer customer, Vehicle vehicle)`:  
    1. Prüft, ob der Kunde in `activeRentals` geführt wird und ob das übergebene Fahrzeug übereinstimmt.  
    2. Ruft `vehicle.returnVehicle()`, entfernt den Eintrag aus `activeRentals`.  
    3. Gibt `true` bei Erfolg, sonst `false`.

## 4. RentalApp
- **Attribute:**  
  - `RentalManager rentalManager`  
  - `Scanner scanner` (einzige Instanz für Nutzereingaben)  

- **Methoden:**  
  - `main(String[] args)`: Erzeugt `RentalApp` und startet `startRentalProcess()`.  
  - `seedData()`: Fügt Beispiel-Fahrzeuge und -Kunden hinzu.  
  - `startRentalProcess()`: Hauptmenü-Schleife mit Optionen:  
    1. Verfügbare Fahrzeuge anzeigen  
    2. Alle Kunden anzeigen  
    3. Fahrzeug mieten  
    4. Fahrzeug zurückgeben  
    5. Beenden  
  - `displayAvailableVehicles()`: Listet alle `Vehicle`-Objekte mit `isAvailable == true` auf.  
  - `displayAllCustomers()`: Listet alle Kunden mitsamt Status (`hasActiveRental`).  
  - `handleRentFlow()`:  
    1. Lese Kunden-ID und Fahrzeug-ID ein.  
    2. Lese Mietdauer (Tage) ein.  
    3. Ruft `customer.rentVehicle(...)` auf.  
  - `handleReturnFlow()`:  
    1. Lese Kunden-ID und Fahrzeug-ID ein.  
    2. Ruft `customer.returnVehicle(rentalManager)` auf.  
  - Hilfsmethoden zur Eingabevalidierung:  
    - `readIntInRange(int min, int max)`: Liest eine Ganzzahl zwischen `min` und `max`.  
    - `readPositiveInt()`: Liest eine positive Ganzzahl.  
    - `findCustomerById(String id)`, `findVehicleById(String id)`: Suchen jeweils in den Listen.

---

> **Zusammenfassung:**  
> - **Vehicle** verwaltet Zustand und Kosten eines Fahrzeugs.  
> - **Customer** hält Referenz auf das aktuell gemietete Fahrzeug und nutzt den **RentalManager**, um Mieten und Rückgaben durchzuführen.  
> - **RentalManager** speichert alle Fahrzeuge und Kunden, verwaltet aktive Mieten über eine Map und führt die eigentliche Logik (Verfügbarkeit, Kosten) aus.  
> - **RentalApp** erstellt Beispiel-Daten, bietet ein textbasiertes Menü und delegiert Eingaben an Customer/Manager.
