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
		
	    this.id = id;

	}

	/**
	 * Method getFirstName.
	 * Pre: Nothing
	 * Post: the First name is returned.
	 * 
	 * @return String */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method setFirstName.
	 * Pre: A string with first name must be given.
	 * Post: The firstname is set to the given string.
	 * 
	 * @param name String
	 */
	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	/**
	 * Method getLastName.
	 * Pre: Nothing
	 * Post: Returns the last name
	 * 
	 * @return String */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method setLastName.
	 * Pre: A last name must be given.
	 * Post: The last name is set to given string
	 * 
	 * @param name String
	 */
	public void setLastName(String name) {
		this.lastName = name;
	}

	/**
	 * Method getReason.
	 * Pre: Nothing
	 * Post: Returns the reason
	 * 
	 * @return String */
	public String getReason() {
		return reason;
	}

	/**
	 * Method setReason.
	 * Pre: A reason must be given.
	 * Post: The reason is set to given string
	 * @param reason String
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * Method getDate.
	 * Pre: Nothing
	 * Post: Returns the date.
	 * 
	 * @return String */
	public String getDate() {
		return date;
	}

	/**
	 * Method setDate.
	 * Pre: A date must be given.
	 * Post: The date is set to the given string.
	 * 
	 * @param date String
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Method getTime.
	 * Pre: Nothing
	 * Post: Returns the time
	 * 
	 * @return String */
	public String getTime() {
		return time;
	}

	/**
	 * Method setTime.
	 * Pre: A string time must be given.
	 * Post: The time is set to the given string
	 * @param time String
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Method getInOut.
	 * Pre: Nothing
	 * Post: returns the InOut 
	 * 
	 * @return String */
	public String getInOut() {
		return InOut;
	}

	/**
	 * Method setInOut.
	 * Pre: A string inOut must be given
	 * Post: the InOut is set to the given string.
	 * 
	 * @param inOut String
	 */
	public void setInOut(String inOut) {
		InOut = inOut;
	}

	/**
	 * Method getGrade.
	 * Pre: Nothing
	 * Post: return grade
	 * 
	 * @return int */
	public int getGrade() {
		return grade;
	}

	/**
	 * Method setGrade.
	 * Pre: A int grade must be given
	 * Post: The grade is set to the given int
	 * 
	 * @param grade int
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}

	/**
	 * Method getID.
	 * Pre: Nothing
	 * Post: returns the UUID
	 * 
	 * @return UUID */
	public UUID getID() {
		return id;
	}

	/**
	 * Method getChecked.
	 * Pre: Nothing
	 * Post: returns the checked value
	 * 
	 * @return boolean */
	public boolean getChecked() {
		return checked;
	}
	
	

}
