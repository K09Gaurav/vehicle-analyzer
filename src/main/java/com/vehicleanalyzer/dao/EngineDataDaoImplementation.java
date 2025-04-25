package com.vehicleanalyzer.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.vehicleanalyzer.model.EngineData;
import com.vehicleanalyzer.util.HibernateUtil;

public class EngineDataDaoImplementation implements EngineDataDAO{

    public EngineDataDaoImplementation() {
        SessionFactory SessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void saveEngineData(EngineData engineData){

    }

    @Override
    public List<EngineData> getEngineDataByVehicleId(Long vehicleId){
        return null;
    }

    @Override
    public double getAverageRPMByVehicleId(Long vehicleId){
        return 0.0;
    }
    
}
