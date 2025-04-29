package com.vehicleanalyzer.dao;

import java.util.List;

import com.vehicleanalyzer.model.FuelData;

public interface  FuelDataDAO {
    //create
    void saveFuelData(FuelData fuelData);
    //read
    FuelData findById(Long id);
    List<FuelData> getFuelDataByVehicleId(Long vehicleId);
    double getFuelEfficiencyStats(Long vehicleId);

    //update
    void updateFuelData(FuelData fuelData);

    //delete
    void deleteFuelDataByFuelId(Long fuelId);
}
