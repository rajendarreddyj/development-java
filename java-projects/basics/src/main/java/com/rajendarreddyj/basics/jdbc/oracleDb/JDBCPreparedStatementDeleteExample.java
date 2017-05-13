package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/*
 * The “PreparedStatement” interface is extended “Statement”, with extra feature to send a pre-compiled SQL statement
 * with parameters. For create, insert, update or delete statement, uses “PreparedStatement.executeUpdate(sql)“; how to
 * delete a record from a table via JDBC PreparedStatement. To issue a delete statement, calls the
 * PreparedStatement.executeUpdate() method like this :
 * 
 * String deleteSQL = "DELETE DBUSER WHERE USER_ID = ?"; PreparedStatement preparedStatement =
 * dbConnection.prepareStatement(deleteSQL); preparedStatement.setInt(1, 1001); // execute delete SQL stetement
 * preparedStatement.executeUpdate();
 */
public class JDBCPreparedStatementDeleteExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) {
        try {
            deleteRecordFromTable();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void deleteRecordFromTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String deleteSQL = "DELETE DBUSER1 WHERE USER_ID = ?";
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, 1001);
            // execute delete SQL stetement
            preparedStatement.executeUpdate();
            logger.info("Record is deleted!");
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
}