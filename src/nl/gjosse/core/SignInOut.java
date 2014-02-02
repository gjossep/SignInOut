package nl.gjosse.core;

import javax.swing.JOptionPane;

import nl.gjosse.gui.MainWindow;
import nl.gjosse.mysql.MYSQL;

/**
 */
public class SignInOut {
	
	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		MainWindow.start();
		FileHandler.createFolder();
		FileHandler.createFiles();
		MYSQL.start();
	}
	
	/**
	 * Method warning.
	 * @param code int
	 */
	public static void warning(int code) {
		if(code == 1) {
			JOptionPane.showMessageDialog(MainWindow.frame,"Some Advisor files are still at default. Please fill them in! \n They are located at: "+FileHandler.source.getAbsolutePath(),"Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Method error.
	 * @param code int
	 */
	public static void error(int code) {
		if(code == 1) {
			JOptionPane.showMessageDialog(MainWindow.frame,"Something went wrong with connecting to the database. \n Quiting...","Link Fail", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}		
	}
	
}
