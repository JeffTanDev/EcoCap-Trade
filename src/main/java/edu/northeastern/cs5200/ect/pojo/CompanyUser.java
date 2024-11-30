package edu.northeastern.cs5200.ect.pojo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CompanyUser {
    private Integer userId;
    private String userName;
    private String password;
    private BigDecimal directEQuota;
    private BigDecimal indirectEEQuota;
    private BigDecimal indirectEQuota;
    private BigDecimal usedDE;
    private BigDecimal usedIEE;
    private BigDecimal usedIE;
    private String cName;
    private String cLocation;
    private String cRegistration;
    private String cType;
    private String linkMan;
    private String email;
}