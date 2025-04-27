package com.vehicleanalyzer.dao;

import java.util.List;

import com.vehicleanalyzer.model.Vehicle;

public interface VehicleDAO {
    //create
    void saveVehicle(Vehicle vehicle);
    //read
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(Long id);

    //update
    void updateVehicle(Vehicle vehicle);

    //delete
    void deleteVehicle(Long id);
}
