package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.mapper.AdminLoginMapping;
import edu.northeastern.cs5200.ect.pojo.AdminEmployee;
import edu.northeastern.cs5200.ect.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    @Autowired
    private AdminLoginMapping adminLoginMapping;

    @Override
    public AdminEmployee auth(String username, String password) {
        return adminLoginMapping.auth(username, password);
    }
} 