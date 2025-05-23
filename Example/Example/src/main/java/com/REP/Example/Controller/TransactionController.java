package com.REP.Example.Controller;

import com.REP.Example.Model.Transaction;
import com.REP.Example.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*") // Allow frontend access from any origin
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public List<Transaction> getAll() {
        return service.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable int id) {
        return service.getTransactionById(id);
    }

    @PostMapping("/book")
    public Transaction bookProperty(@RequestParam String propertyId, @RequestParam String buyerName) {
        return service.bookProperty(propertyId, buyerName);
    }

    @PutMapping("/update")
    public Transaction updateStatus(@RequestParam int id, @RequestParam String status) {
        return service.updateTransactionStatus(id, status);
    }
}
