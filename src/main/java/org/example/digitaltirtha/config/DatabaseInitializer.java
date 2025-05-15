package org.example.digitaltirtha.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.digitaltirtha.dao.impl.MediaContributionDAOImpl;
import org.example.digitaltirtha.model.User;
import org.example.digitaltirtha.service.HeritageSiteService;
import org.example.digitaltirtha.service.UserService;

import java.util.List;

/**
 * Database initializer that runs when the application starts.
 * Initializes the database connection and loads sample data.
 */
@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing database...");

        try {
            // Verify database connection is available
            if (DatabaseConfig.getDataSource() == null) {
                throw new RuntimeException("Database connection not initialized. Check database configuration and JDBC driver.");
            }

            try {
                // Test database connection
                DatabaseConfig.getDataSource().getConnection().close();
                System.out.println("Database connection test successful");
            } catch (Exception e) {
                throw new RuntimeException("Failed to connect to database. Please check database server is running and configuration is correct.", e);
            }

            // Initialize sample data
            try {
                HeritageSiteService heritageSiteService = new HeritageSiteService();
                heritageSiteService.initWithSampleData();

                // Fetch user data and ensure admin user exists
                List<User> users = DatabaseDataFetcher.fetchAllUsers();
                System.out.println("Found " + users.size() + " existing users in the database");

                // Ensure admin user exists
                User adminUser = DatabaseDataFetcher.ensureAdminUserExists();
                if (adminUser != null) {
                    System.out.println("Admin user is available: " + adminUser.getUsername() + " (ID: " + adminUser.getId() + ")");
                }

                // If no users exist yet, initialize with sample data
                if (users.isEmpty()) {
                    UserService userService = new UserService();
                    userService.initWithSampleData();
                    System.out.println("Initialized database with sample user data");
                }

                // Initialize media contributions
                MediaContributionDAOImpl mediaContributionDAO = new MediaContributionDAOImpl();
                mediaContributionDAO.initWithSampleData();

                System.out.println("Database initialization completed successfully");
            } catch (Exception e) {
                System.err.println("Error initializing sample data: " + e.getMessage());
                e.printStackTrace();
                // Continue with application startup even if sample data fails
            }
        } catch (Exception e) {
            System.err.println("Critical error initializing database: " + e.getMessage());
            e.printStackTrace();
            // Consider adding code to notify administrators or fail application startup
            throw new RuntimeException("Application failed to start due to database initialization error", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup resources if needed
        System.out.println("Application shutting down, cleaning up database resources...");
    }
}
