package org.example.digitaltirtha.dao.impl;

import org.example.digitaltirtha.config.DatabaseConfig;
import org.example.digitaltirtha.dao.HeritageSiteDAO;
import org.example.digitaltirtha.model.HeritageSite;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Implementation of HeritageSiteDAO that uses a database.
 */
public class HeritageSiteDAOImpl implements HeritageSiteDAO {

    private final JdbcTemplate jdbcTemplate;

    // SQL statements
    private static final String SQL_INSERT = 
            "INSERT INTO heritage_sites (name, description, location, latitude, longitude, " +
            "risk_level, category, created_date, last_updated, image_url, virtual_tour_url, visit_count, donation_amount) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_BY_ID = 
            "SELECT * FROM heritage_sites WHERE id = ?";

    private static final String SQL_SELECT_ALL = 
            "SELECT * FROM heritage_sites";

    private static final String SQL_UPDATE = 
            "UPDATE heritage_sites SET name = ?, description = ?, location = ?, latitude = ?, " +
            "longitude = ?, risk_level = ?, category = ?, last_updated = ?, image_url = ?, " +
            "virtual_tour_url = ?, visit_count = ?, donation_amount = ? WHERE id = ?";

    private static final String SQL_DELETE = 
            "DELETE FROM heritage_sites WHERE id = ?";

    private static final String SQL_SELECT_BY_RISK_LEVEL = 
            "SELECT * FROM heritage_sites WHERE risk_level = ?";

    private static final String SQL_SELECT_BY_CATEGORY = 
            "SELECT * FROM heritage_sites WHERE category = ?";

    private static final String SQL_UPDATE_VISIT_COUNT = 
            "UPDATE heritage_sites SET visit_count = visit_count + 1, last_updated = ? WHERE id = ?";

    private static final String SQL_UPDATE_DONATION_AMOUNT = 
            "UPDATE heritage_sites SET donation_amount = donation_amount + ?, last_updated = ? WHERE id = ?";

    // Row mapper for HeritageSite
    private final RowMapper<HeritageSite> rowMapper = (rs, rowNum) -> {
        HeritageSite site = new HeritageSite();
        site.setId(rs.getLong("id"));
        site.setName(rs.getString("name"));
        site.setDescription(rs.getString("description"));
        site.setLocation(rs.getString("location"));
        site.setLatitude(rs.getDouble("latitude"));
        site.setLongitude(rs.getDouble("longitude"));
        site.setRiskLevel(rs.getString("risk_level"));
        site.setCategory(rs.getString("category"));
        site.setCreatedDate(rs.getTimestamp("created_date"));
        site.setLastUpdated(rs.getTimestamp("last_updated"));
        site.setImageUrl(rs.getString("image_url"));
        site.setVirtualTourUrl(rs.getString("virtual_tour_url"));
        site.setVisitCount(rs.getInt("visit_count"));
        site.setDonationAmount(rs.getDouble("donation_amount"));
        return site;
    };

    public HeritageSiteDAOImpl() {
        this.jdbcTemplate = DatabaseConfig.getJdbcTemplate();
    }

