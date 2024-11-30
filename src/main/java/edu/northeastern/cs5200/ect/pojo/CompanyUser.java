package edu.northeastern.cs5200.ect.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyUser {
    private Integer userId;
    private String userName;
    private String password;
    private Double directEQuota;
    private Double indirectEeQuota;
    private Double indirectEQuota;
    private Double usedDe;
    private Double usedIee;
    private Double usedIe;
    private String cName;
    private String cLocation;
    private String cRegistration;
    private String cType;
    private String linkMan;
    private String email;
}
