package com.vehicleanalyzer.dao;

import java.util.List;
import static java.util.Objects.requireNonNull;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.vehicleanalyzer.model.Vehicle;
import com.vehicleanalyzer.model.enums.EngineType;
import com.vehicleanalyzer.model.enums.FuelType;
import com.vehicleanalyzer.model.enums.VehicleTypes;
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
                return vehicle.getId();
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

    private Vehicle SaveOrupdateVehicle(Vehicle vehicle) {
        requireNonNull(vehicle, "Vehicle cannot be null");

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Vehicle merged = (Vehicle) session.merge(vehicle);
                tx.commit();
                return merged;
            } catch (Exception e) {
                tx.rollback();
                throw new RuntimeException("Failed to update vehicle: " + vehicle.getId(), e);
            }
        }

    }

    @Override
    public Vehicle updateVehicle(Long id, String Manufacturer, String Model, int year_of_Mfg, VehicleTypes Type, EngineType Engine_Type, FuelType Fuel_Type) {
        requireNonNull(id, "Vehicle ID cannot be null");

        Vehicle existing = getVehicleById(id);
        if (existing == null) throw new EntityNotFoundException("Vehicle not found: " + id);

        if (Manufacturer != null) existing.setManufacturer(Manufacturer);
        if (Model != null) existing.setModel(Model);
        existing.setYear_of_Mfg(year_of_Mfg); // Primitive, can't be null
        if (Type != null) existing.setType(Type);
        if (Engine_Type != null) existing.setEngine_Type(Engine_Type);
        if (Fuel_Type != null) existing.setFuel_Type(Fuel_Type);

        return SaveOrupdateVehicle(existing);
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
        if (vehicle == null || vehicle.getId() == null) {
            throw new IllegalArgumentException("Vehicle or Vehicle ID cannot be null");
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Long id = vehicle.getId();
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
