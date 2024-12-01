package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.pojo.DailyRelease;
import edu.northeastern.cs5200.ect.mapper.IEEQQuotaMapper;
import edu.northeastern.cs5200.ect.service.IEEQQuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class IEEQQuotaServiceImpl implements IEEQQuotaService {
    @Autowired
    private IEEQQuotaMapper ieeqQuotaMapper;

    @Override
    public CompanyUser getQuota(String username) {
        return ieeqQuotaMapper.getCompanyUser(username);
    }

    @Override
    @Transactional
    public boolean sellQuota(String username, Double amount, Integer userId) {
        boolean quotaUpdated = ieeqQuotaMapper.updateUsedQuota(username, amount);
        if (!quotaUpdated) {
            return false;
        }
        return ieeqQuotaMapper.insertTransaction(amount, userId, new Date());
    }

    @Override
    public DailyRelease getIndirectEnergyEmissionsProduct() {
        return ieeqQuotaMapper.getIndirectEnergyEmissionsProduct();
    }

    @Override
    public DailyRelease getProductDetails(Integer productId) {
        return ieeqQuotaMapper.getProductDetails(productId);
    }

    @Override
    @Transactional
    public boolean purchaseQuota(String username, String product, Double quantity, Integer userId) {
        boolean quotaUpdated = ieeqQuotaMapper.updateQuotaForPurchase(username, product, quantity);
        if (!quotaUpdated) {
            return false;
        }
        return ieeqQuotaMapper.insertTransaction(quantity, userId, new Date());
    }

    @Override
    @Transactional
    public boolean updateIndirectEnergyEmissionsQuota(String username, Double quantity) {
        return ieeqQuotaMapper.updateIndirectEnergyEmissionsQuota(username, quantity);
    }
} 