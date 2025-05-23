package com.REP.Example.Service;

import com.REP.Example.Model.Transaction;
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
        int newId = ++currentId;
        Transaction transaction = new Transaction(newId, "Pending", propertyId, buyerName);
        transactionMap.put(newId, transaction);
        return transaction;
    }

    public Transaction updateTransactionStatus(int id, String status) {
        Transaction transaction = transactionMap.get(id);
        if (transaction != null) {
            transaction.setStatus(status);
        }
        return transaction;
    }
}
