package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.Transaction;
import edu.northeastern.cs5200.ect.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/api/user-transactions")
    public List<Transaction> getUserTransactions(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userID"); // 从session中获取userId
        if (userId == null) {
            throw new RuntimeException("User not logged in");
        }
        return transactionService.getTransactionsByUserID(userId);
    }
} 