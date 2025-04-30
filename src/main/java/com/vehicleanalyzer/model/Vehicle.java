package com.vehicleanalyzer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vehicleanalyzer.model.enums.EngineType;
import com.vehicleanalyzer.model.enums.FuelType;
import com.vehicleanalyzer.model.enums.VehicleTypes;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    private String Manufacturer;
    private String Model;
    private int year_of_Mfg;

    @Enumerated(EnumType.STRING)
    @Column(name = "Vehicle_Type")
    private VehicleTypes Type;

    @Enumerated(EnumType.STRING)
    @Column(name = "Engine_Type")
    private EngineType Engine_Type;

    @Enumerated(EnumType.STRING)
    @Column(name = "Fuel_Type")
    private FuelType Fuel_Type;

    public Vehicle(String manufacturer, String model, int year_of_Mfg, VehicleTypes type, EngineType engine_Type,
            FuelType fuel_Type) {
        Manufacturer = manufacturer;
        Model = model;
        this.year_of_Mfg = year_of_Mfg;
        Type = type;
        Engine_Type = engine_Type;
        Fuel_Type = fuel_Type;
    }

    public Vehicle() {
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

    public int getYear_of_Mfg() {
        return year_of_Mfg;
    }

    public void setYear_of_Mfg(int year_of_Mfg) {
        this.year_of_Mfg = year_of_Mfg;
    }

    public VehicleTypes getType() {
        return Type;
    }

    public void setType(VehicleTypes type) {
        Type = type;
    }

    public EngineType getEngine_Type() {
        return Engine_Type;
    }

    public void setEngine_Type(EngineType engine_Type) {
        Engine_Type = engine_Type;
    }

    public FuelType getFuel_Type() {
        return Fuel_Type;
    }

    public void setFuel_Type(FuelType fuel_Type) {
        Fuel_Type = fuel_Type;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehicle{");
        sb.append("id=").append(id);
        sb.append(", Manufacturer=").append(Manufacturer);
        sb.append(", Model=").append(Model);
        sb.append(", year_of_Mfg=").append(year_of_Mfg);
        sb.append(", Type=").append(Type);
        sb.append(", Engine_Type=").append(Engine_Type);
        sb.append(", Fuel_Type=").append(Fuel_Type);
        sb.append('}');
        return sb.toString();
    }

    public void setId(Long id) {
        this.id = id;
    }

}
