package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.AdminEmployee;
import edu.northeastern.cs5200.ect.pojo.AdminLoginRequest;
import edu.northeastern.cs5200.ect.pojo.Result;
import edu.northeastern.cs5200.ect.service.AdminLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AdminLoginController {
    private static final Logger logger = LoggerFactory.getLogger(AdminLoginController.class);
    
    @Autowired
    private AdminLoginService adminLoginService;

    @PostMapping("/admin-login")
    public Result<?> auth(@RequestBody AdminLoginRequest loginRequest, HttpSession session) {
        try {
            if (loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
                logger.warn("Login request missing username or password");
                return Result.error(400, "Username and password are required");
            }
            
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            
            logger.info("Attempting admin login for user: {}", username);
            
            AdminEmployee admin = adminLoginService.auth(username, password);
            
            if (admin != null) {
                session.setAttribute("adminUsername", username);
                session.setAttribute("adminId", admin.getAdminId());
                logger.info("Admin login successful. Username in session: {}", session.getAttribute("adminUsername"));
                return Result.success(admin);
            } else {
                logger.warn("Invalid username or password for user: {}", username);
                return Result.error(401, "Invalid username or password");
            }
        } catch (Exception e) {
            logger.error("Admin login error: ", e);
            return Result.error(500, "Internal server error: " + e.getMessage());
        }
    }

    @GetMapping("/current-admin")
    public Result<?> getCurrentAdmin(HttpSession session) {
        String username = (String) session.getAttribute("adminUsername");
        if (username != null) {
            logger.info("Current admin in session: {}", username);
            return Result.success(username);
        }
        return Result.error(401, "No admin logged in");
    }

    @PostMapping("/admin-logout")
    public Result<?> logout(HttpSession session) {
        try {
            String username = (String) session.getAttribute("adminUsername");
            logger.info("Admin logging out: {}", username);
            
            // 清除 session 中的管理员信息
            session.removeAttribute("adminUsername");
            session.removeAttribute("adminId");
            session.invalidate();
            
            return Result.success("Logged out successfully");
        } catch (Exception e) {
            logger.error("Admin logout error: ", e);
            return Result.error(500, "Logout failed: " + e.getMessage());
        }
    }
} 