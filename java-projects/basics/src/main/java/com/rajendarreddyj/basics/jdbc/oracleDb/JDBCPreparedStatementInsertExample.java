package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/*
 * The “PreparedStatement” interface is extended “Statement”, with extra feature to send a pre-compiled SQL statement
 * with parameters. For create, insert, update or delete statement, uses “PreparedStatement.executeUpdate(sql)“; select
 * query, uses “PreparedStatement.executeQuery(sql)“. how to insert a record into table via JDBC PreparedStatement. To
 * issue an insert statement, calls the PreparedStatement.executeUpdate() method like this :
 * 
 * String insertTableSQL = "INSERT INTO DBUSER" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES" + "(?,?,?,?)";
 * PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL); preparedStatement.setInt(1, 11);
 * preparedStatement.setString(2, "rajendar"); preparedStatement.setString(3, "system");
 * preparedStatement.setTimestamp(4, getCurrentTimeStamp()); // execute insert SQL stetement preparedStatement
 * .executeUpdate();
 */
public class JDBCPreparedStatementInsertExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) {
        try {
            insertRecordIntoTable();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void insertRecordIntoTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO DBUSER1" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES" + "(?,?,?,?)";
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, 11);
            preparedStatement.setString(2, "rajendar");
            preparedStatement.setString(3, "system");
            preparedStatement.setTimestamp(4, getCurrentTimeStamp());
            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            logger.info("Record is inserted into DBUSER table!");
        } catch (SQLException e) {
            logger.info(e.getMessage());
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
            logger.info(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return dbConnection;
    }

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }
}