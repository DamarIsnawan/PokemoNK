package pokemonk;

import java.sql.*;


/**
 * <strong> --------------------------------------------------------------- <br>
 * | ISYS6197 - BUSINESS APPLICATION DEVELOPMENT | <br>
 * --------------------------------------------------------------- <br>
 * </strong> <br>
 * DBConnect.java | This class is used for connection to MySQL database <br>
 * Copyright 2019 - Bina Nusantara University <br>
 * Software Laboratory Center | Laboratory Center Alam Sutera <br>
 * Kevin Surya Wahyudi (SW16-2), All rights reserved. <br>
 */

public final class DBConnect {

	private Statement state;
	private Connection con;
	private static DBConnect connect;

	private String USERNAME = "root";
	private String PASSWORD = "";
	private String DATABASE = "pokemonk";
	private String HOST = "localhost:3306";
	private String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

	/**
	 * Constructor for DBConnect class <br>
	 * This class is used singleton design pattern, so this class only have one
	 * instance
	 */

	public DBConnect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			state = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection failed, system terminated!");
			System.exit(0);
		}

	}

	/**
	 * This method is used for get instance from DBConnect class
	 * 
	 * @return DBConnect This returns instance from DBConnect class
	 */

	public static synchronized DBConnect getConnection() {
		/**
		 * If the connect is null then: - Create the instance from Connect class
		 * - Otherwise, just assign the previous instance of this class
		 */

		return connect = (connect == null) ? new DBConnect() : connect;
	}

	/**
	 * This method is used for SELECT SQL statements.
	 * 
	 * @param String
	 *            This is the query statement
	 * @return ResultSet This returns result data from the database
	 */

	public ResultSet executeQuery(String query) {
		ResultSet rs = null;

		try {
			rs = state.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	* This method is used for INSERT, UPDATE, or DELETE SQL statements.
	* @param String This is the query statement
	*/
	
	public void executeUpdate (String query) {
		try {
			state.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* This method is used for SELECT, INSERT, UPDATE, or DELETE SQL statements using prepare statement.
	* @param String This is the query statement
	*/
	
	public PreparedStatement prepareStatement (String query) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}

}