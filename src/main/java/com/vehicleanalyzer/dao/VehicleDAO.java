package com.vehicleanalyzer.dao;

import java.util.List;

import com.vehicleanalyzer.model.Vehicle;
import com.vehicleanalyzer.model.enums.EngineType;
import com.vehicleanalyzer.model.enums.FuelType;
import com.vehicleanalyzer.model.enums.VehicleTypes;

public interface VehicleDAO {
    //create
    Long saveVehicle(Vehicle vehicle);
    //read
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(Long id);

    //update
    Vehicle updateVehicle(Long id, String Manufacturer, String Model, int year_of_Mfg, VehicleTypes Type, EngineType Engine_Type, FuelType Fuel_Type);

    //delete
    void deleteVehicle(Vehicle vehicle);
    void deleteVehicleById(Long id);
}
