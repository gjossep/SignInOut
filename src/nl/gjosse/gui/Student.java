package nl.gjosse.gui;

import java.util.UUID;

/**
 * @author room-243-16
 * @version $Revision: 1.0 $
 */
public class Student {
	
	public String firstName;
	public String lastName;
	public String reason;
	public String date;
	public String time;
	public String InOut;
	public int grade;
	public String advisor;
	public UUID id;
	public boolean checked; 
	
	/**
	 * Constructor for Student.
	 * @param firstName String
	 * @param lastName String
	 * @param grade int
	 * @param advisor String
	 * @param date String
	 * @param time String
	 * @param InOut String
	 * @param reason String
	 */
	public Student (String firstName, String lastName, int grade, String advisor ,String date, String time, String InOut, String reason) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		this.advisor = advisor;
		this.date = date;
		this.time = time;
		this.InOut = InOut;
		this.reason = reason;
		this.checked = false;
		
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(grade);
		System.out.println(advisor);
		System.out.println(date);
		System.out.println(time);
		System.out.println(InOut);
		System.out.println(reason);
		System.out.println(checked);
		
	    this.id = UUID.randomUUID();
	}
	
	/**
	 * Constructor for Student.
	 * @param firstName String
	 * @param lastName String
	 * @param grade int
	 * @param advisor String
	 * @param date String
	 * @param time String
	 * @param InOut String
	 * @param reason String
	 * @param id UUID
	 */
	public Student (String firstName, String lastName, int grade, String advisor ,String date, String time, String InOut, String reason, UUID id, boolean checked) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		this.advisor = advisor;
		this.date = date;
		this.time = time;
		this.InOut = InOut;
		this.reason = reason;
		this.checked = checked;
		
//		System.out.println(firstName);
//		System.out.println(lastName);
//		System.out.println(grade);
//		System.out.println(advisor);
//		System.out.println(date);
//		System.out.println(time);
//		System.out.println(InOut);
//		System.out.println(reason);
		
	    this.id = id;

	}

	/**
	 * Method getFirstName.
	
	 * @return String */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method setFirstName.
	 * @param name String
	 */
	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	/**
	 * Method getLastName.
	
	 * @return String */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method setLastName.
	 * @param name String
	 */
	public void setLastName(String name) {
		this.lastName = name;
	}

	/**
	 * Method getReason.
	
	 * @return String */
	public String getReason() {
		return reason;
	}

	/**
	 * Method setReason.
	 * @param reason String
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * Method getDate.
	
	 * @return String */
	public String getDate() {
		return date;
	}

	/**
	 * Method setDate.
	 * @param date String
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Method getTime.
	
	 * @return String */
	public String getTime() {
		return time;
	}

	/**
	 * Method setTime.
	 * @param time String
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Method getInOut.
	
	 * @return String */
	public String getInOut() {
		return InOut;
	}

	/**
	 * Method setInOut.
	 * @param inOut String
	 */
	public void setInOut(String inOut) {
		InOut = inOut;
	}

	/**
	 * Method getGrade.
	
	 * @return int */
	public int getGrade() {
		return grade;
	}

	/**
	 * Method setGrade.
	 * @param grade int
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}

	/**
	 * Method getID.
	
	 * @return UUID */
	public UUID getID() {
		return id;
	}

	public boolean getChecked() {
		return checked;
	}
	
	

}
