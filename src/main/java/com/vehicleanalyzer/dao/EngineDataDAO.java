package com.vehicleanalyzer.dao;

import java.util.List;

import com.vehicleanalyzer.model.EngineData;


public interface EngineDataDAO {
    ///create
    void saveEngineData(EngineData engineData);
    
    //read
    List<EngineData> getEngineDataByVehicleId(Long vehicleId);
    double getAverageRPMByVehicleId(Long vehicleId);
    double getAverageTemperatureByVehicleId(Long vehicleId);
    EngineData findById(Long engineId);
    
    //update
    void updateEngineData(EngineData engineData);

    //delete
    void deleteEngineDataByEngineId(Long engineId);
}