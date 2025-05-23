package com.REP.Example.Model;

public class Transaction {
    private int id;
    private String status;
    private String propertyId;
    private String buyerName;

    public Transaction() {}

    public Transaction(int id, String status, String propertyId, String buyerName) {
        this.id = id;
        this.status = status;
        this.propertyId = propertyId;
        this.buyerName = buyerName;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPropertyId() { return propertyId; }
    public void setPropertyId(String propertyId) { this.propertyId = propertyId; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }
}
