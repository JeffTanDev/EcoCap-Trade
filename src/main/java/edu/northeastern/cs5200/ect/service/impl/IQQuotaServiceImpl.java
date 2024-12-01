package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
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
        // 更新配额
        boolean quotaUpdated = iqQuotaMapper.updateUsedQuota(username, amount);
        if (!quotaUpdated) {
            return false;
        }
        
        // 插入交易记录
        return iqQuotaMapper.insertTransaction(amount, userId, new Date());
    }
} 