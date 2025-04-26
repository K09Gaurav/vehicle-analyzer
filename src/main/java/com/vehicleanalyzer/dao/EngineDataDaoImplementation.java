package com.vehicleanalyzer.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.vehicleanalyzer.model.EngineData;
import com.vehicleanalyzer.util.HibernateUtil;

public class EngineDataDaoImplementation implements EngineDataDAO {

    private final SessionFactory sessionFactory;

    public EngineDataDaoImplementation() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void saveEngineData(EngineData engineData) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.persist(engineData);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RuntimeException("Save failed :", e);
            }
        }

    }

    @Override
    public List<EngineData> getEngineDataByVehicleId(Long vehicleId) {
        if (vehicleId == null) throw new IllegalArgumentException("Vehicle ID cannot be null");
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                List<EngineData> resultList = session.createQuery("FROM EngineData e WHERE e.vehicle.id = :vehicleId", EngineData.class)
                        .setParameter("vehicleId", vehicleId)
                        .getResultList();
                tx.commit();
                return resultList;
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RuntimeException("unable to fetch", e);
            }
        }
    }

    @Override
    public double getAverageRPMByVehicleId(Long vehicleId) {
        if (vehicleId == null) throw new IllegalArgumentException("Vehicle ID cannot be null");
        try (Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            try {

                // List<Integer> AllRpm = session.createQuery("SELECT RPM from EngineData e WHERE e.vehicle.id = :vehicleId",Integer.class)
                // .setParameter("vehicleId", vehicleId)
                // .getResultList();
                
                // if (AllRpm.isEmpty()) return 0.0;
                // double avgrpm = AllRpm.stream().mapToInt(Integer::intValue).average().orElse(0.0); 

                Double avgrpm = session.createQuery("SELECT AVG(e.RPM) FROM EngineData e WHERE e.vehicle.id = :vehicleId", Double.class)
                .setParameter("vehicleId", vehicleId)
                .uniqueResult();


                tx.commit();
                return avgrpm != null ? avgrpm : 0.0;
                
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw new RuntimeException("Unable to fetch", e);
            } 

        }
    }

    @Override
    public double getAverageTemperatureByVehicleId(Long vehicleId) {
        if (vehicleId == null) throw new IllegalArgumentException("Vehicle ID cannot be null");

        try (Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            try {
                Double avgtemp = session.createQuery("SELECT AVG(e.Temperature) from EngineData e WHERE e.vehicle.id = :vehicleId", Double.class)
                .setParameter("vehicleId", vehicleId)
                .uniqueResult();
                
                tx.commit();
                return avgtemp != null ? avgtemp : 0.0; 
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw new RuntimeException("Unable to fetch", e);
            }

        }
    }

}
