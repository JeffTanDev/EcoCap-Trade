package edu.northeastern.cs5200.ect.service;

import edu.northeastern.cs5200.ect.pojo.Transaction;
import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionsByUserID(int userId);
} 