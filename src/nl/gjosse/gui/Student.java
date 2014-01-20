package nl.gjosse.gui;

public class Student {
	
	public String firstName;
	public String lastName;
	public String reason;
	public String date;
	public String time;
	public String InOut;
	public int grade;
	public String advisor;
	
	public Student (String firstName, String lastName, int grade, String advisor ,String date, String time, String InOut, String reason) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		this.advisor = advisor;
		this.date = date;
		this.time = time;
		this.InOut = InOut;
		this.reason = reason;
		
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(grade);
		System.out.println(advisor);
		System.out.println(date);
		System.out.println(time);
		System.out.println(InOut);
		System.out.println(reason);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getInOut() {
		return InOut;
	}

	public void setInOut(String inOut) {
		InOut = inOut;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	

}
