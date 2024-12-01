package edu.northeastern.cs5200.ect.service;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;

public interface IQQuotaService {
    CompanyUser getQuota(String username);
    boolean sellQuota(String username, Double amount, Integer userId);
} 