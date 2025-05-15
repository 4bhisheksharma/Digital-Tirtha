package org.example.digitaltirtha.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.DBUtil;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Configuration class for database connectivity.
 * Sets up a connection pool using HikariCP and initializes the database schema.
 */
public class DatabaseConfig {
    private static HikariDataSource dataSource;
    private static JdbcTemplate jdbcTemplate;

    static {
        initializeDataSource();
        initializeSchema();
    }

    /**
     * Initializes the data source with connection pool settings.
     */
    private static void initializeDataSource() {
        try {
            // Load properties from db.properties file
            InputStream is = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties");
            if (is == null) {
                throw new RuntimeException("Could not find db.properties in resources");
            }

            Properties prop = new Properties();
            prop.load(is);

            // Verify driver class is available before configuring HikariCP
            String driverClassName = prop.getProperty("db.driver");
            try {
                Class.forName(driverClassName);
                System.out.println("Successfully loaded JDBC driver: " + driverClassName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Database driver class not found: " + driverClassName + 
                    ". Please ensure the appropriate JDBC driver is in the classpath.", e);
            }

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(prop.getProperty("db.url"));
            config.setUsername(prop.getProperty("db.username"));
            config.setPassword(prop.getProperty("db.password"));
            config.setDriverClassName(driverClassName);
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(5);
            config.setIdleTimeout(30000);
            config.setPoolName("DigitalTirthaPool");

            dataSource = new HikariDataSource(config);
            jdbcTemplate = new JdbcTemplate(dataSource);

            System.out.println("Database connection initialized with URL: " + prop.getProperty("db.url"));
        } catch (Exception e) {
            System.err.println("Error initializing data source: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    /**
     * Initializes the database schema by executing the SQL script.
     */
    private static void initializeSchema() {
        if (dataSource == null) {
            System.err.println("Cannot initialize schema: data source is null");
            return;
        }

        try {
            // Load schema.sql from resources
            InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream("schema.sql");
            if (inputStream == null) {
                throw new RuntimeException("Could not find schema.sql in resources");
            }

            String schema = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));

            // Execute the schema SQL
            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement()) {
                // Split the schema into individual statements
                String[] statements = schema.split(";");
                int executedStatements = 0;

                for (String statement : statements) {
                    if (!statement.trim().isEmpty()) {
                        try {
                            stmt.execute(statement);
                            executedStatements++;
                        } catch (SQLException e) {
                            System.err.println("Error executing SQL statement: " + statement.trim());
                            throw e;
                        }
                    }
                }

                System.out.println("Database schema initialized successfully. Executed " + executedStatements + " statements.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to initialize database schema: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database schema", e);
        } catch (Exception e) {
            System.err.println("Unexpected error during schema initialization: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Unexpected error during schema initialization", e);
        }
    }

    /**
     * Gets the configured data source.
     * 
     * @return The data source
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Gets the configured JDBC template.
     * 
     * @return The JDBC template
     */
    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
