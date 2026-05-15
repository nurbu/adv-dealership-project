package com.pluralsight.dealership;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;

    // Header for columns

    private static final String ROW_FORMAT =
            "%-8s %-6s %-12s %-12s %-8s %-8s %-10s %-10s%n";
    private static final String FORMATTED_HEADER = Colors.HEADER + String.format(ROW_FORMAT,
            "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price")
            + "-".repeat(80) + Colors.RESET;
    private static final String BOTTOM_DASHES = (Colors.HEADER + "-".repeat(80) + "\n" + Colors.RESET);

    public UserInterface() {
    }

    // Displays menu

    public void display() {

        init();
        // Main menu loop
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 10) {
            System.out.println(Colors.HEADER + "Welcome to the Dealership!" + Colors.RESET + "\n");

            System.out.println("1. Filter by price");
            System.out.println("2. Filter by Make and Model");
            System.out.println("3. Filter by Year");
            System.out.println("4. Filter by Color");
            System.out.println("5. Filter by Mileage");
            System.out.println("6. Filter by Vehicle Type");
            System.out.println("7. See All Vehicles");
            System.out.println("8. Add a Vehicle");
            System.out.println("9. Remove a Vehicle");
            System.out.println("10. Exit");
            System.out.print("Your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println(Colors.ERROR + "Please enter 1-10." + Colors.RESET + "\n");
                scanner.nextLine();                 // discard bad input
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();                     // clear newline

            switch (choice) {
                case 1 -> processGetByPriceRequest(scanner);
                case 2 -> processGetByMakeModelRequest(scanner);
                case 3 -> processGetByYearRequest(scanner);
                case 4 -> processGetByColorRequest(scanner);
                case 5 -> processGetByMileageRequest(scanner);
                case 6 -> processGetByVehicleTypeRequest(scanner);
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest(scanner);
                case 9 -> processRemoveVehicleRequest(scanner);
                case 10 ->
                        System.out.println(Colors.SUCCESS + "\nThank you for visiting our dealership!" + Colors.RESET);
                default -> System.out.println(Colors.ERROR + "Invalid choice!" + Colors.RESET + "\n");
            }
        }
        scanner.close();
    }

    /* --------------------------------------------------------------------------
           Filter functions

           *Get filter inputs
           *Use helper functions from dealership object
           to retrieve a custom list
           *Use display Vehicles to display all vehicles within custom list.

           -------------------------------------------------------------------------- */
    private void processGetByPriceRequest(Scanner scanner) {

        System.out.println(Colors.HEADER + "\nFiltering by Price\n" + Colors.RESET);
        double minPrice = parseDouble(scanner, "Enter Min Price: ");
        double maxPrice = parseDouble(scanner, "Enter Max Price: ");

        System.out.println("Based off your price range of " + minPrice + " and " + maxPrice + ".\n");

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);

        System.out.println(FORMATTED_HEADER);
        displayVehicles(vehicles);
        System.out.println(BOTTOM_DASHES);

    }

    private void processGetByMakeModelRequest(Scanner scanner) {

        System.out.println(Colors.HEADER + "\nFiltering by Make and Model\n" + Colors.RESET);
        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();

        System.out.println("Based off your desired Make: " + make + " and Model:" + model + ".\n");

        List<Vehicle> vehicles = dealership.getVehiclesByMakeAndModel(make, model);

        System.out.println(FORMATTED_HEADER);
        displayVehicles(vehicles);
        System.out.println(BOTTOM_DASHES);

    }

    private void processGetByYearRequest(Scanner scanner) {

        System.out.println(Colors.HEADER + "\nFiltering by Year\n" + Colors.RESET);

        int minYear = parseInt(scanner, "Enter Min Year: ");
        int maxYear = parseInt(scanner, "Enter Max Year: ");

        System.out.println("Based off your Year range of " + minYear + " and " + maxYear + ".\n");

        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);

        System.out.println(FORMATTED_HEADER);
        displayVehicles(vehicles);
        System.out.println(BOTTOM_DASHES);

    }

    private void processGetByColorRequest(Scanner scanner) {

        System.out.println(Colors.HEADER + "\nFiltering by Color\n" + Colors.RESET);
        System.out.print("Enter Color: ");
        String color = scanner.nextLine();

        System.out.println("Based off your desired color of " + color + ".\n");

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);

        System.out.println(FORMATTED_HEADER);
        displayVehicles(vehicles);
        System.out.println(BOTTOM_DASHES);

    }

    private void processGetByMileageRequest(Scanner scanner) {

        System.out.println(Colors.HEADER + "\nFiltering by Mileage\n" + Colors.RESET);
        int minMileage = parseInt(scanner, "Enter Min Mileage: ");
        int maxMileage = parseInt(scanner, "Enter Max Mileage: ");

        System.out.println("Based off your Mileage range of " + minMileage + " and " + maxMileage + ".\n");

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);

        System.out.println(FORMATTED_HEADER);
        displayVehicles(vehicles);
        System.out.println(BOTTOM_DASHES);

    }

    private void processGetByVehicleTypeRequest(Scanner scanner) {

        System.out.println(Colors.HEADER + "\nFiltering by Vehicle Type\n" + Colors.RESET);
        System.out.print("Enter Vehicle Type: ");
        String vehicleType = scanner.nextLine();

        System.out.println("Based off your desired Vehicle Type of " + vehicleType + ".\n");

        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);

        System.out.println(FORMATTED_HEADER);
        displayVehicles(vehicles);
        System.out.println(BOTTOM_DASHES);

    }

    /**
     * Displays all vehicles within dealership's inventory
     */
    private void processGetAllVehiclesRequest() {

        List<Vehicle> vehicles = dealership.getAllVehicles();
        System.out.println("\n" + FORMATTED_HEADER);
        displayVehicles(vehicles);
        System.out.println(BOTTOM_DASHES);

    }

    private void processAddVehicleRequest(Scanner scanner) {

        System.out.println(Colors.HEADER + "\nAdding a Vehicle" + Colors.RESET);

        int vin = parseInt(scanner, "Enter Vin: ");
        int year = parseInt(scanner, "Enter Year  Manufactured: ");
        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Color: ");
        String color = scanner.nextLine();
        int mileage = parseInt(scanner, "Enter Mileage: ");
        double price = parseDouble(scanner, "Enter Price: ");

        System.out.println(Colors.SUCCESS + "\nAdded Vehicle" + Colors.RESET);
        System.out.println("\n" + FORMATTED_HEADER);
        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
        System.out.println(vehicle);
        dealership.addVehicle(vehicle);
        DealershipFileManager.saveDealership(dealership);
        System.out.println(BOTTOM_DASHES);

    }

    private void processRemoveVehicleRequest(Scanner scanner) {

        System.out.println(Colors.HEADER + "\nRemoving a Vehicle" + Colors.RESET);

        int vin = parseInt(scanner, "Enter Vin: ");
        int year = parseInt(scanner, "Enter Year Manufactured: ");
        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Color: ");
        String color = scanner.nextLine();
        int mileage = parseInt(scanner, "Enter Mileage: ");
        double price = parseDouble(scanner, "Enter Price: ");

        System.out.println(Colors.SUCCESS + "\nRemoved Vehicle" + Colors.RESET);
        System.out.println("\n" + FORMATTED_HEADER);
        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
        System.out.println(vehicle);
        dealership.removeVehicle(vehicle);
        DealershipFileManager.saveDealership(dealership);
        System.out.println(BOTTOM_DASHES);

    }



    /* --------------------------------------------------------------------------
       Helper Functions
       -------------------------------------------------------------------------- */

    /**
     * Loads dealership details from dealerShipFileManager
     * into this.dealerShip
     */
    private void init() {
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.getDealership();
    }

    /**
     * Checks if Vehicles not null and empty
     * Prints all the vehicles given within filtered list
     *
     * @param vehicles A custom list of vehicles (filtered)
     */
    private void displayVehicles(List<Vehicle> vehicles) {

        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println(Colors.WARN + "No Vehicles found." + Colors.RESET);
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    /**
     * Handles any invalid inputs
     *
     * @param scanner
     * @param prompt  Allows to work with different prompts.
     * @return When only when input is valid.
     */

    private Double parseDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);

            } catch (NumberFormatException e) {
                System.out.println(Colors.WARN + "Please enter a valid number" + Colors.RESET);
            }
        }
    }

    private int parseInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println(Colors.WARN + "Please enter a valid number" + Colors.RESET);
            }
        }
    }
}
