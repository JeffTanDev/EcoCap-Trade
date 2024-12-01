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

    @GetMapping("/iee-dailyRelease")
    public Result<?> getIndirectEnergyEmissionsProduct() {
        try {
            DailyRelease product = ieeqQuotaService.getIndirectEnergyEmissionsProduct();
            if (product != null) {
                return Result.success(product);
            }
            return Result.error(404, "No product data found");
        } catch (Exception e) {
            logger.error("Error fetching Indirect Energy Emissions product: ", e);
            return Result.error(500, "Error fetching product data");
        }
    }

    @GetMapping("/iee-productDetails/{productId}")
    public Result<?> getProductDetails(@PathVariable Integer productId) {
        try {
            DailyRelease product = ieeqQuotaService.getProductDetails(productId);
            if (product != null) {
                return Result.success(product);
            }
            return Result.error(404, "Product details not found");
        } catch (Exception e) {
            logger.error("Error fetching product details: ", e);
            return Result.error(500, "Error fetching product details");
        }
    }

    @PostMapping("/iee-purchase")
    public Result<?> purchaseQuota(@RequestBody Map<String, Object> request, HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            Integer userId = (Integer) session.getAttribute("userID");

            if (username == null || userId == null) {
                return Result.error(401, "Not logged in");
            }

            String product = (String) request.get("product");
            Double quantity = Double.valueOf(request.get("quantity").toString());
            if (quantity == null || quantity <= 0) {
                return Result.error(400, "Invalid quantity");
            }

            boolean success = ieeqQuotaService.purchaseQuota(username, product, quantity, userId);
            if (success) {
                return Result.success("Purchase successful");
            }
            return Result.error(400, "Failed to purchase quota");
        } catch (Exception e) {
            logger.error("Error purchasing quota: ", e);
            return Result.error(500, "Error processing purchase request");
        }
    }

    @PostMapping("/update-indirect-ee-quota")
    public Result<?> updateIndirectEEQuota(@RequestParam String username, @RequestParam Double quantity) {
        boolean success = ieeqQuotaService.updateIndirectEnergyEmissionsQuota(username, quantity);
        if (success) {
            return Result.success("Quota updated successfully");
        } else {
            return Result.error(400, "Failed to update quota");
        }
    }
} 