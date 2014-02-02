package nl.gjosse.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 */
public class FileHandler {

	public static File source = new File(System.getProperty("user.home"),"SignInOut");
	
	public static void createFolder() {
		if(!source.exists()) {
			source.mkdir();
		}
	}
	

	
	/**
	 * Method getStringArray.
	 * @param i int
	 * @return String[]
	 */
	public static String[] getStringArray(int i) {
		File toFind = new File(source, (i +".txt"));
		ArrayList<String> array = new ArrayList<String>();
		if(toFind.exists()) {
			try {
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
	 * @param array ArrayList<String>
	 * @return String[]
	 */
	public static String[] arrayListToArray(ArrayList<String> array) {
		String[] stringArray = new String[array.size()];
		
		for(int i = 0; i<array.size(); i++) {
			String s = array.get(i);
			stringArray[i] = s;
		}
		
		return stringArray;
	}


	public static void createFiles() {
		boolean isDefault = false;
		for(int i = 9; i<=12; i++) {
			File toFind = new File(source, (i +".txt"));
			if(!toFind.exists()) {
				try {
					isDefault = true;
					FileWriter fw = new FileWriter(toFind.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					fw.write("DEFAULT");
					fw.flush();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
		
		if(isDefault) {
			SignInOut.warning(1);
		}

	}
}
