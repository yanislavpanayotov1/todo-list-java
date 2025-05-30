package com.todoapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:h2:mem:tododb;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load H2 driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private static void initializeDatabase() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS tasks (
                id IDENTITY PRIMARY KEY,
                title VARCHAR(255) NOT NULL,
                description TEXT,
                due_date TIMESTAMP,
                created_at TIMESTAMP NOT NULL,
                completed BOOLEAN DEFAULT FALSE
            )
            """;

        try (Connection conn = getConnection()) {
            conn.createStatement().execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }
} 