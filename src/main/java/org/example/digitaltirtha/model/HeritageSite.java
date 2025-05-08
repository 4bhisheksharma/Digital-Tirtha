package org.example.digitaltirtha.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a heritage site in Nepal that is at risk and needs preservation.
 */
public class HeritageSite implements Serializable {
    
    private Long id;
    private String name;
    private String description;
    private String location;
    private double latitude;
    private double longitude;
    private String riskLevel; // Low, Medium, High, Critical
    private String category; // Temple, Monastery, UNESCO site, etc.
    private Date createdDate;
    private Date lastUpdated;
    private String imageUrl;
    private String virtualTourUrl;
    private int visitCount;
    private double donationAmount;
    
    // Constructors
    public HeritageSite() {
    }
    
    public HeritageSite(Long id, String name, String description, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.createdDate = new Date();
        this.lastUpdated = new Date();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public String getRiskLevel() {
        return riskLevel;
    }
    
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public Date getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getVirtualTourUrl() {
        return virtualTourUrl;
    }
    
    public void setVirtualTourUrl(String virtualTourUrl) {
        this.virtualTourUrl = virtualTourUrl;
    }
    
    public int getVisitCount() {
        return visitCount;
    }
    
    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }
    
    public double getDonationAmount() {
        return donationAmount;
    }
    
    public void setDonationAmount(double donationAmount) {
        this.donationAmount = donationAmount;
    }
    
    public void incrementVisitCount() {
        this.visitCount++;
    }
    
    public void addDonation(double amount) {
        this.donationAmount += amount;
    }
    
    @Override
    public String toString() {
        return "HeritageSite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", riskLevel='" + riskLevel + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}