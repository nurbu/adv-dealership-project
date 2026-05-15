package com.pluralsight.dealership;

import java.io.*;

public class DealershipFileManager {

    public static Dealership getDealership() {

        File file = new File("inventory.csv");
        Dealership dealership = null;

        if (!file.exists()) {
            try {
                // Creates new "inventory.csv" and lets user know.
                if (file.createNewFile()) {
                    System.out.println(Colors.WARN + "New \"inventory.csv\" file created" + Colors.RESET);
                }
            } catch (IOException e) {
                // Catches any errors when creating file.
                System.out.println(Colors.ERROR + "Error creating file: " + e.getMessage() + Colors.RESET);
            }
            // If "inventory.csv" didn't exist, dealership has no details.
            return dealership;
        }

        // Uses try-with-resources to auto-close BufferReader
        try (BufferedReader br = new BufferedReader(new FileReader("inventory.csv"))) {

            // Handles the header line

            String headerLine = br.readLine();

            // If file is truly empty returns null.

            if (headerLine == null || headerLine.isBlank()) {
                System.out.println(Colors.ERROR + "Header missing or blank" + Colors.RESET);
                return dealership;
            }
            String[] header = headerLine.split("\\|");
            // Handles blank header and less than over
            if (header.length != 3) {
                System.out.println(Colors.ERROR + "Invalid header" + headerLine + Colors.RESET);
                return dealership;
            }

            dealership = new Dealership(header[0], header[1], header[2]);

            String line;
            while ((line = br.readLine()) != null) {
                /**
                 * Catches bad lines and skips them to prevent exception.
                 * Handles all vehicle object creation
                 */
                try {

                    String[] values = line.split("\\|");

                    if (values.length != 8) {
                        throw new Exception(Colors.WARN + "Incomplete line " + line + Colors.RESET);
                    }

                    int vin = parseInt(values[0]);
                    int year = parseInt(values[1]);
                    String make = values[2];
                    String model = values[3];
                    String vehicleType = values[4];
                    String color = values[5];
                    int odometer = parseInt(values[6]);
                    double price = parseDouble(values[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    dealership.addVehicle(vehicle);
                }
                // Catches all general errors within each transaction.
                catch (Exception e) {
                    System.out.println(Colors.WARN + "Skipping bad line: " + line + "(" + e.getMessage() + ")" + Colors.RESET);
                }
            }
            // catches file not being found or can't read, etc....
        } catch (IOException e) {
            System.out.println(Colors.ERROR + "Error reading file:  " + e.getMessage() + Colors.RESET);
        }
        return dealership;
    }

    /**
     * Updates inventory.csv
     *
     * @param dealership
     */

    public static void saveDealership(Dealership dealership) {

        // Uses try-with-resources to auto-close BufferReader

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.csv"))) {

            // Writes the header

            bw.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            bw.newLine();

            // Loops through vehicles list within dealership and writes to inventory.csv
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                bw.write(vehicle.getVin() + "|" + vehicle.getYear() + "|"
                        + vehicle.getMake() + "|" + vehicle.getModel() + "|"
                        + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|"
                        + vehicle.getOdometer() + "|" + vehicle.getPrice());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println(Colors.ERROR + "Error writing to file: " + e.getMessage() + Colors.RESET);
        }
    }


    /* --------------------------------------------------------------------------
       Helper Functions

     * Parse double and int strings
     * if s is empty throws Exception with Custom message (skipped by user)
     * Uses try/catch to check if user input valid.
     * if s is not a number or in a Double format throws Exception

       -------------------------------------------------------------------------- */

    private static Double parseDouble(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException(Colors.WARN + "Input can't be empty" + Colors.RESET);
        }
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Colors.WARN + "Invalid number: " + s + Colors.RESET);
        }
    }

    private static int parseInt(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException(Colors.WARN + "Input can't be empty" + Colors.RESET);
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Colors.WARN + "Invalid number: " + s + Colors.RESET);
        }
    }
}
