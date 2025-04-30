package com.vehicleanalyzer.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.vehicleanalyzer.model.EngineData;
import com.vehicleanalyzer.model.Vehicle;


public interface EngineDataDAO {
    ///create
    void saveEngineData(EngineData engineData);
    
    //read
    List<EngineData> getEngineDataByVehicleId(Long vehicleId);
    double getAverageRPMByVehicleId(Long vehicleId);
    double getAverageTemperatureByVehicleId(Long vehicleId);
    EngineData findById(Long engineId);
    
    //update
    EngineData updateEngineData(Long engineId, Integer rpm, Double temperature,LocalDateTime timestamp, Vehicle vehicle);

    //delete
    void deleteEngineDataByEngineId(Long engineId);
}