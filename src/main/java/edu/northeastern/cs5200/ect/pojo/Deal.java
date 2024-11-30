package edu.northeastern.cs5200.ect.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class Deal {
    private Integer dealId;
    private Integer transId;
    private Integer userId;
    private Date date;
    private Double amount;
    private Integer productId;
} 