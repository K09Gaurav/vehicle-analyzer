package com.vehicleanalyzer.util;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.vehicleanalyzer.model.EngineData;
import com.vehicleanalyzer.model.FuelData;
import com.vehicleanalyzer.model.Vehicle;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){

        if (sessionFactory == null){
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml").addAnnotatedClass(Vehicle.class)
                        .addAnnotatedClass(EngineData.class)
                        .addAnnotatedClass(FuelData.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);



            } catch (HibernateException e) {
                System.err.println("SessionFactory creation failed: " + e.getMessage());
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
    
    // public static void main(String[] args) {
    //     // Test the HibernateUtil class
    //     SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    //     if (sessionFactory != null) {
    //         System.out.println("SessionFactory created successfully!");
    //     } else {
    //         System.out.println("Failed to create SessionFactory.");
    //     }
    // }
}
