package org.example.digitaltirtha.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a donation made by a user to a heritage site.
 */
public class Donation implements Serializable {
    
    private Long id;
    private Long userId;
    private Long siteId;
    private double amount;
    private String currency; // NPR, USD, etc.
    private Date donationDate;
    private String status; // Pending, Completed, Failed
    private String paymentMethod; // eSewa, Khalti, Credit Card, etc.
    private String transactionId;
    private String purpose; // General, Specific restoration project, etc.
    private boolean isAnonymous;
    private String message; // Optional message from donor
    
    // Constructors
    public Donation() {
    }
    
    public Donation(Long userId, Long siteId, double amount, String currency) {
        this.userId = userId;
        this.siteId = siteId;
        this.amount = amount;
        this.currency = currency;
        this.donationDate = new Date();
        this.status = "Pending";
        this.isAnonymous = false;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getSiteId() {
        return siteId;
    }
    
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public Date getDonationDate() {
        return donationDate;
    }
    
    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getPurpose() {
        return purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    public boolean isAnonymous() {
        return isAnonymous;
    }
    
    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    // Business methods
    public void complete(String transactionId) {
        this.status = "Completed";
        this.transactionId = transactionId;
    }
    
    public void fail(String reason) {
        this.status = "Failed";
        this.message = reason;
    }
    
    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", userId=" + userId +
                ", siteId=" + siteId +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", donationDate=" + donationDate +
                '}';
    }
}