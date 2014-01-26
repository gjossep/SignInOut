package nl.gjosse.mysql;


import java.io.IOException;
import java.sql.SQLException;

import nl.gjosse.core.SignInOut;
import nl.gjosse.gui.Student;



public class MYSQL {

    static String URL = "db4free.net:3306";
    static String Username = "ashcomm";
    static String Password = "trojan01";
    
	public static SyncSQL sql;

	public static void start() {
		try {
			sql = new SyncSQL(URL, "signinout", Username, Password);
			tryConnection();
			checkTable();
		} catch (Exception e) {
			e.printStackTrace();
			SignInOut.error(1);
		}
	}

	private static void checkTable() throws ClassNotFoundException, SQLException, IOException {
		if(!(sql.doesTableExist("students"))) {
			String query = "CREATE TABLE students(ID int NOT NULL AUTO_INCREMENT, lastName varchar(255), firstName varchar(255), grade varchar(255), advisor varchar(255), date varchar(255), time varchar(255), InOrOut varchar(255), reason varchar(255), PRIMARY KEY (ID))";
			sql.standardQuery(query);
		} else {
			System.out.println("Database already exists");
		}
	} 

	private static void tryConnection() throws Exception {
		boolean initialise = sql.initialise();
		System.out.println("Connection "+initialise);
	}

	public static void addStudent(Student s) {
		String query = "INSERT INTO students VALUES (0,'"+s.firstName+"', '"+s.lastName+"', '"+s.grade+"', '"+s.advisor+"', '"+s.date+"', '"+s.time+"', '"+s.InOut+"', '"+s.reason+"');";
		System.out.println(query);
		try {
			sql.standardQuery(query);
			System.out.println("SQL Query sucessfull!");
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
