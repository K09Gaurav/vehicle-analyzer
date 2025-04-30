package com.vehicleanalyzer.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.vehicleanalyzer.model.FuelData;
import com.vehicleanalyzer.model.Vehicle;

public interface  FuelDataDAO {
    //create
    void saveFuelData(FuelData fuelData);
    //read
    FuelData findById(Long id);
    List<FuelData> getFuelDataByVehicleId(Long vehicleId);
    double getFuelEfficiencyStats(Long vehicleId);

    //update
    FuelData updateFuelData(Long fuelId, Double litersUsed, Double distanceKm,LocalDateTime timestamp, Vehicle vehicle);
    
    //delete
    void deleteFuelDataByFuelId(Long fuelId);
}
