package com.vehicleanalyzer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "engineData")
public class EngineData {

    @Id
    @GeneratedValue
    private int engineId;


}
