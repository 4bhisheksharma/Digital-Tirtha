package org.example.digitaltirtha.dao;

import org.example.digitaltirtha.model.HeritageSite;

import java.util.List;

/**
 * DAO interface for HeritageSite entities.
 */
public interface HeritageSiteDAO extends GenericDAO<HeritageSite, Long> {
    
    /**
     * Retrieves heritage sites by risk level.
     *
     * @param riskLevel The risk level to filter by
     * @return A list of heritage sites with the specified risk level
     */
    List<HeritageSite> findByRiskLevel(String riskLevel);
    
    /**
     * Retrieves heritage sites by category.
     *
     * @param category The category to filter by
     * @return A list of heritage sites with the specified category
     */
    List<HeritageSite> findByCategory(String category);
    
    /**
     * Records a visit to a heritage site.
     *
     * @param siteId The ID of the heritage site
     * @return The updated heritage site, or null if not found
     */
    HeritageSite recordVisit(Long siteId);
    
    /**
     * Records a donation to a heritage site.
     *
     * @param siteId The ID of the heritage site
     * @param amount The donation amount
     * @return The updated heritage site, or null if not found
     */
    HeritageSite recordDonation(Long siteId, double amount);
}