package nl.gjosse.mysql;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Made by Adamki11s
 * No credit to Gjosse
 */

/**
 */
public class SyncSQL extends SQLOperations {

	private String host, database, username, password;
	private SCHEMA schema;
	private Connection connection;
	private File databaseFile;

	/**
	 * Constructor for SyncSQL.
	 * @param host String
	 * @param database String
	 * @param username String
	 * @param password String
	 */
	public SyncSQL(String host, String database, String username, String password) {
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
		this.schema = SCHEMA.MySQL;
	}

	/**
	 * Constructor for SyncSQL.
	 * @param databaseFile File
	 */
	public SyncSQL(File databaseFile) {
		this.databaseFile = databaseFile;
		this.schema = SCHEMA.SQLite;
	}

	/**
	 * Reopens the SQL connection if it is closed. This is invoked upon every
	 * query.
	
	
	
	 * @throws ClassNotFoundException  * @throws SQLException  * @throws IOException  */
	public void refreshConnection() throws ClassNotFoundException, SQLException, IOException {
		if (connection == null) {
			initialise();
		}
	}

	/**
	 * Manually close the connection.
	
	 * @throws SQLException  */
	public void closeConnection() throws SQLException {
			this.connection.close();
	}

	/**
	 * Initialise a new connection. This will automatically create the database
	 * file if you are using SQLite and it doesn't already exist.
	 * 
	
	
	
	
	 * @return boolean
	 * @throws ClassNotFoundException  * @throws SQLException  * @throws IOException  */
	public boolean initialise() throws ClassNotFoundException, SQLException, IOException {
		if (schema == SCHEMA.MySQL) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
				return true;
		} else {
			if (!databaseFile.exists()) {
					databaseFile.createNewFile();
			}
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:" + this.databaseFile.getAbsolutePath());
				return true;
		}
	}

	/**
	 * Any query which does not return a ResultSet object. Such as : INSERT,
	 * UPDATE, CREATE TABLE...
	 * 
	 * @param query
	
	
	 * @throws SQLException
	 * @throws ClassNotFoundException  * @throws IOException  */
	public void standardQuery(String query) throws SQLException, ClassNotFoundException, IOException {
		this.refreshConnection();
		super.standardQuery(query, this.connection);
	}

	/**
	 * Check whether a field/entry exists in a database.
	 * @param query
	
	
	
	
	 * @return Whether or not a result has been found in the query. * @throws SQLException * @throws ClassNotFoundException  * @throws IOException  */
	public boolean existanceQuery(String query) throws SQLException, ClassNotFoundException, IOException {
		this.refreshConnection();
		return super.sqlQuery(query, this.connection).next();
	}

	/**
	 * Any query which returns a ResultSet object. Such as : SELECT Remember to
	 * close the ResultSet object after you are done with it to free up
	 * resources immediately. ----- ResultSet set =
	 * sqlQuery("SELECT * FROM sometable;"); set.doSomething(); set.close();
	 * -----
	 * 
	 * @param query
	
	
	
	 * @return ResultSet * @throws SQLException
	 * @throws ClassNotFoundException  * @throws IOException  */
	public ResultSet sqlQuery(String query) throws SQLException, ClassNotFoundException, IOException {
		this.refreshConnection();
		return super.sqlQuery(query, this.connection);
	}

	/**
	 * Check whether the table name exists.
	 * 
	 * @param table
	
	
	
	 * @return boolean
	 * @throws SQLException
	 * @throws ClassNotFoundException  * @throws IOException  */
	public boolean doesTableExist(String table) throws SQLException, ClassNotFoundException, IOException {
		this.refreshConnection();
		return super.checkTable(table, this.connection);
	}

}