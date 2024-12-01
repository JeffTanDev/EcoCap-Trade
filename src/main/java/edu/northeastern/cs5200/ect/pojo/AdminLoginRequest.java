package edu.northeastern.cs5200.ect.pojo;

import lombok.Data;

@Data
public class AdminLoginRequest {
    private String username;
    private String password;
} 