package nl.gjosse.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nl.gjosse.gui.NewSignInOut;
import nl.gjosse.gui.Student;

public class EventHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		
		if(command.equalsIgnoreCase("submit")) {
			Student student = new Student(NewSignInOut.getTextFirstName(),NewSignInOut.getTextLastName(), NewSignInOut.getGradeComboBox(), NewSignInOut.getAdvisorBox(), getDate(), getTime(), NewSignInOut.getInOutBox(), NewSignInOut.getTextReason());
		}
		
	}

	private String getDateAndTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//get current date time with Date()
		Date date = new Date();
		return dateFormat.format(date);
	}

	private String getTime() {
		String dateAndTime = getDateAndTime();
		String time = dateAndTime.substring(11);
		return time;
	}
	
	private String getDate() {
		String dateAndTime = getDateAndTime();
		String time = dateAndTime.substring(0, 10);
		return time;
	}

}
