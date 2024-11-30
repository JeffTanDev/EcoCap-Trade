package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.pojo.Result;
import edu.northeastern.cs5200.ect.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    @GetMapping("/company")
    public Result<?> getInfo(@RequestParam("name") String name) {
        CompanyUser user = userService.get(name);
        logger.info("Searching for company with name: {}", name);
        logger.info("Found user: {}", user);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error(404, "Company not found");
        }
    }
}
