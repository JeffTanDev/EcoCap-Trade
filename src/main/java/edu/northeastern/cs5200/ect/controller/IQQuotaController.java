package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.pojo.Result;
import edu.northeastern.cs5200.ect.service.IQQuotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IQQuotaController {
    private static final Logger logger = LoggerFactory.getLogger(IQQuotaController.class);
    
    @Autowired
    private IQQuotaService iqQuotaService;

    @GetMapping("/iq-quota")
    public Result<?> getQuota(HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            if (username == null) {
                return Result.error(401, "Not logged in");
            }
            
            CompanyUser quota = iqQuotaService.getQuota(username);
            if (quota != null) {
                return Result.success(quota);
            }
            return Result.error(404, "No quota data found");
        } catch (Exception e) {
            logger.error("Error fetching IQ quota: ", e);
            return Result.error(500, "Error fetching quota data");
        }
    }

    @PostMapping("/iq-sell")
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
            
            boolean success = iqQuotaService.sellQuota(username, amount, userId);
            if (success) {
                CompanyUser updatedQuota = iqQuotaService.getQuota(username);
                return Result.success(updatedQuota);
            }
            return Result.error(400, "Failed to sell quota");
        } catch (Exception e) {
            logger.error("Error selling IQ quota: ", e);
            return Result.error(500, "Error processing sell request");
        }
    }
} 