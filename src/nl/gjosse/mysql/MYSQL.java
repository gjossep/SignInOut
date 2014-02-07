package nl.gjosse.mysql;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.UUID;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nl.gjosse.core.SignInOut;
import nl.gjosse.gui.MainWindow;
import nl.gjosse.gui.Student;



/**
 * @author room-243-16
 * @version $Revision: 1.0 $
 */
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

	/**
	 * Method checkTable.
	
	
	
	 * @throws ClassNotFoundException * @throws SQLException * @throws IOException */
	private static void checkTable() throws ClassNotFoundException, SQLException, IOException {
		if(!(sql.doesTableExist("students"))) {
			String query = "CREATE TABLE students(ID int NOT NULL AUTO_INCREMENT, lastName varchar(255), firstName varchar(255), grade varchar(255), advisor varchar(255), date varchar(255), time varchar(255), InOrOut varchar(255), reason varchar(255),uuid varchar(255), checked boolean, PRIMARY KEY (ID))";
			sql.standardQuery(query);
		} else {
			System.out.println("Database already exists");
		}
	} 

	/**
	 * Method tryConnection.
	
	 * @throws Exception */
	private static void tryConnection() throws Exception {
		boolean initialise = sql.initialise();
		System.out.println("Connection "+initialise);
	}

	/**
	 * Method addStudent.
	 * @param s Student
	 */
	public static void addStudent(Student s) {
		String query = "INSERT INTO students VALUES (0,'"+s.lastName+"', '"+s.firstName+"', '"+s.grade+"', '"+s.advisor+"', '"+s.date+"', '"+s.time+"', '"+s.InOut+"', '"+s.reason+"', '"+s.getID().toString()+"', "+s.getChecked()+");";
		System.out.println(query);
		try {
			sql.standardQuery(query);
			System.out.println("SQL Query sucessfull!");
			MainWindow.setModel();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method editStudent.
	 * @param s Student
	 */
	public static void editStudent(Student s) {
		String query = "UPDATE students SET lastName='"+s.lastName+"', firstName='"+s.firstName+"', grade='"+s.grade+"', advisor='"+s.advisor+"', InOrOut='"+s.InOut+"', reason='"+s.reason+"' WHERE uuid='"+s.getID().toString()+"', "+s.getChecked()+";";
		System.out.println(query);
		try {
			sql.standardQuery(query);
			System.out.println("SQL Query sucessfull!");
			MainWindow.setModel();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method getID.
	 * @param firstName String
	 * @param lastName String
	
	 * @return UUID */
	public static UUID getID(String firstName, String lastName) {
		String value = null;
		String query = "SELECT * FROM students;";
		System.out.println(query);
		try {
			ResultSet result = sql.sqlQuery(query);
			while(result.next()) {
				String databaseLast = result.getString(2);
				String databaseFirst = result.getString(3);
				if(databaseFirst.equals(firstName) && databaseLast.equals(lastName)) {
					value = result.getString(10);
				}
			}
			System.out.println("SQL Query sucessfull! "+value);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UUID id = UUID.fromString(value);
		return id;
		
	}
	
	/**
	 * Method removeStudent.
	 * @param id UUID
	 */
	public static void removeStudent(UUID id) {
		String query = "DELETE FROM students WHERE uuid='"+id+"';";
		System.out.println(query);
		
		try {
			sql.standardQuery(query);
			System.out.println("SQL Query sucessfull!");
			MainWindow.setModel();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void clearTable() {
		String query = "DELETE FROM students;";
		System.out.println(query);
		
		try {
			sql.standardQuery(query);
			System.out.println("SQL Query sucessfull!");
			MainWindow.setModel();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void setChecked(Student s) {
		String query = "UPDATE students SET checked="+s.getChecked()+" WHERE uuid='"+s.getID().toString()+"';";
		System.out.println(query);
		
		try {
			sql.standardQuery(query);
			System.out.println("SQL Query sucessfull!");
			MainWindow.setModel();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method getModel.
	
	
	
	
	 * @return TableModel * @throws SQLException * @throws ClassNotFoundException * @throws IOException */
	public static TableModel getModel() throws SQLException, ClassNotFoundException, IOException {
		//sql.initialise();
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
			columns.add("Time"); 
			columns.add("Date"); 
			columns.add("Reason");
			columns.add("Checked");
           
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
                  row.add(rs.getObject("checked"));
              data.add(row);  
              
         }  
         DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
        	 Class[] columnTypes = new Class[] {
     				Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
     			};
     			public Class getColumnClass(int columnIndex) {
     				return columnTypes[columnIndex];
     			}
         };  
         
         
		return tableModel;
	}






	
}
