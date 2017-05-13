package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/*
 * The “PreparedStatement” interface is extended “Statement”, with extra feature to send a pre-compiled SQL statement
 * with parameters. For create, insert, update or delete statement, uses “PreparedStatement.executeUpdate(sql)“; how to
 * select records from table via JDBC PreparedStatement, and display the records via a ResultSet object. To issue a
 * select query, calls the PreparedStatement.executeQuery() method like this :
 * 
 * String selectSQL = "SELECT USER_ID, USERNAME FROM DBUSER WHERE USER_ID = ?"; PreparedStatement preparedStatement =
 * dbConnection.prepareStatement(selectSQL); preparedStatement.setInt(1, 1001); ResultSet rs =
 * preparedStatement.executeQuery(selectSQL ); while (rs.next()) { String userid = rs.getString("USER_ID"); String
 * username = rs.getString("USERNAME"); }
 */
public class JDBCPreparedStatementSelectExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) {
        try {
            selectRecordsFromTable();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void selectRecordsFromTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT USER_ID, USERNAME FROM DBUSER1 WHERE USER_ID = ?";
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, 101);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userid = rs.getString("USER_ID");
                String username = rs.getString("USERNAME");
                logger.info("userid : " + userid);
                logger.info("username : " + username);
            }
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