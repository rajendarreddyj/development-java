package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public static void main(final String[] argv) {

        System.out.println("-------- Oracle JDBC Connection Testing ------");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;
        }
        System.out.println("Oracle JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521", "rajendar", "rajendar");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}