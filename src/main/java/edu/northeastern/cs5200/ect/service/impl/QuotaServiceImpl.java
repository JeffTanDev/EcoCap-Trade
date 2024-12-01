package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.pojo.DailyRelease;
import edu.northeastern.cs5200.ect.mapper.QuotaMapper;
import edu.northeastern.cs5200.ect.service.QuotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class QuotaServiceImpl implements QuotaService {
    private static final Logger logger = LoggerFactory.getLogger(QuotaServiceImpl.class);

    @Autowired
    private QuotaMapper quotaMapper;

    @Override
    public DailyRelease getDEQQuota(String username) {
        return quotaMapper.getDEQQuota(username);
    }

    @Override
    @Transactional
    public boolean sellDEQ(String username, Double amount, Integer userId) {
        // 更新配额
        boolean quotaUpdated = quotaMapper.updateUsedDEQ(username, amount);
        if (!quotaUpdated) {
            return false;
        }
        
        // 插入交易记录
        return quotaMapper.insertTransaction(amount, userId, new Date());
    }

    @Override
    public DailyRelease getDirectEmissionsProduct() {
        return quotaMapper.getDirectEmissionsProduct();
    }

    @Override
    @Transactional
    public boolean purchaseQuota(String username, Double quantity, Integer userId) {
        try {
            // 更新用户的Direct_E_Quota
            boolean quotaUpdated = quotaMapper.updateDirectEQuota(username, quantity);
            if (!quotaUpdated) {
                logger.error("Failed to update Direct_E_Quota for user: {}", username);
                return false;
            }

            // 更新Daily_Release表中的Available_Amount
            boolean dailyReleaseUpdated = quotaMapper.updateAvailableAmount(quantity);
            if (!dailyReleaseUpdated) {
                logger.error("Failed to update Available_Amount in Daily_Release");
                return false;
            }

            // 插入交易记录
            boolean transactionInserted = quotaMapper.insertTransaction(quantity, userId, new Date());
            if (!transactionInserted) {
                logger.error("Failed to insert transaction for user: {}", username);
                return false;
            }

            return true;
        } catch (Exception e) {
            logger.error("Error during purchaseQuota: ", e);
            return false;
        }
    }
} 