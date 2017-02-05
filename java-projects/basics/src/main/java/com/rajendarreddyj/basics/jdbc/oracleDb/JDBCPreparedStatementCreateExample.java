package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * The “PreparedStatement” interface is extended “Statement”, with extra feature to send a pre-compiled SQL statement
 * with parameters. For create, insert, update or delete statement, uses “PreparedStatement.executeUpdate(sql)“; select
 * query, uses “PreparedStatement.executeQuery(sql)“. how to create a table in database via JDBC PrepareStatement. To
 * issue a create statement, calls the PrepareStatement.executeUpdate() method like this :
 * 
 * PreparedStatement preparedStatement = dbConnection.prepareStatement(createTableSQL); // EXECUTE CREATE SQL stetement
 * preparedStatement.executeUpdate();
 */
public class JDBCPreparedStatementCreateExample {
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) {
        try {
            createTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String createTableSQL = "CREATE TABLE DBUSER1(" + "USER_ID NUMBER(5) NOT NULL, " + "USERNAME VARCHAR(20) NOT NULL, "
                + "CREATED_BY VARCHAR(20) NOT NULL, " + "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) " + ")";
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(createTableSQL);
            System.out.println(createTableSQL);
            // execute create SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Table \"dbuser\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}