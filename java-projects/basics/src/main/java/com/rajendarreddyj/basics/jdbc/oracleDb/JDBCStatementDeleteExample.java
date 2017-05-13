package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/*
 * The “Statement” interface is used to execute a simple SQL statement with no parameters. For create, insert, update or
 * delete statement, uses “Statement.executeUpdate(sql)“; select query, uses “Statement.executeQuery(sql)“. how to
 * delete a record from a table via JDBC statement. To issue a delete statement, calls the Statement.executeUpdate()
 * method like this : Statement statement = dbConnection.createStatement(); // execute the delete SQL stetement
 * statement.executeUpdate(deleteTableSQL);
 */
public class JDBCStatementDeleteExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) {
        try {
            deleteRecordFromDbUserTable();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void deleteRecordFromDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String deleteTableSQL = "DELETE DBUSER WHERE USER_ID = 1";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            logger.info(deleteTableSQL);
            // execute delete SQL stetement
            statement.execute(deleteTableSQL);
            logger.info("Record is deleted from DBUSER table!");
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