package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.pojo.LoginRequest;
import edu.northeastern.cs5200.ect.pojo.Result;
import edu.northeastern.cs5200.ect.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private LoginService loginService;

    @PostMapping("/api/login")
    public Result<?> Auth(@RequestBody LoginRequest loginRequest) {
        try {
            if (loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
                return Result.error(400, "Username and password are required");
            }
            
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            
            logger.info("Attempting login for user: {}", username);
            
            CompanyUser user = loginService.auth(username, password);
            
            if (user != null) {
                return Result.success(user);  
            } else {
                return Result.error(401, "Invalid username or password");
            }
        } catch (Exception e) {
            logger.error("Login error: ", e);
            return Result.error(500, "Internal server error: " + e.getMessage());
        }
    }
}
