package nl.gjosse.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import nl.gjosse.core.FileHandler;
import nl.gjosse.mysql.MYSQL;

/**
 * @author Gjosse Zijlstra
 * @version $Revision: 1.0 $
 */
public class NewSignInOut {

	private static JFrame frame;

	/**
	 * Launch the application.
	
	 */
	
	public static JFrame other;
	private static JTextField textFirstName;
	private static JTextField textReason;
	private static JComboBox InOutBox;
	private static JComboBox gradeComboBox;
	private static JComboBox advisorBox;
	private static JTextField textLastName;
	
	public static String time;
	public static String date;
	public static UUID id;
	public static boolean updated = false;

	
	/**
	 * Method start.
	 * @param frame2 JFrame
	 */
	public static void start(JFrame frame2) {
		other = frame2;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					NewSignInOut window = new NewSignInOut();
					NewSignInOut.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Method start.
	 * @param frame2 JFrame
	 * @param student Student
	 */
	public static void start(JFrame frame2, final Student student) {
		other = frame2;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					NewSignInOut window = new NewSignInOut();
					NewSignInOut.frame.setVisible(true);
					
					textFirstName.setText(student.getFirstName());
					textLastName.setText(student.getLastName());
					gradeComboBox.setSelectedItem(student.grade);
					advisorBox.setSelectedItem(student.advisor);
					InOutBox.setSelectedItem(student.InOut);
					time = student.getTime();
					date = student.getDate();
					textReason.setText(student.getReason());
					id = student.getID();
					System.out.println(id);
					updated = true;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public NewSignInOut() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		/* A method to inteleize all compants of the GUI
		 * Pre: Nothing
		 * Post: A GUI is created and prestend to the user.
		 */
		frame = new JFrame();
		frame.setLocation(other.getX(), other.getY());
		frame.setBounds(100, 100, 574, 342);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("First Name:");
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblName.setBounds(10, 54, 78, 24);
		frame.getContentPane().add(lblName);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(98, 56, 396, 20);
		frame.getContentPane().add(textFirstName);
		textFirstName.setColumns(10);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setHorizontalAlignment(SwingConstants.TRAILING);
		lblGrade.setBounds(10, 117, 78, 24);
		frame.getContentPane().add(lblGrade);
		
		JLabel lblSignInout = new JLabel("Sign In/Out");
		lblSignInout.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSignInout.setBounds(10, 178, 78, 24);
		frame.getContentPane().add(lblSignInout);
		
		JLabel lblReason = new JLabel("Reason:");
		lblReason.setHorizontalAlignment(SwingConstants.TRAILING);
		lblReason.setBounds(10, 215, 78, 24);
		frame.getContentPane().add(lblReason);
		
		gradeComboBox = new JComboBox();
		gradeComboBox.setModel(new DefaultComboBoxModel(new String[] {"9", "10", "11", "12"}));
		gradeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*A method to register when the user clicks on the combobox
				 *Pre: The event must be triggered.
				 *Post: The correct model is set for the advisorBox using the grade.
				 */				
				int grade = Integer.parseInt((String) gradeComboBox.getSelectedItem());
				advisorBox.setModel(getAdvisors(grade));
			}
		});
		gradeComboBox.setBounds(97, 119, 117, 20);
		frame.getContentPane().add(gradeComboBox);
		
		InOutBox = new JComboBox();
		InOutBox.setModel(new DefaultComboBoxModel(new String[] {"Signing In", "Signing Out"}));
		InOutBox.setBounds(98, 180, 116, 20);
		frame.getContentPane().add(InOutBox);
		
		textReason = new JTextField();
		textReason.setColumns(10);
		textReason.setBounds(98, 217, 396, 20);
		frame.getContentPane().add(textReason);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(234, 250, 89, 23);
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName = NewSignInOut.getTextFirstName();
				String lastName = NewSignInOut.getTextLastName();
				String reason = NewSignInOut.getTextReason();
				
				if((!firstName.equals("")) && (!lastName.equals("")) && (!reason.equals(""))) {
					if(!updated) {
						Student student = new Student(firstName,lastName, NewSignInOut.getGradeComboBox(), NewSignInOut.getAdvisorBox(), getDate(), getTime(), NewSignInOut.getInOutBox(), reason);
						NewSignInOut.close();
						
						MYSQL.addStudent(student);
					} else {
						Student student = new Student(firstName,lastName, NewSignInOut.getGradeComboBox(), NewSignInOut.getAdvisorBox(), date, time, NewSignInOut.getInOutBox(), reason, id);
						NewSignInOut.close();
						
						MYSQL.editStudent(student);
					}
					
				} else {
					NewSignInOut.error();
				}
			}
		});
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblNewLabel_1 = new JLabel("Sign In/Out");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(137, 11, 283, 32);
		frame.getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 41, 538, 20);
		frame.getContentPane().add(separator);
		
		JLabel lblAdvisor = new JLabel("Advisor:");
		lblAdvisor.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAdvisor.setBounds(10, 145, 78, 24);
		frame.getContentPane().add(lblAdvisor);
		
		advisorBox = new JComboBox();
		advisorBox.setModel(getAdvisors(9));
		advisorBox.setBounds(97, 147, 117, 20);
		frame.getContentPane().add(advisorBox);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLastName.setBounds(10, 82, 78, 24);
		frame.getContentPane().add(lblLastName);
		
		textLastName = new JTextField();
		textLastName.setColumns(10);
		textLastName.setBounds(98, 84, 396, 20);
		frame.getContentPane().add(textLastName);
	}

	/**
	 * Method getAdvisors.
	 * @param i int
	
	 * @return ComboBoxModel */
	@SuppressWarnings("rawtypes")
	private ComboBoxModel getAdvisors(int i) {
		/*
		 * Method to get all the advisors for one grade.
		 * Pre: A grade number as parameter
		 * Post: A ComboBoxModel with all the advisors for the grade.
		 */
		DefaultComboBoxModel model = null;
		String[] array;
		
		array = FileHandler.getStringArray(i);
		model = new DefaultComboBoxModel(array);
		
		return model;
	}

	public static void close() {
		frame.dispose();
	}
	
	/**
	 * Method getDateAndTime.
	
	 * @return String */
	private String getDateAndTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//get current date time with Date()
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Method getTime.
	
	 * @return String */
	private String getTime() {
		String dateAndTime = getDateAndTime();
		String time = dateAndTime.substring(11);
		return time;
	}
	
	/**
	 * Method getDate.
	
	 * @return String */
	private String getDate() {
		String dateAndTime = getDateAndTime();
		String time = dateAndTime.substring(0, 10);
		return time;
	}
	
	public static void error() {
		JOptionPane.showMessageDialog(frame,
			    "Not all fields are filled out!",
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	/**
	 * Method getTextReason.
	
	 * @return String */
	public static String getTextReason() {
		return textReason.getText();
	}
	/**
	 * Method getTextFirstName.
	
	 * @return String */
	public static String getTextFirstName() {
		return textFirstName.getText();
	}
	/**
	 * Method getInOutBox.
	
	 * @return String */
	public static String getInOutBox() {
		return InOutBox.getSelectedItem().toString();
	}
	/**
	 * Method getGradeComboBox.
	
	 * @return int */
	public static int getGradeComboBox() {
		return Integer.parseInt(gradeComboBox.getSelectedItem().toString());
	}
	/**
	 * Method getAdvisorBox.
	
	 * @return String */
	public static String getAdvisorBox() {
		return advisorBox.getSelectedItem().toString();
	}
	/**
	 * Method getTextLastName.
	
	 * @return String */
	public static String getTextLastName() {
		return textLastName.getText();
	}

	
	


}
