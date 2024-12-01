package edu.northeastern.cs5200.ect.service;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.pojo.DailyRelease;

public interface IEEQQuotaService {
    CompanyUser getQuota(String username);
    boolean sellQuota(String username, Double amount, Integer userId);

    DailyRelease getIndirectEnergyEmissionsProduct();
    DailyRelease getProductDetails(Integer productId);

    boolean purchaseQuota(String username, String product, Double quantity, Integer userId);

    boolean updateIndirectEnergyEmissionsQuota(String username, Double quantity);
} 