package org.example.digitaltirtha.test;

import org.example.digitaltirtha.dao.UserDAO;
import org.example.digitaltirtha.dao.impl.UserDAOImpl;
import org.example.digitaltirtha.model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Test class to verify password hashing functionality.
 * This class can be run as a standalone Java application to test password hashing.
 */
public class PasswordHashingTest {

    /**
     * Main method to run the test.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Starting password hashing test...");
        
        try {
            // Create a UserDAO instance
            UserDAO userDAO = new UserDAOImpl();
            
            // Create a test user with a password
            User testUser = new User();
            testUser.setUsername("testuser_" + System.currentTimeMillis()); // Ensure unique username
            testUser.setPassword("password123");
            testUser.setEmail("test_" + System.currentTimeMillis() + "@example.com"); // Ensure unique email
            testUser.setFullName("Test User");
            testUser.setCountry("Test Country");
            testUser.setLocal(true);
            testUser.setRole("USER");
            
            // Create the user in the database
            User createdUser = userDAO.create(testUser);
            if (createdUser == null) {
                throw new RuntimeException("Failed to create test user");
            }
            
            System.out.println("Created test user: " + createdUser.getUsername() + " (ID: " + createdUser.getId() + ")");
            
            // Retrieve the user from the database
            User retrievedUser = userDAO.findById(createdUser.getId());
            if (retrievedUser == null) {
                throw new RuntimeException("Failed to retrieve test user");
            }
            
            // Verify that the password is hashed (not stored in plain text)
            String storedPassword = retrievedUser.getPassword();
            if (storedPassword == null || storedPassword.isEmpty()) {
                throw new RuntimeException("Password is null or empty");
            }
            
            if (storedPassword.equals("password123")) {
                throw new RuntimeException("Password is stored in plain text");
            }
            
            if (!storedPassword.startsWith("$2a$") && !storedPassword.startsWith("$2b$") && !storedPassword.startsWith("$2y$")) {
                throw new RuntimeException("Password does not appear to be hashed with BCrypt");
            }
            
            System.out.println("Password is properly hashed with BCrypt");
            
            // Test authentication with correct password
            User authenticatedUser = userDAO.authenticate(testUser.getUsername(), "password123");
            if (authenticatedUser == null) {
                throw new RuntimeException("Authentication failed with correct password");
            }
            
            System.out.println("Authentication successful with correct password");
            
            // Test authentication with incorrect password
            User failedAuth = userDAO.authenticate(testUser.getUsername(), "wrongpassword");
            if (failedAuth != null) {
                throw new RuntimeException("Authentication succeeded with incorrect password");
            }
            
            System.out.println("Authentication correctly failed with incorrect password");
            
            // Clean up - delete the test user
            boolean deleted = userDAO.delete(createdUser.getId());
            if (!deleted) {
                System.err.println("Warning: Failed to delete test user");
            } else {
                System.out.println("Test user deleted successfully");
            }
            
            System.out.println("Password hashing test completed successfully");
        } catch (Exception e) {
            System.err.println("Error during password hashing test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}