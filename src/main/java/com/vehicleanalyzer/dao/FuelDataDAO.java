package com.vehicleanalyzer.dao;

import java.util.List;

import com.vehicleanalyzer.model.FuelData;

public interface  FuelDataDAO {
    void saveFuelData(FuelData fuelData);
    List<FuelData> getFuelDataByVehicleId(Long vehicleId);
    double getFuelEfficiencyStats(Long vehicleId);
}
