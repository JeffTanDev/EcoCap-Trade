package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
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
        // 更新配额
        boolean quotaUpdated = ieeqQuotaMapper.updateUsedQuota(username, amount);
        if (!quotaUpdated) {
            return false;
        }
        
        // 插入交易记录
        return ieeqQuotaMapper.insertTransaction(amount, userId, new Date());
    }
} 