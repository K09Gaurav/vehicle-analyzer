package com.vehicleanalyzer.dao;

import java.util.List;

import com.vehicleanalyzer.model.Vehicle;

public interface VehicleDAO {
    void saveVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(Long id);
    void deleteVehicle(Long id);
}
