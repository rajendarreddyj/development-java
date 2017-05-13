package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/*
 * The “Statement” interface is used to execute a simple SQL statement with no parameters. For create, insert, update or
 * delete statement, uses “Statement.executeUpdate(sql)“; select query, uses “Statement.executeQuery(sql)“. how to
 * update a record in a table via JDBC statement. To issue a update statement, calls the Statement.executeUpdate()
 * method like this :
 * 
 * Statement statement = dbConnection.createStatement(); // execute the update SQL statement
 * statement.executeUpdate(updateTableSQL);
 */
public class JDBCStatementUpdateExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) {
        try {
            updateRecordIntoDbUserTable();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void updateRecordIntoDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String updateTableSQL = "UPDATE DBUSER" + " SET USERNAME = 'rajendar_new' " + " WHERE USER_ID = 1";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            logger.info(updateTableSQL);
            // execute update SQL stetement
            statement.execute(updateTableSQL);
            logger.info("Record is updated to DBUSER table!");
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
}