package edu.northeastern.cs5200.ect.pojo;

import lombok.Data;

@Data
public class AdminEmployee {
    private Integer adminId;
    private String officeHour;
    private String aAccount;
    private String aPw;
    private String aRbac;
    private Integer departmentIdDuo;
    private Integer employeeIdDuo;
} 