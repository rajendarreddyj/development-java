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
 * insert a record into table via JDBC statement. To issue a insert statement, calls the Statement.executeUpdate()
 * method like this : Statement statement = dbConnection.createStatement(); // execute the insert SQL stetement
 * statement.executeUpdate(insertTableSQL);
 */
import java.util.logging.Logger;

public class JDBCStatementInsertExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void main(final String[] args) {
        try {
            insertRecordIntoDbUserTable();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void insertRecordIntoDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String insertTableSQL = "INSERT INTO DBUSER" + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES" + "(1,'rajendar','system', " + "to_date('"
                + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            logger.info(insertTableSQL);
            // execute insert SQL stetement
            statement.executeUpdate(insertTableSQL);
            logger.info("Record is inserted into DBUSER table!");
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