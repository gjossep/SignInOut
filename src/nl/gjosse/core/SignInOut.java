package nl.gjosse.core;

import nl.gjosse.gui.MainWindow;
import nl.gjosse.mysql.MYSQL;

public class SignInOut {
	
	public static void main(String[] args) {
		MainWindow.start();
		MYSQL.start();
	}
	
}
