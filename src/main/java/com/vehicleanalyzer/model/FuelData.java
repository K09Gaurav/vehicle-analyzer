package com.vehicleanalyzer.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fuelData")
public class FuelData {
    
    @Id
    @GeneratedValue
    private Long fuelId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private double liters_used;
    private double distance_km;
    private LocalDateTime timestamp;

    public FuelData(Vehicle vehicle, float liters_used, float distance_km, LocalDateTime timestamp) {
        this.vehicle = vehicle;
        this.liters_used = liters_used;
        this.distance_km = distance_km;
        this.timestamp = timestamp;
    }

    public FuelData() {
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getLiters_used() {
        return liters_used;
    }

    public void setLiters_used(float liters_used) {
        this.liters_used = liters_used;
    }

    public double getdistance_km() {
        return distance_km;
    }

    public void setdistance_km(float distance_km) {
        this.distance_km = distance_km;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getFuelId() {
        return fuelId;
    }

    @Override
    public String toString() {
        return "FuelData [fuelId=" + fuelId + ", vehicle=" + vehicle + ", liters_used=" + liters_used + ", distance_km="
                + distance_km + ", timestamp=" + timestamp + "]";
    }

       

    
}
