package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/*
 * The “Statement” interface is used to execute a simple SQL statement with no parameters. For create, insert, update or
 * delete statement, uses “Statement.executeUpdate(sql)“; select query, uses “Statement.executeQuery(sql)“. how to
 * insert few records in batch process, via JDBC Statement. dbConnection.setAutoCommit(false); statement =
 * dbConnection.createStatement(); statement.addBatch(insertTableSQL1); statement.addBatch(insertTableSQL2);
 * statement.addBatch(insertTableSQL3); statement.executeBatch(); dbConnection.commit(); Batch Update is not limit to
 * Insert statement, it’s apply for Update and Delete statement as well. The above batch update is same with normal
 * executeUpdate() method like this : statement.executeUpdate(insertTableSQL1);
 * statement.executeUpdate(insertTableSQL2); statement.executeUpdate(insertTableSQL3); But batch update has performance
 * benefit if you want to insert many records, because executeBatch() reduces the number of JDBC calls to database.
 */
import java.util.logging.Logger;

public class JDBCBatchUpdateExample1 {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void main(final String[] argv) {
        try {
            batchInsertRecordsIntoTable();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void batchInsertRecordsIntoTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String insertTableSQL1 = "INSERT INTO DBUSER" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES" + "(101,'rajendar101','system', "
                + "to_date('" + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";
        String insertTableSQL2 = "INSERT INTO DBUSER" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES" + "(102,'rajendar102','system', "
                + "to_date('" + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";
        String insertTableSQL3 = "INSERT INTO DBUSER" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES" + "(103,'rajendar103','system', "
                + "to_date('" + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            dbConnection.setAutoCommit(false);
            statement.addBatch(insertTableSQL1);
            statement.addBatch(insertTableSQL2);
            statement.addBatch(insertTableSQL3);
            statement.executeBatch();
            dbConnection.commit();
            logger.info("Records are inserted into DBUSER table!");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
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

    private static String getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return dateFormat.format(today.getTime());
    }
}