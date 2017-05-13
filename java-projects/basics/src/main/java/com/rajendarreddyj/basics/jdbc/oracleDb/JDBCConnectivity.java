package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/*
 * how to connect to Oracle database via JDBC driver. 1. Download MySQL JDBC Driver
 * 
 * Get Oracle JDBC driver here – ojdbcxxx.jar 2. Java JDBC connection example
 * 
 * Code snippets to use JDBC to connect a Oracle database.
 * 
 * Class.forName("oracle.jdbc.driver.OracleDriver"); Connection connection = null; connection =
 * DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:test","username","password"); connection.close();
 */
public class JDBCConnectivity {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] argv) {
        logger.info("-------- Oracle JDBC Connection Testing ------");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            logger.info("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;
        }
        logger.info("Oracle JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521", "rajendar", "rajendar");
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