package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.pojo.DailyRelease;
import edu.northeastern.cs5200.ect.mapper.IQQuotaMapper;
import edu.northeastern.cs5200.ect.service.IQQuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class IQQuotaServiceImpl implements IQQuotaService {
    @Autowired
    private IQQuotaMapper iqQuotaMapper;

    @Override
    public CompanyUser getQuota(String username) {
        return iqQuotaMapper.getCompanyUser(username);
    }

    @Override
    @Transactional
    public boolean sellQuota(String username, Double amount, Integer userId) {
        boolean quotaUpdated = iqQuotaMapper.updateUsedQuota(username, amount);
        if (!quotaUpdated) {
            return false;
        }
        return iqQuotaMapper.insertTransaction(amount, userId, new Date());
    }

    @Override
    public DailyRelease getProductDetails(Integer productId) {
        return iqQuotaMapper.getProductDetails(productId);
    }

    @Override
    public DailyRelease getIndirectQuotaProduct() {
        return iqQuotaMapper.getIndirectQuotaProduct();
    }

    @Override
    @Transactional
    public boolean purchaseQuota(String username, String product, Double quantity, Integer userId) {
        boolean quotaUpdated = iqQuotaMapper.updateQuotaForPurchase(username, product, quantity);
        if (!quotaUpdated) {
            return false;
        }

        boolean indirectQuotaUpdated = iqQuotaMapper.updateIndirectEmissionQuota(username, quantity);
        if (!indirectQuotaUpdated) {
            return false;
        }

        return iqQuotaMapper.insertTransaction(quantity, userId, new Date());
    }
} 