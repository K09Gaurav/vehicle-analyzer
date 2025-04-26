package com.vehicleanalyzer.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "engineData")
public class EngineData {

    @Id
    @GeneratedValue
    private Long engineId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private int RPM;
    private double  Temperature;

    private LocalDateTime timestamp;

    public EngineData(Vehicle vehicle, int rPM, float temperature, LocalDateTime timestamp) {
        this.vehicle = vehicle;
        RPM = rPM;
        Temperature = temperature;
        this.timestamp = timestamp;
    }

    public EngineData() {
    }

    public Long getEngineId() {
        return engineId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getRPM() {
        return RPM;
    }

    public void setRPM(int rPM) {
        RPM = rPM;
    }

    public double  getTemperature() {
        return Temperature;
    }

    public void setTemperature(float temperature) {
        Temperature = temperature;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "EngineData [engineId=" + engineId + ", vehicle=" + vehicle + ", RPM=" + RPM + ", Temperature="
                + Temperature + ", timestamp=" + timestamp + "]";
    }

    

}
