package nl.gjosse.mysql;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nl.gjosse.core.SignInOut;
import nl.gjosse.gui.MainWindow;
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
			MainWindow.setModel();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static TableModel getModel() throws SQLException, ClassNotFoundException, IOException {
		sql.initialise();
		 ResultSet rs = sql.sqlQuery("select * from students;");  
         ResultSetMetaData md = rs.getMetaData();  
         int columnCount = md.getColumnCount();  
         Vector<String> columns = new Vector<String>(columnCount);  

       //store column names  
			columns.add("Last Name"); 
			columns.add("First Name"); 
			columns.add("Grade"); 
			columns.add("Advisor"); 
			columns.add("In or Out"); 
			columns.add("Time In/Out"); 
			columns.add("Date"); 
			columns.add("Reason");
           
       Vector<Vector<Object>> data = new Vector<Vector<Object>>();  
       Vector<Object> row;  
                
         while (rs.next()) {  
        	 row = new Vector<Object>(columnCount);  
                  row.add(rs.getObject("lastName")); 
                  row.add(rs.getObject("firstName"));
                  row.add(rs.getObject("grade"));
                  row.add(rs.getObject("advisor"));
                  row.add(rs.getObject("InOrOut"));
                  row.add(rs.getObject("time"));
                  row.add(rs.getObject("date"));
                  row.add(rs.getObject("reason"));
              data.add(row);  
              
         }  
         DefaultTableModel tableModel = new DefaultTableModel(data, columns);  
         
		return tableModel;
	}

	
}
