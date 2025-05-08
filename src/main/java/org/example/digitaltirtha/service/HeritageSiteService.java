package org.example.digitaltirtha.service;

import org.example.digitaltirtha.model.HeritageSite;
import org.example.digitaltirtha.model.MediaContribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class for managing heritage sites.
 */
public class HeritageSiteService {
    
    // In-memory storage for demo purposes
    // In a real application, this would use a database
    private Map<Long, HeritageSite> sitesMap = new HashMap<>();
    private long nextId = 1;
    
    /**
     * Creates a new heritage site.
     *
     * @param site The heritage site to create
     * @return The created heritage site with ID assigned
     */
    public HeritageSite createSite(HeritageSite site) {
        site.setId(nextId++);
        sitesMap.put(site.getId(), site);
        return site;
    }
    
    /**
     * Retrieves a heritage site by ID.
     *
     * @param id The ID of the heritage site to retrieve
     * @return The heritage site, or null if not found
     */
    public HeritageSite getSiteById(Long id) {
        return sitesMap.get(id);
    }
    
    /**
     * Updates an existing heritage site.
     *
     * @param site The heritage site to update
     * @return The updated heritage site, or null if not found
     */
    public HeritageSite updateSite(HeritageSite site) {
        if (sitesMap.containsKey(site.getId())) {
            sitesMap.put(site.getId(), site);
            return site;
        }
        return null;
    }
    
    /**
     * Deletes a heritage site by ID.
     *
     * @param id The ID of the heritage site to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteSite(Long id) {
        if (sitesMap.containsKey(id)) {
            sitesMap.remove(id);
            return true;
        }
        return false;
    }
    
    /**
     * Retrieves all heritage sites.
     *
     * @return A list of all heritage sites
     */
    public List<HeritageSite> getAllSites() {
        return new ArrayList<>(sitesMap.values());
    }
    
    /**
     * Retrieves heritage sites by risk level.
     *
     * @param riskLevel The risk level to filter by
     * @return A list of heritage sites with the specified risk level
     */
    public List<HeritageSite> getSitesByRiskLevel(String riskLevel) {
        return sitesMap.values().stream()
                .filter(site -> riskLevel.equals(site.getRiskLevel()))
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves heritage sites by category.
     *
     * @param category The category to filter by
     * @return A list of heritage sites with the specified category
     */
    public List<HeritageSite> getSitesByCategory(String category) {
        return sitesMap.values().stream()
                .filter(site -> category.equals(site.getCategory()))
                .collect(Collectors.toList());
    }
    
    /**
     * Records a visit to a heritage site.
     *
     * @param siteId The ID of the heritage site
     * @return The updated heritage site, or null if not found
     */
    public HeritageSite recordVisit(Long siteId) {
        HeritageSite site = sitesMap.get(siteId);
        if (site != null) {
            site.incrementVisitCount();
            return site;
        }
        return null;
    }
    
    /**
     * Records a donation to a heritage site.
     *
     * @param siteId The ID of the heritage site
     * @param amount The donation amount
     * @return The updated heritage site, or null if not found
     */
    public HeritageSite recordDonation(Long siteId, double amount) {
        HeritageSite site = sitesMap.get(siteId);
        if (site != null) {
            site.addDonation(amount);
            return site;
        }
        return null;
    }
    
    /**
     * Initializes the service with sample data.
     */
    public void initWithSampleData() {
        // Create sample heritage sites
        HeritageSite site1 = new HeritageSite(nextId++, "Swayambhunath Stupa", 
                "One of the oldest and most sacred Buddhist stupas in Nepal, also known as the Monkey Temple.", 
                "Kathmandu");
        site1.setRiskLevel("Medium");
        site1.setCategory("Temple");
        site1.setLatitude(27.7147);
        site1.setLongitude(85.2904);
        site1.setImageUrl("images/swayambhunath.jpg");
        site1.setVirtualTourUrl("tours/swayambhunath.html");
        
        HeritageSite site2 = new HeritageSite(nextId++, "Patan Durbar Square", 
                "A UNESCO World Heritage Site with ancient royal palaces, temples, and shrines.", 
                "Lalitpur");
        site2.setRiskLevel("High");
        site2.setCategory("UNESCO");
        site2.setLatitude(27.6726);
        site2.setLongitude(85.3239);
        site2.setImageUrl("images/patan.jpg");
        site2.setVirtualTourUrl("tours/patan.html");
        
        HeritageSite site3 = new HeritageSite(nextId++, "Boudhanath Stupa", 
                "One of the largest spherical stupas in Nepal and a UNESCO World Heritage Site.", 
                "Kathmandu");
        site3.setRiskLevel("Low");
        site3.setCategory("UNESCO");
        site3.setLatitude(27.7215);
        site3.setLongitude(85.3620);
        site3.setImageUrl("images/boudhanath.jpg");
        site3.setVirtualTourUrl("tours/boudhanath.html");
        
        // Add sites to the map
        sitesMap.put(site1.getId(), site1);
        sitesMap.put(site2.getId(), site2);
        sitesMap.put(site3.getId(), site3);
    }
}