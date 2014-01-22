package nl.gjosse.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {

	public static File source = new File(System.getProperty("user.home"),"SignInOut");
	
	public static void createFolder() {
		if(!source.exists()) {
			source.mkdir();
		}
	}
	
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return (arrayListToArray(array));
	}

	public static String[] arrayListToArray(ArrayList<String> array) {
		String[] stringArray = new String[array.size()];
		
		for(int i = 0; i<array.size(); i++) {
			String s = array.get(i);
			stringArray[i] = s;
		}
		
		return stringArray;
	}
}
