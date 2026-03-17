package com.edutech.progressive.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConnectionManager {

    private static final Properties properties = new Properties();
    private static volatile boolean loaded = false;

    private DatabaseConnectionManager() {}

    private static void loadProperties() {
        if (loaded) return;
        synchronized (DatabaseConnectionManager.class) {
            if (loaded) return;
            try (InputStream in = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties")) {
                if (in == null) {
                    throw new IllegalStateException("application.properties not found on classpath");
                }
                properties.load(in);

                // Optionally load JDBC driver if provided
                String driver = properties.getProperty("db.driver");
                if (driver != null && !driver.isBlank()) {
                    Class.forName(driver);
                }
                loaded = true;
            } catch (IOException e) {
                throw new RuntimeException("Failed to load database configuration", e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("JDBC Driver class not found", e);
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        loadProperties();
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.username");
        String pass = properties.getProperty("db.password");

        if (url == null || user == null) {
            throw new SQLException("Database URL/username not configured in application.properties");
        }
        return DriverManager.getConnection(url, user, pass);
    }
}