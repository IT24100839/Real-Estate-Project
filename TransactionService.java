package com.example.property.service;

import com.example.property.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {
    private final Map<Integer, Transaction> transactionMap = new HashMap<>();
    private int currentId = 100;

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactionMap.values());
    }

    public Transaction getTransactionById(int id) {
        return transactionMap.get(id);
    }

    public Transaction bookProperty(String propertyId, String buyerName) {
        currentId++;
        Transaction transaction = new Transaction(currentId, propertyId, buyerName, "Pending");
        transactionMap.put(currentId, transaction);
        return transaction;
    }

    public Transaction updateStatus(int id, String status) {
        Transaction transaction = transactionMap.get(id);
        if (transaction != null) {
            transaction.setStatus(status);
        }
        return transaction;
    }
}
