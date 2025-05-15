package org.example.digitaltirtha.dao.impl;

import org.example.digitaltirtha.config.DatabaseConfig;
import org.example.digitaltirtha.dao.UserDAO;
import org.example.digitaltirtha.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.DataIntegrityViolationException;
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
 * Implementation of UserDAO that uses a database.
 */
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    // SQL statements
    private static final String SQL_INSERT = 
            "INSERT INTO users (username, password, email, full_name, country, is_local, " +
            "karma_points, registration_date, last_login_date, sites_visited, media_contributed, " +
            "total_donations, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_BY_ID = 
            "SELECT * FROM users WHERE id = ?";

    private static final String SQL_SELECT_ALL = 
            "SELECT * FROM users";

    private static final String SQL_UPDATE = 
            "UPDATE users SET username = ?, password = ?, email = ?, full_name = ?, " +
            "country = ?, is_local = ?, karma_points = ?, last_login_date = ?, " +
            "sites_visited = ?, media_contributed = ?, total_donations = ?, role = ? " +
            "WHERE id = ?";

    private static final String SQL_DELETE = 
            "DELETE FROM users WHERE id = ?";

    private static final String SQL_SELECT_BY_USERNAME = 
            "SELECT * FROM users WHERE username = ?";

    private static final String SQL_SELECT_BY_EMAIL = 
            "SELECT * FROM users WHERE email = ?";

    private static final String SQL_UPDATE_LAST_LOGIN = 
            "UPDATE users SET last_login_date = ? WHERE id = ?";

    // Row mapper for User
    private final RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setFullName(rs.getString("full_name"));
        user.setCountry(rs.getString("country"));
        user.setLocal(rs.getBoolean("is_local"));
        user.setKarmaPoints(rs.getInt("karma_points"));
        user.setRegistrationDate(rs.getTimestamp("registration_date"));
        user.setLastLoginDate(rs.getTimestamp("last_login_date"));
        user.setSitesVisited(rs.getInt("sites_visited"));
        user.setMediaContributed(rs.getInt("media_contributed"));
        user.setTotalDonations(rs.getDouble("total_donations"));
        user.setRole(rs.getString("role"));
        return user;
    };

    public UserDAOImpl() {
        this.jdbcTemplate = DatabaseConfig.getJdbcTemplate();
    }

    /**
     * Creates a new user.
     *
     * @param user The user to create
     * @return The created user with ID assigned, or null if username or email already exists
     */
    @Override
    public User create(User user) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            Date now = new Date();
            user.setRegistrationDate(now);

            // Hash the password using BCrypt before storing
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUsername());
                ps.setString(2, hashedPassword); // Store the hashed password
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getFullName());
                ps.setString(5, user.getCountry());
                ps.setBoolean(6, user.isLocal());
                ps.setInt(7, user.getKarmaPoints());
                ps.setTimestamp(8, new Timestamp(now.getTime()));
                ps.setTimestamp(9, user.getLastLoginDate() != null ? 
                        new Timestamp(user.getLastLoginDate().getTime()) : null);
                ps.setInt(10, user.getSitesVisited());
                ps.setInt(11, user.getMediaContributed());
                ps.setDouble(12, user.getTotalDonations());
                ps.setString(13, user.getRole() != null ? user.getRole() : "USER");
                return ps;
            }, keyHolder);

            user.setId(keyHolder.getKey().longValue());
            // Don't store the plain text password in the User object
            user.setPassword(null);
            return user;
        } catch (DataIntegrityViolationException e) {
            // This exception is thrown when a unique constraint is violated
            // (e.g., username or email already exists)
            return null;
        }
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve
     * @return The user, or null if not found
     */
    @Override
    public User findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all users
     */
    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    /**
     * Updates an existing user.
     *
     * @param user The user to update
     * @return The updated user, or null if not found
     */
    @Override
    public User update(User user) {
        try {
            // Get the current user from the database to check if password needs to be updated
            User existingUser = findById(user.getId());
            String passwordToUse;

            // If password is provided and different from what's in the database, hash it
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                passwordToUse = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            } else if (existingUser != null) {
                // Keep the existing hashed password if no new password is provided
                passwordToUse = existingUser.getPassword();
            } else {
                // User not found
                return null;
            }

            int rowsAffected = jdbcTemplate.update(SQL_UPDATE,
                    user.getUsername(),
                    passwordToUse, // Use the hashed password
                    user.getEmail(),
                    user.getFullName(),
                    user.getCountry(),
                    user.isLocal(),
                    user.getKarmaPoints(),
                    user.getLastLoginDate() != null ? 
                            new Timestamp(user.getLastLoginDate().getTime()) : null,
                    user.getSitesVisited(),
                    user.getMediaContributed(),
                    user.getTotalDonations(),
                    user.getRole(),
                    user.getId());

            if (rowsAffected > 0) {
                // Don't store the plain text password in the User object
                user.setPassword(null);
                return user;
            } else {
                return null;
            }
        } catch (DataIntegrityViolationException e) {
            // This exception is thrown when a unique constraint is violated
            // (e.g., username or email already exists)
            return null;
        }
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete
     * @return true if deleted, false if not found
     */
    @Override
    public boolean delete(Long id) {
        int rowsAffected = jdbcTemplate.update(SQL_DELETE, id);
        return rowsAffected > 0;
    }

    /**
     * Retrieves a user by username.
     *
     * @param username The username of the user to retrieve
     * @return The user, or null if not found
     */
    @Override
    public User findByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_USERNAME, rowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves a user by email.
     *
     * @param email The email of the user to retrieve
     * @return The user, or null if not found
     */
    @Override
    public User findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, rowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Authenticates a user.
     *
     * @param username The username
     * @param password The password
     * @return The authenticated user, or null if authentication fails
     */
    @Override
    public User authenticate(String username, String password) {
        User user = findByUsername(username);
        // Use BCrypt.checkpw to verify the password against the stored hash
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            Date now = new Date();
            jdbcTemplate.update(SQL_UPDATE_LAST_LOGIN, new Timestamp(now.getTime()), user.getId());
            user.setLastLoginDate(now);
            // Don't return the hashed password to the caller
            user.setPassword(null);
            return user;
        }
        return null;
    }

    /**
     * Initializes the DAO with sample data.
     */
    public void initWithSampleData() {
        // Create sample users
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin123"); // Will be hashed by create method
        admin.setEmail("admin@digitaltirtha.org");
        admin.setFullName("Admin User");
        admin.setCountry("Nepal");
        admin.setLocal(true);
        admin.setRole("ADMIN");

        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("john123"); // Will be hashed by create method
        user1.setEmail("john@example.com");
        user1.setFullName("John Doe");
        user1.setCountry("USA");
        user1.setLocal(false);
        user1.setRole("USER");

        User user2 = new User();
        user2.setUsername("jane");
        user2.setPassword("jane123"); // Will be hashed by create method
        user2.setEmail("jane@example.com");
        user2.setFullName("Jane Smith");
        user2.setCountry("Nepal");
        user2.setLocal(true);
        user2.setRole("MODERATOR");

        // Add users to the database - passwords will be hashed by the create method
        create(admin);
        create(user1);
        create(user2);
    }
}
