package nl.gjosse.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 */
public class SQLOperations {

	/**
	 * Method standardQuery.
	 * @param query String
	 * @param connection Connection
	 * @throws SQLException
	 */
	protected synchronized void standardQuery(String query, Connection connection) throws SQLException{
		Statement statement = connection.createStatement();
		statement.executeUpdate(query);
		statement.close();
	}

	/**
	 * Method sqlQuery.
	 * @param query String
	 * @param connection Connection
	 * @return ResultSet
	 * @throws SQLException
	 */
	protected synchronized ResultSet sqlQuery(String query, Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		return result;
	}

	/**
	 * Method checkTable.
	 * @param table String
	 * @param connection Connection
	 * @return boolean
	 * @throws SQLException
	 */
	protected synchronized boolean checkTable(String table, Connection connection) throws SQLException {
		DatabaseMetaData dbm;
		dbm = connection.getMetaData();
		ResultSet tables = dbm.getTables(null, null, table, null);
		return tables.next();
	}

}