package com.rajendarreddyj.basics.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * how to connect to MySQL database via JDBC driver. 1. Download MySQL JDBC Driver
 * 
 * Get a MySQL JDBC driver from here -MySQL JDBC Driver Download Here 2. Java JDBC connection example
 * 
 * Code snippets to use JDBC to connect a MySQL database.
 * 
 * Class.forName("com.mysql.jdbc.Driver"); Connection connection = null; connection = DriverManager.getConnection(
 * "jdbc:mysql://hostname:port/dbname","username", "password"); connection.close();
 */

public class JDBCConnectivity {

    public static void main(final String[] argv) {

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/table", "root", "toor");

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
