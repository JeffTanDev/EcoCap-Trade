package edu.northeastern.cs5200.ect.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class Ticket {
    private Integer ticketId;
    private Date ticketStart;
    private Date ticketClose;
    private String ticketType;
    private String resolve;
    private Integer adminIdDuo;
    private Date assistDate;
    private Integer userId;
    private Integer transId;
} 