package nl.gjosse.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXTable;

/**
 * @author Gjosse Zijlstra
 * @version $Revision: 1.0 $
 */
public class FileHandler {

	public static File source = new File(System.getProperty("user.home"),"SignInOut");
	public static File history = new File(source, "History");
	public static void createFolder() {
		if(!source.exists()) {
			source.mkdir();
		}
		
		if(!history.exists()) {
			history.mkdir();
		}
	}
	

	
	/**
	 * Method getStringArray.
	 * This method gets an array out of the file with advisors names.
	 * Pre: the amount of advisors must be known
	 * Post: A Array is created
	 * @param i int
	
	 * @return String[] */
	public static String[] getStringArray(int i) {
		File toFind = new File(source, (i +".txt"));
		ArrayList<String> array = new ArrayList<String>();
		if(toFind.exists()) {
			try {
				@SuppressWarnings("resource")
				BufferedReader fr = new BufferedReader(new FileReader(toFind));
				String input;
				while((input=fr.readLine())!= null) {
					array.add(input);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				toFind.createNewFile();
				FileWriter fw = new FileWriter(toFind);
				BufferedWriter bw = new BufferedWriter(fw);
				
				bw.write("John");
				bw.newLine();

				bw.flush();
				bw.close();
				fw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return (arrayListToArray(array));
	}

	/**
	 * Method arrayListToArray.
	 * This method converts a ArrayList into a normal array.
	 * Pre: A ArrayList must be given.
	 * Post: A String array is created.
	 * @param array ArrayList<String>
	
	 * @return String[] */
	public static String[] arrayListToArray(ArrayList<String> array) {
		String[] stringArray = new String[array.size()];
		
		for(int i = 0; i<array.size(); i++) {
			String s = array.get(i);
			stringArray[i] = s;
		}
		
		return stringArray;
	}

	/**
	 * Method createFiles
	 * This method is to create the new advisor files and fill them in with DEFAULT
	 * Pre: Nothing
	 * Post: The files are created.
	 */
	public static void createFiles() {
		boolean isDefault = false;
		for(int i = 9; i<=12; i++) {
			File toFind = new File(source, (i +".txt"));
			if(!toFind.exists()) {
				try {
					isDefault = true;
					FileWriter fw = new FileWriter(toFind.getAbsoluteFile());
					fw.write("DEFAULT");
					fw.flush();
					fw.close();
				} catch (IOException e) {
					//Error, display to user
					SignInOut.error(2);
					e.printStackTrace();
				}
			} else {
				BufferedReader fr;
				try {
					fr = new BufferedReader(new FileReader(toFind));
					String input = fr.readLine();
					if(input.equalsIgnoreCase("DEFAULT")) {
						isDefault = true;
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		}
		
		if(isDefault) {
			SignInOut.warning(1);
		}

	}



	/**
	 * Method saveTable.
	 * A method to save the table into a excel file.
	 * Pre: A table must be given.
	 * Post: The file is writen.
	 * @param table JXTable
	 */
	public static void saveTable(JXTable table) {
		TableModel model = table.getModel();
		File toSave = new File(history, getDate() + ".xls");
		System.out.println(toSave.getAbsolutePath());
		try {
			if(toSave.exists()) {
				System.out.println("WRONG");
			} else {
				toSave.createNewFile();
			}
			FileWriter output = new FileWriter(toSave);

			for (int i = 0; i < model.getColumnCount(); i++) {
				output.write(model.getColumnName(i) + "\t");
			}

			output.write("\n");

			for (int k = 0; k < model.getRowCount(); k++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					output.write(model.getValueAt(k, j).toString() + "\t");
				}
				output.write("\n");

			}
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
			SignInOut.warning(2);
		}
	}
	
	/**
	 * Method getDate.
	 * A method to get the date.
	 * Pre: Null
	 * Post: The date is returned.
	 * @return String
	 */
	private static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//get current date time with Date()
		Date date = new Date();
		return dateFormat.format(date);
	}
}
