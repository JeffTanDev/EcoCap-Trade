package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.service.ProfileService;
import edu.northeastern.cs5200.ect.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class ProfileController {
    
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    
    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public Result<?> getProfile(HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            logger.info("Getting profile for username from session: " + username);
            
            if (username == null) {
                logger.error("Username not found in session");
                return Result.error(401, "Not logged in");
            }
            
            CompanyUser user = profileService.getProfileByUsername(username);
            logger.info("Found user: " + user);
            
            if (user != null) {
                return Result.success(user);
            }
            return Result.error(404, "User not found");
        } catch (Exception e) {
            logger.error("Error getting profile", e);
            return Result.error(500, "Server error: " + e.getMessage());
        }
    }
}