package com.vehicleanalyzer.dao;

import java.util.List;
import java.util.Optional;

import com.vehicleanalyzer.model.FuelData;

public interface  FuelDataDAO {
    //create
    void saveFuelData(FuelData fuelData);
    //read
    Optional<FuelData> findById(Long id);
    List<FuelData> getFuelDataByVehicleId(Long vehicleId);
    double getFuelEfficiencyStats(Long vehicleId);

    //update
    void updateFuelData(FuelData fuelData);

    //delete
    void deleteFuelData(Long id);
}
