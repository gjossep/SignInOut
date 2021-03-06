package nl.gjosse.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import nl.gjosse.core.EventHandler;
import nl.gjosse.core.FileHandler;
import nl.gjosse.core.SignInOut;
import nl.gjosse.mysql.MYSQL;

import org.jdesktop.swingx.JXTable;

/**
 * @author Gjosse Zijlstra
 * @version $Revision: 1.0 $
 */
public class MainWindow {

	public static JFrame frame;
	private static JXTable table;
	private JTextField searchField;

	/*
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow window = new MainWindow();
					MainWindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Timer time = new Timer();
		//time.scheduleAtFixedRate(update, 5000, 20000);
	}

	static TimerTask update = new TimerTask() {

		@Override
		public void run() {
			try {
				System.out.println("Update");
				table.setModel(MYSQL.getModel());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	 
	};
	
	/**
	 * Create the application.
	 * @throws ClassNotFoundException  * @throws SQLException  * @throws IOException  * @throws SQLException
	 * @throws IOException
	 */
	public MainWindow() throws ClassNotFoundException, SQLException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * Pre: Nothing
	 * Post: A window is created
	 * 
	 * @throws ClassNotFoundException  * @throws SQLException  * @throws IOException  * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings({ "serial", "unchecked" })
	private void initialize() throws ClassNotFoundException, SQLException, IOException {
		
		//Set up the frame
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Set up the Jpanel for the table
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(0, 0, 570, 462);
		frame.getContentPane().add(tablePanel);
		tablePanel.setLayout(null);
		
		//Insert a scrollPane for the table to be in
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 570, 462);
		tablePanel.add(scrollPane);
		
		//Create a new JXtable and add it to the scrolpane
		table = new JXTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(MYSQL.getModel());
		table.setAutoCreateRowSorter(true);
		table.putClientProperty("terminateEditOnFocusLost", true);
		table.getModel().addTableModelListener(new EventHandler(table));
		
		scrollPane.setViewportView(table);
				
		//Set up the userInput panel.
		JPanel userInputPanel = new JPanel();
		userInputPanel.setBounds(569, 0, 215, 462);
		frame.getContentPane().add(userInputPanel);
		userInputPanel.setLayout(null);
		
		//Create some labels
		JLabel lblNewLabel = new JLabel("Sign In/Out");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(36, 1, 140, 24);
		userInputPanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 25, 195, 10);
		userInputPanel.add(separator);
		
		//Set up the button for a new Sign In/Out
		JButton btnSignInout = new JButton("Sign In/Out");
		btnSignInout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//New sign in/out
				NewSignInOut.start(frame);
			}
		});
		btnSignInout.setBounds(46, 36, 113, 30);
		userInputPanel.add(btnSignInout);
		
		//Set up the button to edit a entry
		JButton btnEditData = new JButton("Edit data");
		btnEditData.addMouseListener(new MouseAdapter() {
			
			//Check if mouse clicked the button
			@Override
			public void mouseReleased(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow >= 0) {
					String lastName = (String) table.getModel().getValueAt(selectedRow, 0);
					String firstName = (String) table.getModel().getValueAt(selectedRow, 1);
					int grade = Integer.parseInt((String) table.getModel().getValueAt(selectedRow, 2));
					String advisor = (String) table.getModel().getValueAt(selectedRow, 3);
					String inOrOut = (String) table.getModel().getValueAt(selectedRow, 4);
					String time = (String) table.getModel().getValueAt(selectedRow, 5);
					String date = (String) table.getModel().getValueAt(selectedRow, 6);
					String reason = (String) table.getModel().getValueAt(selectedRow, 7);
					boolean checked = (Boolean) table.getModel().getValueAt(selectedRow, 8);
					
					Student s = new Student(firstName, lastName, grade, advisor, date, time, inOrOut, reason, MYSQL.getID(firstName, lastName), checked);
					NewSignInOut.start(frame, s);
					
				}
			}
		});
		btnEditData.setBounds(46, 68, 113, 30);
		userInputPanel.add(btnEditData);
		
		//Set up the button to remove a entry
		JButton btnRemoveStudent = new JButton("Remove entry");
		btnRemoveStudent.setBounds(46, 100, 113, 30);
		btnRemoveStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				Object[] options = {"Yes!","No!"};
				int n = JOptionPane.showOptionDialog(frame, "Are you sure you want to remove this entry?", "Alert", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if(n==JOptionPane.YES_OPTION) {
					int selectedRow = table.getSelectedRow();
					if(selectedRow >= 0) {
						String lastName = (String) table.getModel().getValueAt(selectedRow, 0);
						String firstName = (String) table.getModel().getValueAt(selectedRow, 1);
						
						MYSQL.removeStudent(MYSQL.getID(firstName, lastName));	
						
					}
				}
			}
		});
		userInputPanel.add(btnRemoveStudent);
		
		//Create the button to save and clear the screen.
		JButton btnPrintTable = new JButton("Save & Clear");
		btnPrintTable.setBounds(46, 200, 113, 30);
		btnPrintTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				FileHandler.saveTable(table);
				MYSQL.clearTable();
				SignInOut.message("Saved table to file \n and cleared the table!");
			}
		});
		userInputPanel.add(btnPrintTable);
		
		//Set up the search fields and labels.
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSearch.setBounds(10, 255, 46, 14);
		userInputPanel.add(lblSearch);
		
		searchField = new JTextField();
		searchField.setBounds(66, 252, 128, 20);
		userInputPanel.add(searchField);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(66, 280, 128, 30);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Search the table for the indicated field
				searchTable(searchField.getText());
			}
		});
		userInputPanel.add(btnSearch);
	}

	/**
	 * Method searchTable.
	 * A method to search the table
	 * Pre: A string needs to be given
	 * Post: A entry in the table is selected 
	 * 
	 * @param toFind String
	 */
	protected void searchTable(String toFind) {
		for(int i = 0; i<table.getRowCount(); i++)
		{
			String username = (String) table.getValueAt(i, 1);
			if(username.contains(toFind)) {
                table.setRowSelectionInterval(i, i);
			}
		}
		
	}

	/**
	 * Method setModel.	
	 * A Method to set the model
	 * Pre: Nothing
	 * Post: The model is loaded from the database and given to the table.
	 * 
	 * @throws ClassNotFoundException * @throws SQLException * @throws IOException */
	public static void setModel() throws ClassNotFoundException, SQLException, IOException {
		table.setModel(MYSQL.getModel());
	}
	
}
