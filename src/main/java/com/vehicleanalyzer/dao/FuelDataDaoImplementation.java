package com.vehicleanalyzer.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.vehicleanalyzer.model.FuelData;
import com.vehicleanalyzer.util.HibernateUtil;

public class FuelDataDaoImplementation implements FuelDataDAO{

    public FuelDataDaoImplementation() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    @Override
    public void saveFuelData(FuelData fuelData){

    }

    @Override
    public List<FuelData> getFuelDataByVehicleId(Long vehicleId){
        return null;
    }

    @Override
    public double getFuelEfficiencyStats(Long vehicleId){
        return 0.0;
    }

}
