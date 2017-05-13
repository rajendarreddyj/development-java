package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

/*
 * For Oracle stored procedure returns CURSOR parameter, you can 1.Registered via JDBC
 * CallableStatement.registerOutParameter(index,OracleTypes.CURSOR). 2.Get it back via
 * callableStatement.getObject(index). See code snippets //getDBUSERCursor is a stored procedure String
 * getDBUSERCursorSql = "{call getDBUSERCursor(?,?)}"; callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);
 * callableStatement.setString(1, "mkyong"); callableStatement.registerOutParameter(2, OracleTypes.CURSOR); //execute
 * getDBUSERCursor store procedure callableStatement.executeUpdate(); //get cursor and cast it to ResultSet rs =
 * (ResultSet) callableStatement.getObject(2); //loop it like normal while (rs.next()) { String userid =
 * rs.getString("USER_ID"); String userName = rs.getString("USERNAME"); } JDBC CallableStatement CURSOR Example See a
 * full JDBC CallableStatement example for OUT CURSOR parameter. 1. Stored Procedure A Oracle stored procedure, with one
 * IN and one OUT CURSOR parameter. Later, calls it via JDBC. CREATE OR REPLACE PROCEDURE getDBUSERCursor( p_username IN
 * DBUSER.USERNAME%TYPE, c_dbuser OUT SYS_REFCURSOR) IS BEGIN OPEN c_dbuser FOR SELECT * FROM DBUSER WHERE USERNAME LIKE
 * p_username || '%'; END; 2. Calls Stored Procedure via CallableStatement JDBC example to call above stored procedure,
 * cast the returned CURSOR to ResultSet and loop through the records sequentially.
 */
public class JDBCCallableStatementCURSORExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) {
        try {
            callOracleStoredProcCURSORParameter();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void callOracleStoredProcCURSORParameter() throws SQLException {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String getDBUSERCursorSql = "{call getDBUSERCursor(?,?)}";
        try {
            dbConnection = getDBConnection();
            callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);
            callableStatement.setString(1, "rajendar1");
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            // execute getDBUSERCursor store procedure
            callableStatement.executeUpdate();
            // get cursor and cast it to ResultSet
            rs = (ResultSet) callableStatement.getObject(2);
            while (rs.next()) {
                String userid = rs.getString("USER_ID");
                String userName = rs.getString("USERNAME");
                String createdBy = rs.getString("CREATED_BY");
                String createdDate = rs.getString("CREATED_DATE");
                logger.info("UserName : " + userid);
                logger.info("UserName : " + userName);
                logger.info("CreatedBy : " + createdBy);
                logger.info("CreatedDate : " + createdDate);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (callableStatement != null) {
                callableStatement.close();
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