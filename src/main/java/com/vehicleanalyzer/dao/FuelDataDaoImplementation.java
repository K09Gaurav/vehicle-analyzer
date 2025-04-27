package com.vehicleanalyzer.dao;

import java.util.List;

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
            Transaction tx = session.beginTransaction();
            try {
                List<FuelData> resultList = session.createQuery("FROM FuelData f WHERE f.vehicle.id = :vehicleId", FuelData.class)
                        .setParameter("vehicleId", vehicleId)
                        .getResultList();
                tx.commit();
                return resultList;
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RuntimeException("Error fetching Fuel data", e);
            }
        }
    }

    @Override
    public double getFuelEfficiencyStats(Long vehicleId) {
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle ID cannot be null");
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                // Double TotalDist = session.createQuery("SELECT SUM(f.distance_km) FROM FuelData f WHERE f.vehicle.id = :vehicleId", Double.class)
                //         .setParameter("vehicleId", vehicleId)
                //         .uniqueResult();

                // Double TotalFuel = session.createQuery("SELECT SUM(f.liters_used) FROM FuelData f WHERE f.vehicle.id = :vehicleId", Double.class)
                //         .setParameter("vehicleId", vehicleId)
                //         .uniqueResult();
                Object[] result = session.createQuery("SELECT SUM(f.liters_used), SUM(f.distance_km) FROM FuelData f WHERE f.vehicle.id = :vehicleId", Object[].class)
                        .setParameter("vehicleId", vehicleId)
                        .uniqueResult();

                if (result == null) {
                    return 0.0;
                }

                double TotalDist = (double) result[1];
                double TotalFuel = (double) result[0];

                double FuelEfficiency = TotalFuel != 0 ? (TotalDist / TotalFuel) : 0.0;

                tx.commit();
                return FuelEfficiency;
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RuntimeException("Error fetching Fuel data", e);
            }
        }
    }

}
