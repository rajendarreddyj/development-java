package com.rajendarreddyj.basics.jdbc.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/*
 * how to connect to PostgreSQL database with JDBC driver. 1. Download PostgreSQL JDBC Driver
 * 
 * Get a PostgreSQL JDBC driver at this URL : http://jdbc.postgresql.org/download.html 2. Java JDBC connection example
 * 
 * Code snippets to use JDBC to connect a PostgreSQL database
 * 
 * Class.forName("org.postgresql.Driver"); Connection connection = null; connection = DriverManager.getConnection(
 * "jdbc:postgresql://hostname:port/dbname","username", "password"); connection.close();
 */
public class JDBCConnectivity {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] argv) {
        logger.info("-------- PostgreSQL " + "JDBC Connection Testing ------------");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.info("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
            return;
        }
        logger.info("PostgreSQL JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testdb", "root", "123456");
        } catch (SQLException e) {
            logger.info("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            logger.info("You made it, take control your database now!");
        } else {
            logger.info("Failed to make connection!");
        }
    }
}
