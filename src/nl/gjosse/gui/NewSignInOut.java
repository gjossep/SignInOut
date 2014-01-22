package nl.gjosse.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import nl.gjosse.core.EventHandler;
import nl.gjosse.core.FileHandler;

public class NewSignInOut {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @param frame2 
	 */
	
	public static JFrame other;
	private static JTextField textFirstName;
	private static JTextField textReason;
	private static JComboBox<?> InOutBox;
	private static JComboBox<?> gradeComboBox;
	private static JComboBox<?> advisorBox;
	private static JTextField textLastName;
	
	
	public static void start(JFrame frame2) {
		other = frame2;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewSignInOut window = new NewSignInOut();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void start(JFrame frame2, Student student) {
		other = frame2;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewSignInOut window = new NewSignInOut();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setLocation(other.getX(), other.getY());
		frame.setBounds(100, 100, 574, 342);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		gradeComboBox = new JComboBox<Object>();
		gradeComboBox.setModel(new DefaultComboBoxModel(new String[] {"9", "10", "11", "12"}));
		gradeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int grade = Integer.parseInt((String) gradeComboBox.getSelectedItem());
			}
		});
		gradeComboBox.setBounds(97, 119, 117, 20);
		frame.getContentPane().add(gradeComboBox);
		
		InOutBox = new JComboBox<Object>();
		InOutBox.setModel(new DefaultComboBoxModel(new String[] {"Signing In", "Signing Out"}));
		InOutBox.setBounds(98, 180, 116, 20);
		frame.getContentPane().add(InOutBox);
		
		textReason = new JTextField();
		textReason.setColumns(10);
		textReason.setBounds(98, 217, 396, 20);
		frame.getContentPane().add(textReason);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(234, 250, 89, 23);
		btnSubmit.setActionCommand("submit");
		btnSubmit.addActionListener(new EventHandler());
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
		
		advisorBox = new JComboBox<Object>();
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
		model = new DefaultComboBoxModel<String>(array);
		
		return model;
	}

	public static String getTextReason() {
		return textReason.getText();
	}
	public static String getTextFirstName() {
		return textFirstName.getText();
	}
	public static String getInOutBox() {
		return InOutBox.getSelectedItem().toString();
	}
	public static int getGradeComboBox() {
		return Integer.parseInt(gradeComboBox.getSelectedItem().toString());
	}
	public static String getAdvisorBox() {
		return advisorBox.getSelectedItem().toString();
	}
	public static String getTextLastName() {
		return textLastName.getText();
	}
}
