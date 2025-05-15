package org.example.digitaltirtha.dao;

import org.example.digitaltirtha.model.User;

/**
 * DAO interface for User entities.
 */
public interface UserDAO extends GenericDAO<User, Long> {
    
    /**
     * Retrieves a user by username.
     *
     * @param username The username of the user to retrieve
     * @return The user, or null if not found
     */
    User findByUsername(String username);
    
    /**
     * Retrieves a user by email.
     *
     * @param email The email of the user to retrieve
     * @return The user, or null if not found
     */
    User findByEmail(String email);
    
    /**
     * Authenticates a user.
     *
     * @param username The username
     * @param password The password
     * @return The authenticated user, or null if authentication fails
     */
    User authenticate(String username, String password);
}