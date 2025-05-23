package com.example.property.controller;

import com.example.property.model.Transaction;
import com.example.property.service.TransactionService;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*") // For frontend access
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return service.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable int id) {
        return service.getTransactionById(id);
    }

    @PostMapping("/book")
    public Transaction book(@RequestParam String propertyId, @RequestParam String buyerName) {
        return service.bookProperty(propertyId, buyerName);
    }

    @PostMapping("/update")
    public Transaction update(@RequestParam int id, @RequestParam String status) {
        return service.updateStatus(id, status);
    }
}
