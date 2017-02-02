Oracle JDBC Drivers 11.2.0.4 Readme
===================================
Base Readme (11.2.0.1) @ http://download.oracle.com/otn/utilities_drivers/jdbc/112/Readme.txt

List of Fixes in 11.2.0.4
-------------------------
8870611	         ORA-600: [17281], [1001] when selecting ref cursors via JDBC thin and not 
                 explicitly closing then
10315994	 ResultSet.getRow() incorrectly returns rowcount instead of 0 after all rows are 
                 fetched (next() returns false).
11892137	 ORA-22925 when retrieving a LOB larger than 2gb via JDBC thin
11931187	 The 11.2 JDBC driver handles embedded CALL statements differently to 11.1
12596686	 JDBC thin app sends scale value of 0 or 9 for Timestamp binds causing many 
                 child cursors
12744662	 Connection/login failure with "IOError: undefined error" if os username contains 
                 specific multi-byte character
12744967	 Using newer time zones with JDBC fails with "The database session time zone is not 
                 supported"
12775220	 DatabaseMetaData.getColumns() returns unexpected values in COLUMN_SIZE and 
                 DECIMAL_DIGITS for INTEGER columns
13387375	 ORA-932 using JSP with ADT argument
13428912	 PreparedStatement executeBatch() fails with ORA-1460 while execute() works
13508485	 JDBC Statement.cancel can cause ORA-1013 from a Rollback
13583799	 JDBC class NTFConnection creates runaway threads
13627118	 Atlantic/Jan_Mayen mapping in ZONEIDMAP.java does not match the database value
13688142	 JDBC cannot connect to RAC using shared server when the DISPATCHERS parameter 
                 has POOL=ON and redirection occurs - superseded
13877559	 oracle.sql.TIMESTAMP.TimeZoneConvert() returns wrong results
14021941	 JDBC program shows high cursor version count and high bind mismatch count
14107475	 ORA-4043 using JDBC OCI; JDBC thin works
14223360	 oracle.jdbc.pool.OracleDataSourceFactory ignores connection properties when 
                 creating an OracleDataSource
14244395	 JDBC Thin ResultSet.isBeforeFirst() always returns true when Calling a PLSQL
14282782	 JDBC still uses the RULE hint for certain queries on ALL_SYNONYMS which degrades 
                 performance
14298523	 With the patch for bug 13688142 installed JDBC cannot connect using shared server 
                 when the DISPATCHERS parameter has POOL=ON and no redirection occurs
14354533	 Calling getMetaData() on a StructDescriptor.gives poor performance selecting 
                 from ALL_TYPE_ATTRS
14496772	 The JDBC SQL parser treats literals containing the word RETURN as though they 
                 were a RETURN[ING] ... INTO clause
14584969	 OutOfMemory exception when executing a plsql statement with 280+ VARCHAR2 
                 parameters in JDBC
16479818	 JDBC NTFConnection threads are stuck waiting and consuming CPU



