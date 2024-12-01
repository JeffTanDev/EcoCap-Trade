package edu.northeastern.cs5200.ect.service;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.pojo.DailyRelease;

public interface IQQuotaService {
    CompanyUser getQuota(String username);
    boolean sellQuota(String username, Double amount, Integer userId);

    DailyRelease getProductDetails(Integer productId);

    DailyRelease getIndirectQuotaProduct();
    boolean purchaseQuota(String username, String product, Double quantity, Integer userId);

    boolean updateIndirectEmissionQuota(String username, Double quantity);
} 