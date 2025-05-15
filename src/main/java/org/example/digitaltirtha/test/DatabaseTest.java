package org.example.digitaltirtha.test;

import org.example.digitaltirtha.config.DatabaseConfig;
import org.example.digitaltirtha.config.DatabaseDataFetcher;
import org.example.digitaltirtha.model.User;

import java.util.List;

/**
 * Simple test class to verify database functionality.
 * This class can be run as a standalone Java application to test database operations.
 */
public class DatabaseTest {

    /**
     * Main method to run the test.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Starting database test...");
        
        try {
            // Verify database connection
            if (DatabaseConfig.getDataSource() == null) {
                throw new RuntimeException("Database connection not initialized");
            }
            
            System.out.println("Database connection established successfully");
            
            // Test fetching users
            List<User> users = DatabaseDataFetcher.fetchAllUsers();
            System.out.println("Found " + users.size() + " users in the database");
            
            // Print user details
            for (User user : users) {
                System.out.println("User: " + user.getUsername() + 
                                  ", Email: " + user.getEmail() + 
                                  ", Role: " + user.getRole());
            }
            
            // Test ensuring admin user exists
            User adminUser = DatabaseDataFetcher.ensureAdminUserExists();
            if (adminUser != null) {
                System.out.println("Admin user verified: " + adminUser.getUsername() + 
                                  " (ID: " + adminUser.getId() + ")");
            } else {
                System.err.println("Failed to verify admin user");
            }
            
            // Fetch users again to verify admin was created if needed
            List<User> updatedUsers = DatabaseDataFetcher.fetchAllUsers();
            System.out.println("Now found " + updatedUsers.size() + " users in the database");
            
            System.out.println("Database test completed successfully");
        } catch (Exception e) {
            System.err.println("Error during database test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}