package com.rajendarreddyj.basics.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Logger;

public class MySQLAccess {
    private static final Logger logger = Logger.getAnonymousLogger();
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @SuppressWarnings("deprecation")
    public void readDataBase() throws Exception {
        try {
            String userName = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost/table";
            try {
                // The newInstance() call is a work around for some
                // broken Java implementations
                // This will load the MySQL driver, each DB has its own driver
                Class.forName("org.gjt.mm.mysql.Driver").newInstance();
                logger.info("MySQL driver loaded");
            } catch (Exception E) {
                System.err.println("Unable to load driver.");
                E.printStackTrace();
            }
            // Setup the connection with the DB
            this.connect = DriverManager.getConnection(url, userName, password);
            if (!this.connect.isClosed()) {
                logger.info("Successfully connected to " + "MySQL server using TCP/IP...");
            }
            logger.info("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            e.printStackTrace();
        }
        // Statements allow to issue SQL queries to the database
        this.statement = this.connect.createStatement();
        // Result set get the result of the SQL query
        this.resultSet = this.statement.executeQuery("select * from table.COMMENTS");
        this.writeResultSet(this.resultSet);
        try {
            // PreparedStatements can use variables and are more efficient
            this.preparedStatement = this.connect.prepareStatement("insert into  table.COMMENTS values (default, ?, ?, ?, ? , ?, ?)");
            // "myuser, webpage, datum, summery, COMMENTS from table.COMMENTS");
            // Parameters start with 1
            this.preparedStatement.setString(1, "Test");
            this.preparedStatement.setString(2, "TestEmail");
            this.preparedStatement.setString(3, "TestWebpage");
            this.preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
            this.preparedStatement.setString(5, "TestSummary");
            this.preparedStatement.setString(6, "TestComment");
            this.preparedStatement.executeUpdate();
            this.preparedStatement = this.connect.prepareStatement("SELECT myuser, webpage, datum, summery, COMMENTS from table.COMMENTS");
            this.resultSet = this.preparedStatement.executeQuery();
            this.writeResultSet(this.resultSet);
            // Remove again the insert comment
            this.preparedStatement = this.connect.prepareStatement("delete from table.COMMENTS where myuser= ? ; ");
            this.preparedStatement.setString(1, "Test");
            this.preparedStatement.executeUpdate();
            this.resultSet = this.statement.executeQuery("select * from table.COMMENTS");
            this.writeMetaData(this.resultSet);
        } catch (Exception e) {
            throw e;
        } finally {
            this.close();
        }
    }

    private void writeMetaData(final ResultSet resultSet) throws SQLException {
        // Now get some metadata from the database
        // Result set get the result of the SQL query
        logger.info("The columns in the table are: ");
        logger.info("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            logger.info("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(final ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String user = resultSet.getString("myuser");
            String website = resultSet.getString("webpage");
            String summery = resultSet.getString("summery");
            Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            logger.info("User: " + user);
            logger.info("Website: " + website);
            logger.info("Summery: " + summery);
            logger.info("Date: " + date);
            logger.info("Comment: " + comment);
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (this.resultSet != null) {
                this.resultSet.close();
            }
            if (this.statement != null) {
                this.statement.close();
            }
            if (this.connect != null) {
                this.connect.close();
            }
        } catch (Exception e) {
        }
    }
}
