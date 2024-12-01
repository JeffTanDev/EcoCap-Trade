package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.DailyRelease;
import edu.northeastern.cs5200.ect.pojo.Result;
import edu.northeastern.cs5200.ect.service.QuotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuotaController {
    private static final Logger logger = LoggerFactory.getLogger(QuotaController.class);
    
    @Autowired
    private QuotaService quotaService;

    @GetMapping("/deq-quota")
    public Result<?> getDEQQuota(HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            if (username == null) {
                return Result.error(401, "Not logged in");
            }
            
            DailyRelease quota = quotaService.getDEQQuota(username);
            if (quota != null) {
                // 计算剩余配额
                if (quota.getInitialAmount() != null && quota.getAvailableAmount() != null) {
                    double remaining = quota.getInitialAmount() - quota.getAvailableAmount();
                    quota.setAvailableAmount(remaining);
                }
                return Result.success(quota);
            }
            return Result.error(404, "No quota data found");
        } catch (Exception e) {
            logger.error("Error fetching DEQ quota: ", e);
            return Result.error(500, "Error fetching quota data");
        }
    }

    @PostMapping("/deq-sell")
    public Result<?> sellDEQ(@RequestBody Map<String, Double> request, HttpSession session) {
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
            
            boolean success = quotaService.sellDEQ(username, amount, userId);
            if (success) {
                // 获取更新后的配额信息
                DailyRelease updatedQuota = quotaService.getDEQQuota(username);
                return Result.success(updatedQuota);
            }
            return Result.error(400, "Failed to sell quota");
        } catch (Exception e) {
            logger.error("Error selling DEQ quota: ", e);
            return Result.error(500, "Error processing sell request");
        }
    }

    @GetMapping("/dailyRelease")
    public Result<?> getDirectEmissionsProduct() {
        try {
            DailyRelease product = quotaService.getDirectEmissionsProduct();
            logger.info("Product: {}", product);
            if (product != null) {
                return Result.success(product);
            }
            return Result.error(404, "No product data found");
        } catch (Exception e) {
            logger.error("Error fetching Direct Emissions product: ", e);
            return Result.error(500, "Error fetching product data");
        }
    }
} 