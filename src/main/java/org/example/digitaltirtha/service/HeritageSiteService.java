package org.example.digitaltirtha.service;

import org.example.digitaltirtha.dao.HeritageSiteDAO;
import org.example.digitaltirtha.dao.impl.HeritageSiteDAOImpl;
import org.example.digitaltirtha.model.HeritageSite;

import java.util.List;

/**
 * Service class for managing heritage sites.
 * Implements business logic and delegates data access to DAO.
 */
public class HeritageSiteService {

    private HeritageSiteDAO heritageSiteDAO;

    /**
     * Constructor that initializes the DAO.
     */
    public HeritageSiteService() {
        this.heritageSiteDAO = new HeritageSiteDAOImpl();
    }

    /**
     * Constructor with DAO injection for testing.
     * 
     * @param heritageSiteDAO The DAO to use
     */
    public HeritageSiteService(HeritageSiteDAO heritageSiteDAO) {
        this.heritageSiteDAO = heritageSiteDAO;
    }

    /**
     * Creates a new heritage site.
     *
     * @param site The heritage site to create
     * @return The created heritage site with ID assigned
     */
    public HeritageSite createSite(HeritageSite site) {
        return heritageSiteDAO.create(site);
    }

    /**
     * Retrieves a heritage site by ID.
     *
     * @param id The ID of the heritage site to retrieve
     * @return The heritage site, or null if not found
     */
    public HeritageSite getSiteById(Long id) {
        return heritageSiteDAO.findById(id);
    }

    /**
     * Updates an existing heritage site.
     *
     * @param site The heritage site to update
     * @return The updated heritage site, or null if not found
     */
    public HeritageSite updateSite(HeritageSite site) {
        return heritageSiteDAO.update(site);
    }

    /**
     * Deletes a heritage site by ID.
     *
     * @param id The ID of the heritage site to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteSite(Long id) {
        return heritageSiteDAO.delete(id);
    }

    /**
     * Retrieves all heritage sites.
     *
     * @return A list of all heritage sites
     */
    public List<HeritageSite> getAllSites() {
        return heritageSiteDAO.findAll();
    }

    /**
     * Retrieves heritage sites by risk level.
     *
     * @param riskLevel The risk level to filter by
     * @return A list of heritage sites with the specified risk level
     */
    public List<HeritageSite> getSitesByRiskLevel(String riskLevel) {
        return heritageSiteDAO.findByRiskLevel(riskLevel);
    }

    /**
     * Retrieves heritage sites by category.
     *
     * @param category The category to filter by
     * @return A list of heritage sites with the specified category
     */
    public List<HeritageSite> getSitesByCategory(String category) {
        return heritageSiteDAO.findByCategory(category);
    }

    /**
     * Records a visit to a heritage site.
     *
     * @param siteId The ID of the heritage site
     * @return The updated heritage site, or null if not found
     */
    public HeritageSite recordVisit(Long siteId) {
        return heritageSiteDAO.recordVisit(siteId);
    }

    /**
     * Records a donation to a heritage site.
     *
     * @param siteId The ID of the heritage site
     * @param amount The donation amount
     * @return The updated heritage site, or null if not found
     */
    public HeritageSite recordDonation(Long siteId, double amount) {
        return heritageSiteDAO.recordDonation(siteId, amount);
    }

    /**
     * Initializes the service with sample data.
     */
    public void initWithSampleData() {
        ((HeritageSiteDAOImpl) heritageSiteDAO).initWithSampleData();
    }
}
