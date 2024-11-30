package edu.northeastern.cs5200.ect.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class Transaction {
    private Integer transId;
    private Double price;
    private String transStatus;
    private String paymentMethod;
    private Integer userId;
    private Date date;
    private Integer ticketIdDuo;
    private String eType;
} 