package com.vehicleanalyzer.dao;

import java.util.List;

import com.vehicleanalyzer.model.EngineData;


public interface EngineDataDAO {
    void saveEngineData(EngineData engineData);
    List<EngineData> getEngineDataByVehicleId(Long vehicleId);
    double getAverageRPMByVehicleId(Long vehicleId);
}