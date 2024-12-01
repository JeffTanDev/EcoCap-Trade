package edu.northeastern.cs5200.ect.service;

import edu.northeastern.cs5200.ect.pojo.DailyRelease;

public interface QuotaService {
    DailyRelease getDEQQuota(String username);
    boolean sellDEQ(String username, Double amount, Integer userId);
} 