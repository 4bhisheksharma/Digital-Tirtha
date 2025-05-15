package org.example.digitaltirtha.dao;

import org.example.digitaltirtha.model.MediaContribution;

import java.util.List;

/**
 * DAO interface for MediaContribution entities.
 */
public interface MediaContributionDAO extends GenericDAO<MediaContribution, Long> {
    
    /**
     * Retrieves media contributions by user ID.
     *
     * @param userId The ID of the user
     * @return A list of media contributions by the specified user
     */
    List<MediaContribution> findByUserId(Long userId);
    
    /**
     * Retrieves media contributions by site ID.
     *
     * @param siteId The ID of the heritage site
     * @return A list of media contributions for the specified site
     */
    List<MediaContribution> findBySiteId(Long siteId);
    
    /**
     * Retrieves media contributions by status.
     *
     * @param status The status to filter by (e.g., "Pending", "Approved", "Rejected")
     * @return A list of media contributions with the specified status
     */
    List<MediaContribution> findByStatus(String status);
    
    /**
     * Retrieves media contributions by media type.
     *
     * @param mediaType The media type to filter by (e.g., "Photo", "Video")
     * @return A list of media contributions with the specified media type
     */
    List<MediaContribution> findByMediaType(String mediaType);
    
    /**
     * Approves a media contribution.
     *
     * @param id The ID of the media contribution to approve
     * @return The approved media contribution, or null if not found
     */
    MediaContribution approve(Long id);
    
    /**
     * Rejects a media contribution with a reason.
     *
     * @param id The ID of the media contribution to reject
     * @param reason The reason for rejection
     * @return The rejected media contribution, or null if not found
     */
    MediaContribution reject(Long id, String reason);
    
    /**
     * Records a view of a media contribution.
     *
     * @param id The ID of the media contribution
     * @return The updated media contribution, or null if not found
     */
    MediaContribution recordView(Long id);
    
    /**
     * Records a like for a media contribution.
     *
     * @param id The ID of the media contribution
     * @return The updated media contribution, or null if not found
     */
    MediaContribution recordLike(Long id);
    
    /**
     * Sets damage analysis for a media contribution.
     *
     * @param id The ID of the media contribution
     * @param damageLevel The level of damage detected
     * @param damageDescription The description of the damage
     * @return The updated media contribution, or null if not found
     */
    MediaContribution setDamageAnalysis(Long id, String damageLevel, String damageDescription);
}