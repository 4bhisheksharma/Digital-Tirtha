package org.example.digitaltirtha.service;

import org.example.digitaltirtha.dao.UserDAO;
import org.example.digitaltirtha.dao.impl.UserDAOImpl;
import org.example.digitaltirtha.model.User;

import java.util.Date;
import java.util.List;

/**
 * Service class for managing users.
 * Implements business logic and delegates data access to DAO.
 */
public class UserService {

    private UserDAO userDAO;

    /**
     * Constructor that initializes the DAO.
     */
    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    /**
     * Constructor with DAO injection for testing.
     * 
     * @param userDAO The DAO to use
     */
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Registers a new user.
     *
     * @param user The user to register
     * @return The registered user with ID assigned, or null if username or email already exists
     */
    public User registerUser(User user) {
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        return userDAO.create(user);
    }

    /**
     * Authenticates a user.
     *
     * @param username The username
     * @param password The password
     * @return The authenticated user, or null if authentication fails
     */
    public User authenticateUser(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve
     * @return The user, or null if not found
     */
    public User getUserById(Long id) {
        return userDAO.findById(id);
    }

    /**
     * Retrieves a user by username.
     *
     * @param username The username of the user to retrieve
     * @return The user, or null if not found
     */
    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    /**
     * Updates an existing user.
     *
     * @param user The user to update
     * @return The updated user, or null if not found
     */
    public User updateUser(User user) {
        return userDAO.update(user);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteUser(Long id) {
        return userDAO.delete(id);
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all users
     */
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    /**
     * Adds karma points to a user.
     *
     * @param userId The ID of the user
     * @param points The number of karma points to add
     * @return The updated user, or null if not found
     */
    public User addKarmaPoints(Long userId, int points) {
        User user = userDAO.findById(userId);
        if (user != null) {
            user.addKarmaPoints(points);
            return userDAO.update(user);
        }
        return null;
    }

    /**
     * Records a site visit by a user.
     *
     * @param userId The ID of the user
     * @return The updated user, or null if not found
     */
    public User recordSiteVisit(Long userId) {
        User user = userDAO.findById(userId);
        if (user != null) {
            user.incrementSitesVisited();
            return userDAO.update(user);
        }
        return null;
    }

    /**
     * Records a media contribution by a user.
     *
     * @param userId The ID of the user
     * @return The updated user, or null if not found
     */
    public User recordMediaContribution(Long userId) {
        User user = userDAO.findById(userId);
        if (user != null) {
            user.incrementMediaContributed();
            return userDAO.update(user);
        }
        return null;
    }

    /**
     * Records a donation by a user.
     *
     * @param userId The ID of the user
     * @param amount The donation amount
     * @return The updated user, or null if not found
     */
    public User recordDonation(Long userId, double amount) {
        User user = userDAO.findById(userId);
        if (user != null) {
            user.addDonation(amount);
            return userDAO.update(user);
        }
        return null;
    }

    /**
     * Initializes the service with sample data.
     */
    public void initWithSampleData() {
        ((UserDAOImpl) userDAO).initWithSampleData();
    }
}
