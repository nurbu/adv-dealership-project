package com.pluralsight.dealership;

import java.util.ArrayList;
import java.util.List;

// Dealership details

public class Dealership {
    private String name;
    private String address;
    private String phone;

    // Holds all the vehicles in this dealership

    private List<Vehicle> vehicles;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        vehicles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   /* --------------------------------------------------------------------------
       Filter Functions
       -------------------------------------------------------------------------- */

    public List<Vehicle> getVehiclesByPrice(double min, double max) {

        List<Vehicle> cars = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                cars.add(vehicle);
            }
        }
        return cars;
    }

    public List<Vehicle> getVehiclesByMakeAndModel(String make, String model) {
        List<Vehicle> cars = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getMake().equals(make) && vehicle.getModel().equals(model)) {
                cars.add(vehicle);
            }
        }
        return cars;

    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> cars = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                cars.add(vehicle);
            }
        }
        return cars;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> cars = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getColor().equals(color)) {
                cars.add(vehicle);
            }
        }
        return cars;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {

        List<Vehicle> cars = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                cars.add(vehicle);
            }
        }
        return cars;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {

        List<Vehicle> cars = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleType().equals(vehicleType)) {
                cars.add(vehicle);
            }
        }
        return cars;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    /* --------------------------------------------------------------------------
       Modify inventory.csv Functions
       -------------------------------------------------------------------------- */

    public void addVehicle(Vehicle vehicle) {

        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {

        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVin() == vehicle.getVin()) {
                vehicles.remove(i);
                return;
            }
        }

    }


}
