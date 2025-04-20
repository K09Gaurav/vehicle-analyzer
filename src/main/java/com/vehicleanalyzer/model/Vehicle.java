package com.vehicleanalyzer.model;

import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    private static final Logger LOGVehicles = Logger.getLogger(Vehicle.class.getName());

    public static Logger getLOGVehicles() {
        return LOGVehicles;
    }
    
    @Id
    @GeneratedValue
    private String ID;
    
    private String Manufacturer;
    private String Model;
    private String Year;

    @Enumerated(EnumType.STRING)
    @Column(name = "VehicleType")
    private VehicleTypes Type ;

    

    public Vehicle(String manufacturer, String model, String year, VehicleTypes type) {
        Manufacturer = manufacturer;
        Model = model;
        Year = year;
        Type = type;
    }

    public Vehicle() {
    }

    public String getID() {
        return ID;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public VehicleTypes getType() {
        return Type;
    }

    public void setType(VehicleTypes type) {
        Type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehicle{");
        sb.append("ID=").append(ID);
        sb.append(", Manufacturer=").append(Manufacturer);
        sb.append(", Model=").append(Model);
        sb.append(", Year=").append(Year);
        sb.append(", Type=").append(Type);
        sb.append('}');
        return sb.toString();
    }

    
    


}
