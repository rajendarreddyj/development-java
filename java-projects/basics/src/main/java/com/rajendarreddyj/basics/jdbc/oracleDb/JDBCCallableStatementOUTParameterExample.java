package com.rajendarreddyj.basics.jdbc.oracleDb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/*
 * For stored procedure returns OUT parameters, it must be
 * 
 * Registered via JDBC CallableStatement.registerOutParameter(index,sqlType). Get it back via
 * CallableStatement.getDataType(index).
 * 
 * See code snippets //getDBUSERByUserId is a stored procedure String getDBUSERByUserIdSql =
 * "{call getDBUSERByUserId(?,?,?,?)}"; callableStatement = dbConnection.prepareCall(getDBUSERByUserIdSql);
 * callableStatement.setInt(1, 10); callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
 * callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR); callableStatement.registerOutParameter(4,
 * java.sql.Types.DATE); //execute getDBUSERByUserId store procedure callableStatement.executeUpdate(); String userName
 * = callableStatement.getString(2); String createdBy = callableStatement.getString(3); Date createdDate =
 * callableStatement.getDate(4); See a full JDBC CallableStatement example for OUT parameter. 1. Stored Procedure A
 * stored procedure in Oracle database, with IN and OUT parameters. Later, calls it via JDBC. CREATE OR REPLACE
 * PROCEDURE getDBUSERByUserId( p_userid IN DBUSER.USER_ID%TYPE, o_username OUT DBUSER.USERNAME%TYPE, o_createdby OUT
 * DBUSER.CREATED_BY%TYPE, o_date OUT DBUSER.CREATED_DATE%TYPE) IS BEGIN SELECT USERNAME , CREATED_BY, CREATED_DATE INTO
 * o_username, o_createdby, o_date FROM DBUSER WHERE USER_ID = p_userid; END; 2. Calls Stored Procedure via
 * CallableStatement JDBC example to call a stored procedure via CallableStatement.
 */
public class JDBCCallableStatementOUTParameterExample {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521";
    private static final String DB_USER = "rajendar";
    private static final String DB_PASSWORD = "rajendar";

    public static void main(final String[] argv) {
        try {
            callOracleStoredProcOUTParameter();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private static void callOracleStoredProcOUTParameter() throws SQLException {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
        String getDBUSERByUserIdSql = "{call getDBUSERByUserId(?,?,?,?)}";
        try {
            dbConnection = getDBConnection();
            callableStatement = dbConnection.prepareCall(getDBUSERByUserIdSql);
            callableStatement.setInt(1, 101);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(4, java.sql.Types.DATE);
            // execute getDBUSERByUserId store procedure
            callableStatement.executeUpdate();
            String userName = callableStatement.getString(2);
            String createdBy = callableStatement.getString(3);
            Date createdDate = callableStatement.getDate(4);
            logger.info("UserName : " + userName);
            logger.info("CreatedBy : " + createdBy);
            logger.info("CreatedDate : " + createdDate);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
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