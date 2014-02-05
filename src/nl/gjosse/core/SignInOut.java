package nl.gjosse.core;

import javax.swing.JOptionPane;

import nl.gjosse.gui.MainWindow;
import nl.gjosse.mysql.MYSQL;

/**
 * @author Gjosse Zijlstra
 * @version $Revision: 1.0 $
 */
public class SignInOut {
	
	public static void main(String[] args) {
		MYSQL.start();
		MainWindow.start();
		FileHandler.createFolder();
		FileHandler.createFiles();
		
		
	}
	

	/**
	 * Method warning.
	 * A method to display a warning the user.
	 * Pre: A code of what kind of warning is needed.
	 * Post: A dialog window pops up.
	 * @param code int
	 */
	public static void warning(int code) {
		if(code == 1) {
			JOptionPane.showMessageDialog(MainWindow.frame,"Some Advisor files are still at default. Please fill them in! \n They are located at: "+FileHandler.source.getAbsolutePath(),"Warning", JOptionPane.WARNING_MESSAGE);
		} else if(code == 2) {
			JOptionPane.showMessageDialog(MainWindow.frame,"There was an error trying to save the table to the file.","Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Method error.
	 * A method to display a error the user and then quit.
	 * Pre: A code of what kind of error is needed.
	 * Post: A dialog is showen to the user.
	 * @param code int
	 */
	public static void error(int code) {
		if(code == 1) {
			JOptionPane.showMessageDialog(MainWindow.frame,"Something went wrong with connecting to the database. \n Quiting...","Link Fail", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}		
	}


	/**
	 * Method message.
	 * A method to display a message to the user is displayed
	 * Pre: A String msg must be given.
	 * Post: A dialog wit the message is showen.
	 * @param msg String
	 */
	public static void message(String msg) {
		JOptionPane.showMessageDialog(MainWindow.frame,msg,"Message", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
