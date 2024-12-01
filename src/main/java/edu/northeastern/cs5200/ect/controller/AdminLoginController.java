package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.AdminEmployee;
import edu.northeastern.cs5200.ect.pojo.AdminLoginRequest;
import edu.northeastern.cs5200.ect.pojo.Result;
import edu.northeastern.cs5200.ect.service.AdminLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminLoginController {
    private static final Logger logger = LoggerFactory.getLogger(AdminLoginController.class);
    
    @Autowired
    private AdminLoginService adminLoginService;

    @PostMapping("/api/admin-login")
    public Result<?> auth(@RequestBody AdminLoginRequest loginRequest) {
        try {
            if (loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
                return Result.error(400, "Username and password are required");
            }
            
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            
            logger.info("Attempting admin login for user: {}", username);
            
            AdminEmployee admin = adminLoginService.auth(username, password);
            
            if (admin != null) {
                return Result.success(admin);
            } else {
                return Result.error(401, "Invalid username or password");
            }
        } catch (Exception e) {
            logger.error("Admin login error: ", e);
            return Result.error(500, "Internal server error: " + e.getMessage());
        }
    }
} 