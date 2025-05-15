package org.example.digitaltirtha.config;

import org.example.digitaltirtha.model.User;
import org.example.digitaltirtha.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Utility class for fetching data from the database and ensuring an admin user exists.
 */
public class DatabaseDataFetcher {

    private static final JdbcTemplate jdbcTemplate = DatabaseConfig.getJdbcTemplate();
    private static final UserService userService = new UserService();

    /**
     * Fetches all users from the database.
     * 
     * @return A list of all users
     */
    public static List<User> fetchAllUsers() {
        String sql = "SELECT * FROM users";
        
        RowMapper<User> rowMapper = (rs, rowNum) -> mapUserFromResultSet(rs);
        
        List<User> users = jdbcTemplate.query(sql, rowMapper);
        System.out.println("Fetched " + users.size() + " users from database");
        return users;
    }
    
    /**
     * Ensures that an admin user exists in the database.
     * If no admin user exists, creates one.
     * 
     * @return The admin user
     */
    public static User ensureAdminUserExists() {
        // Check if admin user exists
        String sql = "SELECT * FROM users WHERE role = 'ADMIN' LIMIT 1";
        
        RowMapper<User> rowMapper = (rs, rowNum) -> mapUserFromResultSet(rs);
        
        List<User> adminUsers = jdbcTemplate.query(sql, rowMapper);
        
        if (!adminUsers.isEmpty()) {
            System.out.println("Admin user already exists: " + adminUsers.get(0).getUsername());
            return adminUsers.get(0);
        }
        
        // Create admin user
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("admin123"); // In a real app, this would be hashed
        adminUser.setEmail("admin@digitaltirtha.org");
        adminUser.setFullName("Admin User");
        adminUser.setCountry("Nepal");
        adminUser.setLocal(true);
        adminUser.setRole("ADMIN");
        
        User createdAdmin = userService.registerUser(adminUser);
        
        if (createdAdmin != null) {
            System.out.println("Created admin user: " + createdAdmin.getUsername());
            return createdAdmin;
        } else {
            System.err.println("Failed to create admin user");
            return null;
        }
    }
    
    /**
     * Maps a ResultSet row to a User object.
     * 
     * @param rs The ResultSet
     * @return The User object
     * @throws SQLException If an error occurs while accessing the ResultSet
     */
    private static User mapUserFromResultSet(ResultSet rs) throws SQLException {
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
    }
    
    /**
     * Main method for testing.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Initialize database
        try {
            // Ensure database connection is available
            if (DatabaseConfig.getDataSource() == null) {
                throw new RuntimeException("Database connection not initialized");
            }
            
            // Fetch all users
            List<User> users = fetchAllUsers();
            for (User user : users) {
                System.out.println("User: " + user.getUsername() + ", Role: " + user.getRole());
            }
            
            // Ensure admin user exists
            User adminUser = ensureAdminUserExists();
            if (adminUser != null) {
                System.out.println("Admin user: " + adminUser.getUsername() + ", ID: " + adminUser.getId());
            }
            
            System.out.println("Database data fetching completed successfully");
        } catch (Exception e) {
            System.err.println("Error fetching data from database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}