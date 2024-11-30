package edu.northeastern.cs5200.ect.pojo;

import lombok.Data;

@Data
public class IndirectEnergyEmission {
    private Integer userId;
    private Double usedEmission;
    private Double remainEmissions;
    private String eType;
} 