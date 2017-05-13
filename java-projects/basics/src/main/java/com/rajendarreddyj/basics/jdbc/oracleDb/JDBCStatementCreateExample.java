package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * The “Statement” interface is used to execute a simple SQL statement with no parameters. For create, insert, update or
 * delete statement, uses “Statement.executeUpdate(sql)“; select query, uses “Statement.executeQuery(sql)“. how to
 * create a table in database via JDBC statement. To issue a create statement, calls the Statement.execute() method like
 * this : Statement statement = dbConnection.createStatement(); // execute create SQL stetement
 * statement.execute(createTableSQL);
 */
import java.util.logging.Logger;

public class JDBCStatementCreateExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) {
        try {
            createDbUserTable();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void createDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String createTableSQL = "CREATE TABLE DBUSER(" + "USER_ID NUMBER(5) NOT NULL, " + "USERNAME VARCHAR(20) NOT NULL, "
                + "CREATED_BY VARCHAR(20) NOT NULL, " + "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) " + ")";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            logger.info(createTableSQL);
            // execute the SQL stetement
            statement.execute(createTableSQL);
            logger.info("Table \"dbuser\" is created!");
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