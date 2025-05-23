package com.example.property.model;

public class Transaction {
    private int id;
    private String propertyId;
    private String buyerName;
    private String status;

    public Transaction() {    }

    public Transaction(int id, String propertyId, String buyerName, String status) {
        this.id = id;
        this.propertyId = propertyId;
        this.buyerName = buyerName;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPropertyId() { return propertyId; }
    public void setPropertyId(String propertyId) { this.propertyId = propertyId; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}






