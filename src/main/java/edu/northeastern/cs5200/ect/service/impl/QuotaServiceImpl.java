package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.pojo.DailyRelease;
import edu.northeastern.cs5200.ect.mapper.QuotaMapper;
import edu.northeastern.cs5200.ect.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class QuotaServiceImpl implements QuotaService {
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
} 