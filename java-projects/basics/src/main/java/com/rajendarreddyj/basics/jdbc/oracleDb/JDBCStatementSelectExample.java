package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/*
 * The “Statement” interface is used to execute a simple SQL statement with no parameters. For create, insert, update or
 * delete statement, uses “Statement.executeUpdate(sql)“; select query, uses “Statement.executeQuery(sql)“. how to
 * select the entire records from table via JDBC statement, and display all the records via a ResultSet object. To issue
 * a select query, calls the Statement.executeQuery method like this :
 * 
 * String selectTableSQL = "SELECT USER_ID, USERNAME from DBUSER"; Statement statement = dbConnection.createStatement();
 * ResultSet rs = statement.executeQuery(selectTableSQL); while (rs.next()) { String userid = rs.getString("USER_ID");
 * String username = rs.getString("USERNAME"); }
 */
public class JDBCStatementSelectExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] args) {
        try {
            selectRecordsFromDbUserTable();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void selectRecordsFromDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = "SELECT USER_ID, USERNAME from DBUSER";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            logger.info(selectTableSQL);
            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String userid = rs.getString("USER_ID");
                String username = rs.getString("USERNAME");
                logger.info("userid : " + userid);
                logger.info("username : " + username);
            }
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