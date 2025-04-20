package com.vehicleanalyzer.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EngineData {
    
    @Id
    private String engineId;
}
