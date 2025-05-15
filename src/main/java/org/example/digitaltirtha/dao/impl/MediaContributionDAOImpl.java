package org.example.digitaltirtha.dao.impl;

import org.example.digitaltirtha.config.DatabaseConfig;
import org.example.digitaltirtha.dao.MediaContributionDAO;
import org.example.digitaltirtha.model.MediaContribution;
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
 * Implementation of MediaContributionDAO that uses a database.
 */
public class MediaContributionDAOImpl implements MediaContributionDAO {

    private final JdbcTemplate jdbcTemplate;

    // SQL statements
    private static final String SQL_INSERT = 
            "INSERT INTO media_contributions (user_id, site_id, media_type, file_url, thumbnail_url, " +
            "title, description, upload_date, capture_date, status, location, latitude, longitude, " +
            "has_damage_detection, damage_level, damage_description, view_count, like_count) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_BY_ID = 
            "SELECT * FROM media_contributions WHERE id = ?";

    private static final String SQL_SELECT_ALL = 
            "SELECT * FROM media_contributions";

    private static final String SQL_UPDATE = 
            "UPDATE media_contributions SET user_id = ?, site_id = ?, media_type = ?, file_url = ?, " +
            "thumbnail_url = ?, title = ?, description = ?, capture_date = ?, status = ?, location = ?, " +
            "latitude = ?, longitude = ?, has_damage_detection = ?, damage_level = ?, " +
            "damage_description = ?, view_count = ?, like_count = ? WHERE id = ?";

    private static final String SQL_DELETE = 
            "DELETE FROM media_contributions WHERE id = ?";

    private static final String SQL_SELECT_BY_USER_ID = 
            "SELECT * FROM media_contributions WHERE user_id = ?";

    private static final String SQL_SELECT_BY_SITE_ID = 
            "SELECT * FROM media_contributions WHERE site_id = ?";

    private static final String SQL_SELECT_BY_STATUS = 
            "SELECT * FROM media_contributions WHERE status = ?";

    private static final String SQL_SELECT_BY_MEDIA_TYPE = 
            "SELECT * FROM media_contributions WHERE media_type = ?";

    private static final String SQL_UPDATE_STATUS_APPROVED = 
            "UPDATE media_contributions SET status = 'APPROVED' WHERE id = ?";

    private static final String SQL_UPDATE_STATUS_REJECTED = 
            "UPDATE media_contributions SET status = 'REJECTED' WHERE id = ?";

    private static final String SQL_UPDATE_VIEW_COUNT = 
            "UPDATE media_contributions SET view_count = view_count + 1 WHERE id = ?";

    private static final String SQL_UPDATE_LIKE_COUNT = 
            "UPDATE media_contributions SET like_count = like_count + 1 WHERE id = ?";

    private static final String SQL_UPDATE_DAMAGE_ANALYSIS = 
            "UPDATE media_contributions SET has_damage_detection = true, damage_level = ?, " +
            "damage_description = ? WHERE id = ?";

    // Row mapper for MediaContribution
    private final RowMapper<MediaContribution> rowMapper = (rs, rowNum) -> {
        MediaContribution contribution = new MediaContribution();
        contribution.setId(rs.getLong("id"));
        contribution.setUserId(rs.getLong("user_id"));
        contribution.setSiteId(rs.getLong("site_id"));
        contribution.setMediaType(rs.getString("media_type"));
        contribution.setFileUrl(rs.getString("file_url"));
        contribution.setThumbnailUrl(rs.getString("thumbnail_url"));
        contribution.setTitle(rs.getString("title"));
        contribution.setDescription(rs.getString("description"));
        contribution.setUploadDate(rs.getTimestamp("upload_date"));
        contribution.setCaptureDate(rs.getTimestamp("capture_date"));
        contribution.setStatus(rs.getString("status"));
        contribution.setLocation(rs.getString("location"));
        contribution.setLatitude(rs.getDouble("latitude"));
        contribution.setLongitude(rs.getDouble("longitude"));
        contribution.setHasDamageDetection(rs.getBoolean("has_damage_detection"));
        contribution.setDamageLevel(rs.getString("damage_level"));
        contribution.setDamageDescription(rs.getString("damage_description"));
        contribution.setViewCount(rs.getInt("view_count"));
        contribution.setLikeCount(rs.getInt("like_count"));
        return contribution;
    };

    public MediaContributionDAOImpl() {
        this.jdbcTemplate = DatabaseConfig.getJdbcTemplate();
    }

