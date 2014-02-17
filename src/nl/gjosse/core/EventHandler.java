package nl.gjosse.core;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jdesktop.swingx.JXTable;

import nl.gjosse.gui.Student;
import nl.gjosse.mysql.MYSQL;

public class EventHandler implements TableModelListener {

	JXTable table;
	
	public EventHandler(JXTable table) {
		this.table = table;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		//Get the column it gets changed.
		int column = e.getColumn();
		System.out.println(column);
		
		//If the column changed is the last one.
		if(column == 8) {
			
			//Get all info from other column.
			int selectedRow = e.getLastRow();
			String lastName = (String) table.getModel().getValueAt(selectedRow, 0);
			String firstName = (String) table.getModel().getValueAt(selectedRow, 1);
			int grade = Integer.parseInt((String) table.getModel().getValueAt(selectedRow, 2));
			String advisor = (String) table.getModel().getValueAt(selectedRow, 3);
			String inOrOut = (String) table.getModel().getValueAt(selectedRow, 4);
			String time = (String) table.getModel().getValueAt(selectedRow, 5);
			String date = (String) table.getModel().getValueAt(selectedRow, 6);
			String reason = (String) table.getModel().getValueAt(selectedRow, 7);
			boolean checked = (Boolean) table.getModel().getValueAt(selectedRow, 8);
			
			//Create a new student using the data.
			final Student s = new Student(firstName, lastName, grade, advisor, date, time, inOrOut, reason, MYSQL.getID(firstName, lastName), checked);
			
			//Set checked on the database.
			MYSQL.setChecked(s);
		}		
	}

}
