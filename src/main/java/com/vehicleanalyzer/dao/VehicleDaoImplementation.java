package com.vehicleanalyzer.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.vehicleanalyzer.model.Vehicle;
import com.vehicleanalyzer.util.HibernateUtil;

public class VehicleDaoImplementation implements VehicleDAO {

    public VehicleDaoImplementation() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    }    
    
    @Override
    public void saveVehicle(Vehicle vehicle){

    }

    @Override
    public List<Vehicle> getAllVehicles(){

        return null;
    }

    @Override
    public Vehicle getVehicleById(Long id){
        return null;
    }

    @Override
    public void deleteVehicle(Long id){

    }
    
}
