package com.vehicleanalyzer.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Tuple;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.vehicleanalyzer.model.FuelData;
import com.vehicleanalyzer.util.HibernateUtil;

public class FuelDataDaoImplementation implements FuelDataDAO {

    private final SessionFactory sessionFactory;

    public FuelDataDaoImplementation() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void saveFuelData(FuelData fuelData) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.persist(fuelData);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RuntimeException("Error saving Fuel data", e);
            }
        }
    }

    @Override
    public List<FuelData> getFuelDataByVehicleId(Long vehicleId) {
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle ID cannot be null");
        }
        try (Session session = sessionFactory.openSession()) {
            session.setDefaultReadOnly(true);
            List<FuelData> resultList = session.createQuery("""
                FROM FuelData f 
                WHERE f.vehicle.id = :vehicleId""",
                    FuelData.class)
                    .setParameter("vehicleId", vehicleId)
                    .getResultList();
            return resultList;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching Fuel data", e);
        }

    }

    @Override
    public double getFuelEfficiencyStats(Long vehicleId) {
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle ID cannot be null");
        }
        try (Session session = sessionFactory.openSession()) {
            session.setDefaultReadOnly(true);

            // Double TotalDist = session.createQuery("SELECT SUM(f.distance_km) FROM FuelData f WHERE f.vehicle.id = :vehicleId", Double.class)
            //         .setParameter("vehicleId", vehicleId)
            //         .uniqueResult();
            // Double TotalFuel = session.createQuery("SELECT SUM(f.liters_used) FROM FuelData f WHERE f.vehicle.id = :vehicleId", Double.class)
            //         .setParameter("vehicleId", vehicleId)
            //         .uniqueResult();
            Tuple result = session.createQuery("""
                SELECT 
                    SUM(f.liters_used) as Tlitre, 
                    SUM(f.distance_km) as TDist 
                FROM FuelData f 
                WHERE f.vehicle.id = :vehicleId""",
                    Tuple.class)
                    .setParameter("vehicleId", vehicleId)
                    .uniqueResult();

            if (result == null) {
                return 0.0;
            }

            double TotalDist = (double) result.get("TDist");
            double TotalFuel = (double) result.get("Tlitre");

            double FuelEfficiency = TotalFuel != 0 ? (TotalDist / TotalFuel) : 0.0;

            return FuelEfficiency;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching Fuel data", e);
        }

    }

    @Override
    public FuelData findById(Long fuelId) {
        if (fuelId == null) {
            throw new IllegalArgumentException("Fuel ID cannot be null");
        }
        try (Session session = sessionFactory.openSession()) {
            session.setDefaultReadOnly(true);
            FuelData fuelData = session.get(FuelData.class, fuelId);
            if (fuelData == null) {
                throw new EntityNotFoundException("Fuel data not found with ID: " + fuelId);
            }
            return fuelData;
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Error fetching Fuel data by id", e);
        }

    }

    @Override
    public void updateFuelData(FuelData fuelData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteFuelDataByFuelId(Long fuelId) {
            if (fuelId == null) {
            throw new IllegalArgumentException("Fuel Id cannot be null");
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                FuelData fuelData = session.get(FuelData.class, fuelId);
                if (fuelData != null) {
                    session.delete(fuelData);
                }
                tx.commit();

            } catch (Exception e) {
                tx.rollback();
                throw new RuntimeException("Error deleting Fuel Id", e);
            }
        }
    }

}
