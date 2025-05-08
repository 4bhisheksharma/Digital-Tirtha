package org.example.digitaltirtha.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a media contribution (photo, video) uploaded by a user for a heritage site.
 */
public class MediaContribution implements Serializable {
    
    private Long id;
    private Long userId;
    private Long siteId;
    private String mediaType; // Photo, Video, 3D Scan, etc.
    private String fileUrl;
    private String thumbnailUrl;
    private String title;
    private String description;
    private Date uploadDate;
    private Date captureDate; // When the photo/video was taken
    private String status; // Pending, Approved, Rejected
    private String location; // Location where the media was captured
    private double latitude;
    private double longitude;
    private boolean hasDamageDetection; // Whether AI has analyzed this for damage
    private String damageLevel; // None, Low, Medium, High
    private String damageDescription;
    private int viewCount;
    private int likeCount;
    
    // Constructors
    public MediaContribution() {
    }
    
    public MediaContribution(Long userId, Long siteId, String mediaType, String fileUrl) {
        this.userId = userId;
        this.siteId = siteId;
        this.mediaType = mediaType;
        this.fileUrl = fileUrl;
        this.uploadDate = new Date();
        this.status = "Pending";
        this.hasDamageDetection = false;
        this.viewCount = 0;
        this.likeCount = 0;
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
    
    public String getMediaType() {
        return mediaType;
    }
    
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    
    public String getFileUrl() {
        return fileUrl;
    }
    
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getUploadDate() {
        return uploadDate;
    }
    
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    
    public Date getCaptureDate() {
        return captureDate;
    }
    
    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
    
    public boolean isHasDamageDetection() {
        return hasDamageDetection;
    }
    
    public void setHasDamageDetection(boolean hasDamageDetection) {
        this.hasDamageDetection = hasDamageDetection;
    }
    
    public String getDamageLevel() {
        return damageLevel;
    }
    
    public void setDamageLevel(String damageLevel) {
        this.damageLevel = damageLevel;
    }
    
    public String getDamageDescription() {
        return damageDescription;
    }
    
    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }
    
    public int getViewCount() {
        return viewCount;
    }
    
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    
    public int getLikeCount() {
        return likeCount;
    }
    
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
    
    // Business methods
    public void approve() {
        this.status = "Approved";
    }
    
    public void reject(String reason) {
        this.status = "Rejected";
        this.description = reason;
    }
    
    public void incrementViewCount() {
        this.viewCount++;
    }
    
    public void incrementLikeCount() {
        this.likeCount++;
    }
    
    public void setDamageAnalysis(String damageLevel, String damageDescription) {
        this.hasDamageDetection = true;
        this.damageLevel = damageLevel;
        this.damageDescription = damageDescription;
    }
    
    @Override
    public String toString() {
        return "MediaContribution{" +
                "id=" + id +
                ", userId=" + userId +
                ", siteId=" + siteId +
                ", mediaType='" + mediaType + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", damageLevel='" + damageLevel + '\'' +
                '}';
    }
}