package com.vehicleanalyzer.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.vehicleanalyzer.model.Vehicle;
import com.vehicleanalyzer.util.HibernateUtil;

public class VehicleDaoImplementation implements VehicleDAO {

    private final SessionFactory sessionFactory;

    public VehicleDaoImplementation() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Long saveVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.persist(vehicle);
                tx.commit();
                return vehicle.getid();
            } catch (Exception e) {
                tx.rollback();
                throw new RuntimeException("error saving Vehicle", e);
            }
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        try (Session session = sessionFactory.openSession()) {
            session.setDefaultReadOnly(true);
            List<Vehicle> vehicleList = session.createQuery("FROM Vehicle", Vehicle.class)
                    .getResultList();
            return vehicleList;
        } catch (Exception e) {
            throw new RuntimeException("Error Getting Vehicle", e);
        }
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Vehicle Id cannot be null");
        }
        try (Session session = sessionFactory.openSession()) {
            session.setDefaultReadOnly(true);
            // Vehicle vehicle = session.createQuery("FROM Vehicle v WHERE v.id = :id", Vehicle.class)
            //         .setParameter("id", id)
            //         .uniqueResult();
            Vehicle vehicle = session.get(Vehicle.class, id);
            return vehicle;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch vehicle ID: " + id, e);
        }
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Unimplemented method 'updateVehicle'");
    }

    @Override
    public void deleteVehicleById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Vehicle Id cannot be null");
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                // First delete children
                session.createQuery("DELETE FROM FuelData f WHERE f.vehicle.id = :id")
                        .setParameter("id", id)
                        .executeUpdate();

                session.createQuery("DELETE FROM EngineData e WHERE e.vehicle.id = :id")
                        .setParameter("id", id)
                        .executeUpdate();

                Vehicle vehicle = session.get(Vehicle.class, id);
                if (vehicle != null) {
                    session.delete(vehicle);
                }
                tx.commit();

            } catch (Exception e) {
                tx.rollback();
                throw new RuntimeException("Error deleting Vehicle", e);
            }
        }

    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {
        if (vehicle == null || vehicle.getid() == null) {
            throw new IllegalArgumentException("Vehicle or Vehicle ID cannot be null");
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Long id = vehicle.getid();
                // First delete children
                session.createQuery("DELETE FROM FuelData f WHERE f.vehicle.id = :id")
                        .setParameter("id", id)
                        .executeUpdate();

                session.createQuery("DELETE FROM EngineData e WHERE e.vehicle.id = :id")
                        .setParameter("id", id)
                        .executeUpdate();

                session.delete(vehicle);
                tx.commit();

            } catch (Exception e) {
                tx.rollback();
                throw new RuntimeException("Error deleting Vehicle", e);
            }
        }
    }

}
