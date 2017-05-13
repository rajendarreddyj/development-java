package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/*
 * The “PreparedStatement” interface is extended “Statement”, with extra feature to send a pre-compiled SQL statement
 * with parameters. For create, insert, update or delete statement, uses “PreparedStatement.executeUpdate(sql)“; select
 * query, uses “PreparedStatement.executeQuery(sql)“. how to update a record in table via JDBC PreparedStatement. To
 * issue a update statement, calls the PreparedStatement.executeUpdate() method like this :
 * 
 * String updateTableSQL = "UPDATE DBUSER SET USERNAME = ? WHERE USER_ID = ?"; PreparedStatement preparedStatement =
 * dbConnection.prepareStatement(updateTableSQL); preparedStatement.setString(1, "rajendar_new_value");
 * preparedStatement.setInt(2, 1001); // execute insert SQL stetement preparedStatement .executeUpdate();
 */
public class JDBCPreparedStatementUpdateExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) {
        try {
            updateRecordToTable();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void updateRecordToTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateTableSQL = "UPDATE DBUSER SET USERNAME = ? " + " WHERE USER_ID = ?";
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, "rajendar_new_value");
            preparedStatement.setInt(2, 1001);
            // execute update SQL stetement
            preparedStatement.executeUpdate();
            logger.info("Record is updated to DBUSER table!");
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