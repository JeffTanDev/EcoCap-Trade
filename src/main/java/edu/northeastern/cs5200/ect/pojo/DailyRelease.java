package edu.northeastern.cs5200.ect.pojo;

import lombok.Data;

@Data
public class DailyRelease {
    private Integer productId;
    private String productName;
    private Double initialAmount;
    private Double availableAmount;
} 