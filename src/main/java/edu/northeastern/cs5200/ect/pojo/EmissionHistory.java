package edu.northeastern.cs5200.ect.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class EmissionHistory {
    private Integer historyId;
    private Integer userId;
    private String eType;
    private Date date;
    private Double directEmi;
    private Double indirectEmi;
    private Double indirectEnerEmi;
} 