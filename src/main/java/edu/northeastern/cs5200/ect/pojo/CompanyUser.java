package edu.northeastern.cs5200.ect.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
public class CompanyUser {
    private Integer userId;
    private String userName;
    private String password;
    private Double directEQuota;
    private Double indirectEeQuota;
    private Double indirectEQuota;
    private String companyName;
    private String companyLocation;
    private String companyRegistration;
    private String companyType;
    private String linkMan;
    private String email;
}