    /**
     * Creates a new media contribution.
     *
     * @param contribution The media contribution to create
     * @return The created media contribution with ID assigned
     */
    @Override
    public MediaContribution create(MediaContribution contribution) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Date now = new Date();
        contribution.setUploadDate(now);

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, contribution.getUserId());
            ps.setLong(2, contribution.getSiteId());
            ps.setString(3, contribution.getMediaType());
            ps.setString(4, contribution.getFileUrl());
            ps.setString(5, contribution.getThumbnailUrl());
            ps.setString(6, contribution.getTitle());
            ps.setString(7, contribution.getDescription());
            ps.setTimestamp(8, new Timestamp(now.getTime()));
            ps.setTimestamp(9, contribution.getCaptureDate() != null ? 
                    new Timestamp(contribution.getCaptureDate().getTime()) : null);
            ps.setString(10, contribution.getStatus() != null ? contribution.getStatus() : "PENDING");
            ps.setString(11, contribution.getLocation());
            ps.setDouble(12, contribution.getLatitude());
            ps.setDouble(13, contribution.getLongitude());
            ps.setBoolean(14, contribution.isHasDamageDetection());
            ps.setString(15, contribution.getDamageLevel());
            ps.setString(16, contribution.getDamageDescription());
            ps.setInt(17, contribution.getViewCount());
            ps.setInt(18, contribution.getLikeCount());
            return ps;
        }, keyHolder);

        contribution.setId(keyHolder.getKey().longValue());
        return contribution;
    }

    /**
     * Retrieves a media contribution by ID.
     *
     * @param id The ID of the media contribution to retrieve
     * @return The media contribution, or null if not found
     */
    @Override
    public MediaContribution findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves all media contributions.
     *
     * @return A list of all media contributions
     */
    @Override
    public List<MediaContribution> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    /**
     * Updates an existing media contribution.
     *
     * @param contribution The media contribution to update
     * @return The updated media contribution, or null if not found
     */
    @Override
    public MediaContribution update(MediaContribution contribution) {
        int rowsAffected = jdbcTemplate.update(SQL_UPDATE,
                contribution.getUserId(),
                contribution.getSiteId(),
                contribution.getMediaType(),
                contribution.getFileUrl(),
                contribution.getThumbnailUrl(),
                contribution.getTitle(),
                contribution.getDescription(),
                contribution.getCaptureDate() != null ? 
                        new Timestamp(contribution.getCaptureDate().getTime()) : null,
                contribution.getStatus(),
                contribution.getLocation(),
                contribution.getLatitude(),
                contribution.getLongitude(),
                contribution.isHasDamageDetection(),
                contribution.getDamageLevel(),
                contribution.getDamageDescription(),
                contribution.getViewCount(),
                contribution.getLikeCount(),
                contribution.getId());

        return rowsAffected > 0 ? contribution : null;
    }

    /**
     * Deletes a media contribution by ID.
     *
     * @param id The ID of the media contribution to delete
     * @return true if deleted, false if not found
     */
    @Override
    public boolean delete(Long id) {
        int rowsAffected = jdbcTemplate.update(SQL_DELETE, id);
        return rowsAffected > 0;
    }

    /**
     * Retrieves media contributions by user ID.
     *
     * @param userId The ID of the user
     * @return A list of media contributions by the specified user
     */
    @Override
    public List<MediaContribution> findByUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_BY_USER_ID, rowMapper, userId);
    }

    /**
     * Retrieves media contributions by site ID.
     *
     * @param siteId The ID of the heritage site
     * @return A list of media contributions for the specified site
     */
    @Override
    public List<MediaContribution> findBySiteId(Long siteId) {
        return jdbcTemplate.query(SQL_SELECT_BY_SITE_ID, rowMapper, siteId);
    }

    /**
     * Retrieves media contributions by status.
     *
     * @param status The status to filter by (e.g., "Pending", "Approved", "Rejected")
     * @return A list of media contributions with the specified status
     */
    @Override
    public List<MediaContribution> findByStatus(String status) {
        return jdbcTemplate.query(SQL_SELECT_BY_STATUS, rowMapper, status);
    }

    /**
     * Retrieves media contributions by media type.
     *
     * @param mediaType The media type to filter by (e.g., "Photo", "Video")
     * @return A list of media contributions with the specified media type
     */
    @Override
    public List<MediaContribution> findByMediaType(String mediaType) {
        return jdbcTemplate.query(SQL_SELECT_BY_MEDIA_TYPE, rowMapper, mediaType);
    }

    /**
     * Approves a media contribution.
     *
     * @param id The ID of the media contribution to approve
     * @return The approved media contribution, or null if not found
     */
    @Override
    public MediaContribution approve(Long id) {
        int rowsAffected = jdbcTemplate.update(SQL_UPDATE_STATUS_APPROVED, id);
        if (rowsAffected > 0) {
            return findById(id);
        }
        return null;
    }

    /**
     * Rejects a media contribution with a reason.
     *
     * @param id The ID of the media contribution to reject
     * @param reason The reason for rejection
     * @return The rejected media contribution, or null if not found
     */
    @Override
    public MediaContribution reject(Long id, String reason) {
        int rowsAffected = jdbcTemplate.update(SQL_UPDATE_STATUS_REJECTED, id);
        if (rowsAffected > 0) {
            // Note: We're not storing the rejection reason in the database in this implementation
            // In a real application, you would add a column for rejection_reason
            return findById(id);
        }
        return null;
    }

    /**
     * Records a view of a media contribution.
     *
     * @param id The ID of the media contribution
     * @return The updated media contribution, or null if not found
     */
    @Override
    public MediaContribution recordView(Long id) {
        int rowsAffected = jdbcTemplate.update(SQL_UPDATE_VIEW_COUNT, id);
        if (rowsAffected > 0) {
            return findById(id);
        }
        return null;
    }

    /**
     * Records a like for a media contribution.
     *
     * @param id The ID of the media contribution
     * @return The updated media contribution, or null if not found
     */
    @Override
    public MediaContribution recordLike(Long id) {
        int rowsAffected = jdbcTemplate.update(SQL_UPDATE_LIKE_COUNT, id);
        if (rowsAffected > 0) {
            return findById(id);
        }
        return null;
    }

    /**
     * Sets damage analysis for a media contribution.
     *
     * @param id The ID of the media contribution
     * @param damageLevel The level of damage detected
     * @param damageDescription The description of the damage
     * @return The updated media contribution, or null if not found
     */
    @Override
    public MediaContribution setDamageAnalysis(Long id, String damageLevel, String damageDescription) {
        int rowsAffected = jdbcTemplate.update(SQL_UPDATE_DAMAGE_ANALYSIS, 
                damageLevel, damageDescription, id);
        if (rowsAffected > 0) {
            return findById(id);
        }
        return null;
    }

    /**
     * Initializes the DAO with sample data.
     */
    public void initWithSampleData() {
        // Create sample media contributions
        MediaContribution contribution1 = new MediaContribution();
        contribution1.setUserId(1L);
        contribution1.setSiteId(1L);
        contribution1.setMediaType("Photo");
        contribution1.setFileUrl("uploads/swayambhunath1.jpg");
        contribution1.setTitle("Swayambhunath Stupa Main View");
        contribution1.setDescription("A view of the main stupa from the eastern entrance.");
        contribution1.setThumbnailUrl("thumbnails/swayambhunath1.jpg");
        contribution1.setLocation("Kathmandu");
        contribution1.setLatitude(27.7147);
        contribution1.setLongitude(85.2904);
        contribution1.setCaptureDate(new Date());
        contribution1.setStatus("APPROVED");

        MediaContribution contribution2 = new MediaContribution();
        contribution2.setUserId(2L);
        contribution2.setSiteId(2L);
        contribution2.setMediaType("Video");
        contribution2.setFileUrl("uploads/patan_durbar_square.mp4");
        contribution2.setTitle("Patan Durbar Square Walkthrough");
        contribution2.setDescription("A walking tour of Patan Durbar Square showing the current state of restoration.");
        contribution2.setThumbnailUrl("thumbnails/patan_durbar_square.jpg");
        contribution2.setLocation("Lalitpur");
        contribution2.setLatitude(27.6726);
        contribution2.setLongitude(85.3239);
        contribution2.setCaptureDate(new Date());
        contribution2.setStatus("PENDING");

        MediaContribution contribution3 = new MediaContribution();
        contribution3.setUserId(3L);
        contribution3.setSiteId(3L);
        contribution3.setMediaType("Photo");
        contribution3.setFileUrl("uploads/boudhanath1.jpg");
        contribution3.setTitle("Boudhanath Stupa After Restoration");
        contribution3.setDescription("The Boudhanath Stupa after the 2015 earthquake restoration work.");
        contribution3.setThumbnailUrl("thumbnails/boudhanath1.jpg");
        contribution3.setLocation("Kathmandu");
        contribution3.setLatitude(27.7215);
        contribution3.setLongitude(85.3620);
        contribution3.setCaptureDate(new Date());
        contribution3.setStatus("APPROVED");
        contribution3.setHasDamageDetection(true);
        contribution3.setDamageLevel("Low");
        contribution3.setDamageDescription("Minor cracks visible on the eastern side of the base.");

        // Add contributions to the database
        create(contribution1);
        create(contribution2);
        create(contribution3);
    }
}
