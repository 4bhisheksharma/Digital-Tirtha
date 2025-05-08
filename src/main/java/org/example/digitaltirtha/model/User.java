package org.example.digitaltirtha.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a user of the Digital-Tirtha platform.
 */
public class User implements Serializable {
    
    private Long id;
    private String username;
    private String password; // Would be stored encrypted in a real application
    private String email;
    private String fullName;
    private String country;
    private boolean isLocal; // Whether the user is from Nepal or diaspora/tourist
    private int karmaPoints;
    private Date registrationDate;
    private Date lastLoginDate;
    private int sitesVisited;
    private int mediaContributed;
    private double totalDonations;
    
    // Constructors
    public User() {
    }
    
    public User(Long id, String username, String email, String fullName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.registrationDate = new Date();
        this.karmaPoints = 0;
        this.sitesVisited = 0;
        this.mediaContributed = 0;
        this.totalDonations = 0.0;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public boolean isLocal() {
        return isLocal;
    }
    
    public void setLocal(boolean local) {
        isLocal = local;
    }
    
    public int getKarmaPoints() {
        return karmaPoints;
    }
    
    public void setKarmaPoints(int karmaPoints) {
        this.karmaPoints = karmaPoints;
    }
    
    public Date getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public Date getLastLoginDate() {
        return lastLoginDate;
    }
    
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    
    public int getSitesVisited() {
        return sitesVisited;
    }
    
    public void setSitesVisited(int sitesVisited) {
        this.sitesVisited = sitesVisited;
    }
    
    public int getMediaContributed() {
        return mediaContributed;
    }
    
    public void setMediaContributed(int mediaContributed) {
        this.mediaContributed = mediaContributed;
    }
    
    public double getTotalDonations() {
        return totalDonations;
    }
    
    public void setTotalDonations(double totalDonations) {
        this.totalDonations = totalDonations;
    }
    
    // Business methods
    public void addKarmaPoints(int points) {
        this.karmaPoints += points;
    }
    
    public void incrementSitesVisited() {
        this.sitesVisited++;
        // Add karma points for visiting a site
        addKarmaPoints(5);
    }
    
    public void incrementMediaContributed() {
        this.mediaContributed++;
        // Add karma points for contributing media
        addKarmaPoints(10);
    }
    
    public void addDonation(double amount) {
        this.totalDonations += amount;
        // Add karma points based on donation amount (1 point per $1)
        addKarmaPoints((int) amount);
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", karmaPoints=" + karmaPoints +
                ", sitesVisited=" + sitesVisited +
                '}';
    }
}