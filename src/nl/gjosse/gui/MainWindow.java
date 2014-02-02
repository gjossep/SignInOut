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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import nl.gjosse.mysql.MYSQL;

import org.jdesktop.swingx.JXTable;

public class MainWindow {

	public static JFrame frame;
	private static JXTable table;
	private JTextField searchField;

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public MainWindow() throws ClassNotFoundException, SQLException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings({ "serial", "unchecked" })
	private void initialize() throws ClassNotFoundException, SQLException, IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(0, 0, 570, 462);
		frame.getContentPane().add(tablePanel);
		tablePanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 570, 462);
		tablePanel.add(scrollPane);
		
		table = new JXTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(MYSQL.getModel());
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
				
		
		JPanel userInputPanel = new JPanel();
		userInputPanel.setBounds(569, 0, 215, 462);
		frame.getContentPane().add(userInputPanel);
		userInputPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign In/Out");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(36, 1, 140, 24);
		userInputPanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 25, 195, 10);
		userInputPanel.add(separator);
		
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
		
		JButton btnEditData = new JButton("Edit data");
		btnEditData.addMouseListener(new MouseAdapter() {
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
					
					Student s = new Student(firstName, lastName, grade, advisor, date, time, inOrOut, reason, MYSQL.getID(firstName, lastName));
					NewSignInOut.start(frame, s);
					
				}
			}
		});
		btnEditData.setBounds(46, 68, 113, 30);
		userInputPanel.add(btnEditData);
		
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
		
		JButton btnPrintTable = new JButton("Print Table");
		btnPrintTable.setBounds(46, 200, 113, 30);
		btnPrintTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					table.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		userInputPanel.add(btnPrintTable);
		
		
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
				searchTable(searchField.getText());
			}
		});
		userInputPanel.add(btnSearch);
	}

	protected void searchTable(String toFind) {
		for(int i = 0; i<table.getRowCount(); i++)
		{
			String username = (String) table.getValueAt(i, 1);
			if(username.contains(toFind)) {
                table.setRowSelectionInterval(i, i);
			}
		}
		
	}

	public static void setModel() throws ClassNotFoundException, SQLException, IOException {
		table.setModel(MYSQL.getModel());
	}
	
}
