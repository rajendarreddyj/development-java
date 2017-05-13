package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/*
 * JDBC Transaction let you control how and when a transaction should commit into database. //transaction block start
 * //SQL insert statement //SQL update statement //SQL delete statement //transaction block end
 * 
 * In simple, JDBC transaction make sure SQL statements within a transaction block are all executed successful, if
 * either one of the SQL statement within transaction block is failed, abort and rollback everything within the
 * transaction block.
 * 
 * See below two examples to understand how JDBC transaction works. 1. Without JDBC Transaction
 * 
 * By default, data will be committed into database when executeUpdate() is called. String insertTableSQL =
 * "INSERT INTO DBUSER" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES" + "(?,?,?,?)"; String updateTableSQL =
 * "UPDATE DBUSER SET USERNAME =? " + "WHERE USER_ID = ?"; preparedStatementInsert =
 * dbConnection.prepareStatement(insertTableSQL); preparedStatementInsert.setInt(1, 999);
 * preparedStatementInsert.setString(2, "mkyong101"); preparedStatementInsert.setString(3, "system");
 * preparedStatementInsert.setTimestamp(4, getCurrentTimeStamp()); preparedStatementInsert.executeUpdate(); //data
 * COMMITTED into database. preparedStatementUpdate = dbConnection.prepareStatement(updateTableSQL);
 * preparedStatementUpdate.setString(1, "A very very long string caused DATABASE ERROR");
 * preparedStatementUpdate.setInt(2, 999); preparedStatementUpdate.executeUpdate(); //Error, value too big, ignore this
 * update statement, //but user_id=999 is inserted When this code is executed, the USER_ID = ’999′ is inserted but the
 * username is not update.
 * 
 * 2. With JDBC Transaction
 * 
 * To put this in a transaction, you can use dbConnection.setAutoCommit(false); to start a transaction block.
 * dbConnection.commit(); to end a transaction block. See code snippets : dbConnection.setAutoCommit(false);
 * //transaction block start String insertTableSQL = "INSERT INTO DBUSER" +
 * "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES" + "(?,?,?,?)"; String updateTableSQL =
 * "UPDATE DBUSER SET USERNAME =? " + "WHERE USER_ID = ?"; preparedStatementInsert =
 * dbConnection.prepareStatement(insertTableSQL); preparedStatementInsert.setInt(1, 999);
 * preparedStatementInsert.setString(2, "mkyong101"); preparedStatementInsert.setString(3, "system");
 * preparedStatementInsert.setTimestamp(4, getCurrentTimeStamp()); preparedStatementInsert.executeUpdate(); //data IS
 * NOT commit yet preparedStatementUpdate = dbConnection.prepareStatement(updateTableSQL);
 * preparedStatementUpdate.setString(1, "A very very long string caused DATABASE ERROR");
 * preparedStatementUpdate.setInt(2, 999); preparedStatementUpdate.executeUpdate(); //Error, rollback, including the
 * first insert statement. dbConnection.commit(); //transaction block end When this code is executed, update statement
 * is hits error, and make both insert and update statements rollback together.
 */
public class JDBCTransactionExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatementInsert = null;
        PreparedStatement preparedStatementUpdate = null;
        String insertTableSQL = "INSERT INTO DBUSER" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES" + "(?,?,?,?)";
        String updateTableSQL = "UPDATE DBUSER SET USERNAME =? " + "WHERE USER_ID = ?";
        try {
            dbConnection = getDBConnection();
            dbConnection.setAutoCommit(false);
            preparedStatementInsert = dbConnection.prepareStatement(insertTableSQL);
            preparedStatementInsert.setInt(1, 999);
            preparedStatementInsert.setString(2, "rajendar101");
            preparedStatementInsert.setString(3, "system");
            preparedStatementInsert.setTimestamp(4, getCurrentTimeStamp());
            preparedStatementInsert.executeUpdate();
            preparedStatementUpdate = dbConnection.prepareStatement(updateTableSQL);
            // preparedStatementUpdate.setString(1,
            // "A very very long string caused db error");
            preparedStatementUpdate.setString(1, "new string");
            preparedStatementUpdate.setInt(2, 999);
            preparedStatementUpdate.executeUpdate();
            dbConnection.commit();
            logger.info("Done!");
        } catch (SQLException e) {
            logger.info(e.getMessage());
            dbConnection.rollback();
        } finally {
            if (preparedStatementInsert != null) {
                preparedStatementInsert.close();
            }
            if (preparedStatementUpdate != null) {
                preparedStatementUpdate.close();
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