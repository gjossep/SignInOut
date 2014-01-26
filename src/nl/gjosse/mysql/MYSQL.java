package nl.gjosse.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nl.gjosse.gui.Student;



public class MYSQL {
	
    private static Connection con = null;
    private static Statement st = null;
    private static ResultSet rs = null;

    static String url = "jdbc:mysql://db4free.net:3306";
    static String user = "ashcomm";
    static String password = "trojan01";

	public static void start() {
		try {
			tryConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void tryConnection() throws SQLException {
		con = DriverManager.getConnection(url, user, password);
		System.out.println(con.isClosed());
	}

	public static void addStudent(Student student) {
		
	}

}
