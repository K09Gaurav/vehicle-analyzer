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

    private float liters_used;
    private float Distance_km;
    private LocalDateTime timestamp;

    public FuelData(Vehicle vehicle, float liters_used, float distance_km, LocalDateTime timestamp) {
        this.vehicle = vehicle;
        this.liters_used = liters_used;
        Distance_km = distance_km;
        this.timestamp = timestamp;
    }

    public FuelData() {
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public float getLiters_used() {
        return liters_used;
    }

    public void setLiters_used(float liters_used) {
        this.liters_used = liters_used;
    }

    public float getDistance_km() {
        return Distance_km;
    }

    public void setDistance_km(float distance_km) {
        Distance_km = distance_km;
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
        return "FuelData [fuelId=" + fuelId + ", vehicle=" + vehicle + ", liters_used=" + liters_used + ", Distance_km="
                + Distance_km + ", timestamp=" + timestamp + "]";
    }

       

    
}