    /**
     * Creates a new heritage site.
     *
     * @param site The heritage site to create
     * @return The created heritage site with ID assigned
     */
    @Override
    public HeritageSite create(HeritageSite site) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Date now = new Date();
        site.setCreatedDate(now);
        site.setLastUpdated(now);

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, site.getName());
            ps.setString(2, site.getDescription());
            ps.setString(3, site.getLocation());
            ps.setDouble(4, site.getLatitude());
            ps.setDouble(5, site.getLongitude());
            ps.setString(6, site.getRiskLevel());
            ps.setString(7, site.getCategory());
            ps.setTimestamp(8, new Timestamp(site.getCreatedDate().getTime()));
            ps.setTimestamp(9, new Timestamp(site.getLastUpdated().getTime()));
            ps.setString(10, site.getImageUrl());
            ps.setString(11, site.getVirtualTourUrl());
            ps.setInt(12, site.getVisitCount());
            ps.setDouble(13, site.getDonationAmount());
            return ps;
        }, keyHolder);

        site.setId(keyHolder.getKey().longValue());
        return site;
    }

    /**
     * Retrieves a heritage site by ID.
     *
     * @param id The ID of the heritage site to retrieve
     * @return The heritage site, or null if not found
     */
    @Override
    public HeritageSite findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves all heritage sites.
     *
     * @return A list of all heritage sites
     */
    @Override
    public List<HeritageSite> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    /**
     * Updates an existing heritage site.
     *
     * @param site The heritage site to update
     * @return The updated heritage site, or null if not found
     */
    @Override
    public HeritageSite update(HeritageSite site) {
        site.setLastUpdated(new Date());

        int rowsAffected = jdbcTemplate.update(SQL_UPDATE,
                site.getName(),
                site.getDescription(),
                site.getLocation(),
                site.getLatitude(),
                site.getLongitude(),
                site.getRiskLevel(),
                site.getCategory(),
                new Timestamp(site.getLastUpdated().getTime()),
                site.getImageUrl(),
                site.getVirtualTourUrl(),
                site.getVisitCount(),
                site.getDonationAmount(),
                site.getId());

        return rowsAffected > 0 ? site : null;
    }

    /**
     * Deletes a heritage site by ID.
     *
     * @param id The ID of the heritage site to delete
     * @return true if deleted, false if not found
     */
    @Override
    public boolean delete(Long id) {
        int rowsAffected = jdbcTemplate.update(SQL_DELETE, id);
        return rowsAffected > 0;
    }

    /**
     * Retrieves heritage sites by risk level.
     *
     * @param riskLevel The risk level to filter by
     * @return A list of heritage sites with the specified risk level
     */
    @Override
    public List<HeritageSite> findByRiskLevel(String riskLevel) {
        return jdbcTemplate.query(SQL_SELECT_BY_RISK_LEVEL, rowMapper, riskLevel);
    }

    /**
     * Retrieves heritage sites by category.
     *
     * @param category The category to filter by
     * @return A list of heritage sites with the specified category
     */
    @Override
    public List<HeritageSite> findByCategory(String category) {
        return jdbcTemplate.query(SQL_SELECT_BY_CATEGORY, rowMapper, category);
    }

    /**
     * Records a visit to a heritage site.
     *
     * @param siteId The ID of the heritage site
     * @return The updated heritage site, or null if not found
     */
    @Override
    public HeritageSite recordVisit(Long siteId) {
        Date now = new Date();
        int rowsAffected = jdbcTemplate.update(SQL_UPDATE_VISIT_COUNT, 
                new Timestamp(now.getTime()), siteId);

        if (rowsAffected > 0) {
            return findById(siteId);
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
    @Override
    public HeritageSite recordDonation(Long siteId, double amount) {
        Date now = new Date();
        int rowsAffected = jdbcTemplate.update(SQL_UPDATE_DONATION_AMOUNT, 
                amount, new Timestamp(now.getTime()), siteId);

        if (rowsAffected > 0) {
            return findById(siteId);
        }
        return null;
    }

    /**
     * Initializes the DAO with sample data.
     */
    public void initWithSampleData() {
        // Create sample heritage sites
        HeritageSite site1 = new HeritageSite();
        site1.setName("Swayambhunath Stupa");
        site1.setDescription("One of the oldest and most sacred Buddhist stupas in Nepal, also known as the Monkey Temple.");
        site1.setLocation("Kathmandu");
        site1.setRiskLevel("Medium");
        site1.setCategory("Temple");
        site1.setLatitude(27.7147);
        site1.setLongitude(85.2904);
        site1.setImageUrl("images/swayambhunath.jpg");
        site1.setVirtualTourUrl("tours/swayambhunath.html");

        HeritageSite site2 = new HeritageSite();
        site2.setName("Patan Durbar Square");
        site2.setDescription("A UNESCO World Heritage Site with ancient royal palaces, temples, and shrines.");
        site2.setLocation("Lalitpur");
        site2.setRiskLevel("High");
        site2.setCategory("UNESCO");
        site2.setLatitude(27.6726);
        site2.setLongitude(85.3239);
        site2.setImageUrl("images/patan.jpg");
        site2.setVirtualTourUrl("tours/patan.html");

        HeritageSite site3 = new HeritageSite();
        site3.setName("Boudhanath Stupa");
        site3.setDescription("One of the largest spherical stupas in Nepal and a UNESCO World Heritage Site.");
        site3.setLocation("Kathmandu");
        site3.setRiskLevel("Low");
        site3.setCategory("UNESCO");
        site3.setLatitude(27.7215);
        site3.setLongitude(85.3620);
        site3.setImageUrl("images/boudhanath.jpg");
        site3.setVirtualTourUrl("tours/boudhanath.html");

        // Add sites to the database
        create(site1);
        create(site2);
        create(site3);
    }
}
