package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.pojo.DailyRelease;
import edu.northeastern.cs5200.ect.pojo.Result;
import edu.northeastern.cs5200.ect.service.IEEQQuotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IEEQQuotaController {
    private static final Logger logger = LoggerFactory.getLogger(IEEQQuotaController.class);
    
    @Autowired
    private IEEQQuotaService ieeqQuotaService;

    @GetMapping("/iee-quota")
    public Result<?> getQuota(HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            if (username == null) {
                return Result.error(401, "Not logged in");
            }
            
            CompanyUser quota = ieeqQuotaService.getQuota(username);
            if (quota != null) {
                return Result.success(quota);
            }
            return Result.error(404, "No quota data found");
        } catch (Exception e) {
            logger.error("Error fetching IEEQ quota: ", e);
            return Result.error(500, "Error fetching quota data");
        }
    }

    @PostMapping("/iee-sell")
    public Result<?> sellQuota(@RequestBody Map<String, Double> request, HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            Integer userId = (Integer) session.getAttribute("userID");
            
            if (username == null || userId == null) {
                return Result.error(401, "Not logged in");
            }
            
            Double amount = request.get("amount");
            if (amount == null) {
                return Result.error(400, "Amount is required");
            }
            
            boolean success = ieeqQuotaService.sellQuota(username, amount, userId);
            if (success) {
                CompanyUser updatedQuota = ieeqQuotaService.getQuota(username);
                return Result.success(updatedQuota);
            }
            return Result.error(400, "Failed to sell quota");
        } catch (Exception e) {
            logger.error("Error selling IEEQ quota: ", e);
            return Result.error(500, "Error processing sell request");
        }
    }
} 