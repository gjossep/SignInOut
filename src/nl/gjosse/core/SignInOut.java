package nl.gjosse.core;

import javax.swing.JOptionPane;

import nl.gjosse.gui.MainWindow;
import nl.gjosse.mysql.MYSQL;

public class SignInOut {
	
	public static void main(String[] args) {
		MainWindow.start();
		FileHandler.createFolder();
		FileHandler.createFiles();
		MYSQL.start();
	}
	
	public static void warning(int code) {
		if(code == 1) {
			JOptionPane.showMessageDialog(MainWindow.frame,"Some Advisor files are still at default. Please fill them in! \n They are located at: "+FileHandler.source.getAbsolutePath(),"Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
