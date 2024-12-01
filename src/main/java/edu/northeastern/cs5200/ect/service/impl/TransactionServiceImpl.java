package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.mapper.TransactionMapper;
import edu.northeastern.cs5200.ect.pojo.Transaction;
import edu.northeastern.cs5200.ect.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public List<Transaction> getTransactionsByUserID(int userId) {
        return transactionMapper.findTransactionsByUserId(userId);
    }
} 