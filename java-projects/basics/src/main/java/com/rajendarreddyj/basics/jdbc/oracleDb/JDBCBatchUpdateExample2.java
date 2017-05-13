package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/*
 * The “PreparedStatement” interface is extended “Statement”, with extra feature to send a pre-compiled SQL statement
 * with parameters. For create, insert, update or delete statement, uses “PreparedStatement.executeUpdate(sql)“; how to
 * insert few records in batch process, via JDBC PreparedStatement. dbConnection.setAutoCommit(false);//commit
 * trasaction manually String insertTableSQL = "INSERT INTO DBUSER" +
 * "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES" + "(?,?,?,?)"; PreparedStatement =
 * dbConnection.prepareStatement(insertTableSQL); preparedStatement.setInt(1, 101); preparedStatement.setString(2,
 * "rajendar101"); preparedStatement.setString(3, "system"); preparedStatement.setTimestamp(4, getCurrentTimeStamp());
 * preparedStatement.addBatch(); preparedStatement.setInt(1, 102); preparedStatement.setString(2, "rajendar102");
 * preparedStatement.setString(3, "system"); preparedStatement.setTimestamp(4, getCurrentTimeStamp());
 * preparedStatement.addBatch(); preparedStatement.executeBatch(); dbConnection.commit(); Batch Update is not limit to
 * Insert statement, it’s apply for Update and Delete statement as well.
 */
public class JDBCBatchUpdateExample2 {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) {
        try {
            batchInsertRecordsIntoTable();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void batchInsertRecordsIntoTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO DBUSER1" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES" + "(?,?,?,?)";
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            dbConnection.setAutoCommit(false);
            preparedStatement.setInt(1, 101);
            preparedStatement.setString(2, "rajendar101");
            preparedStatement.setString(3, "system");
            preparedStatement.setTimestamp(4, getCurrentTimeStamp());
            preparedStatement.addBatch();
            preparedStatement.setInt(1, 102);
            preparedStatement.setString(2, "rajendar102");
            preparedStatement.setString(3, "system");
            preparedStatement.setTimestamp(4, getCurrentTimeStamp());
            preparedStatement.addBatch();
            preparedStatement.setInt(1, 103);
            preparedStatement.setString(2, "rajendar103");
            preparedStatement.setString(3, "system");
            preparedStatement.setTimestamp(4, getCurrentTimeStamp());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            dbConnection.commit();
            logger.info("Record is inserted into DBUSER table!");
        } catch (SQLException e) {
            logger.info(e.getMessage());
            dbConnection.rollback();
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