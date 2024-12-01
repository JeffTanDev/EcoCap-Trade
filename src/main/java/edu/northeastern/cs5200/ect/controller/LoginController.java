package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.pojo.LoginRequest;
import edu.northeastern.cs5200.ect.pojo.Result;
import edu.northeastern.cs5200.ect.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private LoginService loginService;

    @PostMapping("/api/login")
    public Result<?> Auth(@RequestBody LoginRequest loginRequest, HttpSession session) {
        try {
            if (loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
                return Result.error(400, "Username and password are required");
            }
            
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            
            logger.info("Attempting login for user: {}", username);
            
            CompanyUser user = loginService.auth(username, password);
            
            if (user != null) {
                session.setAttribute("username", username);
                session.setAttribute("userID", user.getUserId());
                System.out.println("Session username set to: " + username);
                System.out.println("Session userID set to: " + user.getUserId());
                System.out.println("Login successful. Username in session: " + session.getAttribute("username"));
                return Result.success(user);
            } else {
                return Result.error(401, "Invalid username or password");
            }
        } catch (Exception e) {
            logger.error("Login error: ", e);
            return Result.error(500, "Internal server error: " + e.getMessage());
        }
    }

    @GetMapping("/api/current-user")
    public Result<?> getCurrentUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return Result.success(username);
        }
        return Result.error(401, "No user logged in");
    }

    @PostMapping("/api/logout")
    public Result<?> logout(HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            logger.info("Logging out user: {}", username);
            
            session.removeAttribute("username");
            session.invalidate();  // 清除整个session
            
            return Result.success("Logged out successfully");
        } catch (Exception e) {
            logger.error("Logout error: ", e);
            return Result.error(500, "Logout failed: " + e.getMessage());
        }
    }
}
