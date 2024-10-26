package edu.northeastern.cs5200.ect.pojo;

public class User {
    private Long id;
    private String cName;
    private String cLocation;
    private String cType;
    private Double emissionQuota;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcLocation() {
        return cLocation;
    }

    public void setcLocation(String cLocation) {
        this.cLocation = cLocation;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public Double getEmissionQuota() {
        return emissionQuota;
    }

    public void setEmissionQuota(Double emissionQuota) {
        this.emissionQuota = emissionQuota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
