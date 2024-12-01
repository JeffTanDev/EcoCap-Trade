package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.pojo.DailyRelease;
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

    @GetMapping("/iq-productDetails/{productId}")
    public Result<?> getIQProductDetails(@PathVariable Integer productId) {
        try {
            DailyRelease product = iqQuotaService.getProductDetails(productId);
            if (product != null) {
                return Result.success(product);
            }
            return Result.error(404, "Product details not found");
        } catch (Exception e) {
            logger.error("Error fetching product details: ", e);
            return Result.error(500, "Error fetching product details");
        }
    }

    @GetMapping("/iq-dailyRelease")
    public Result<?> getIndirectQuotaProduct() {
        try {
            DailyRelease product = iqQuotaService.getIndirectQuotaProduct();
            if (product != null) {
                return Result.success(product);
            }
            return Result.error(404, "No product data found");
        } catch (Exception e) {
            logger.error("Error fetching Indirect Quota product: ", e);
            return Result.error(500, "Error fetching product data");
        }
    }

    @PostMapping("/iq-purchase")
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

            boolean success = iqQuotaService.purchaseQuota(username, product, quantity, userId);
            if (success) {
                return Result.success("Purchase successful");
            }
            return Result.error(400, "Failed to purchase quota");
        } catch (Exception e) {
            logger.error("Error purchasing quota: ", e);
            return Result.error(500, "Error processing purchase request");
        }
    }

    @PostMapping("/update-indirect-emission-quota")
    public Result<?> updateIndirectEmissionQuota(@RequestParam String username, @RequestParam Double quantity) {
        boolean success = iqQuotaService.updateIndirectEmissionQuota(username, quantity);
        if (success) {
            return Result.success("Quota updated successfully");
        } else {
            return Result.error(400, "Failed to update quota");
        }
    }
} 