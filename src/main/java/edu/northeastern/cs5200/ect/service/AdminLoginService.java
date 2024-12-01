package edu.northeastern.cs5200.ect.service;

import edu.northeastern.cs5200.ect.pojo.AdminEmployee;

public interface AdminLoginService {
    AdminEmployee auth(String username, String password);
} 