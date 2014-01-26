package nl.gjosse.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

import org.jdesktop.swingx.JXTable;

import nl.gjosse.mysql.MYSQL;

public class MainWindow {

	public static JFrame frame;
	private static JXTable table;
	private JTextField textField;

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
	@SuppressWarnings("serial")
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
		btnSignInout.setBounds(46, 36, 113, 23);
		userInputPanel.add(btnSignInout);
		
		JButton btnEditData = new JButton("Edit data");
		btnEditData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					table.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEditData.setBounds(46, 68, 113, 23);
		userInputPanel.add(btnEditData);
		
		JLabel lblSort = new JLabel("Sort:");
		lblSort.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSort.setBounds(10, 221, 46, 14);
		userInputPanel.add(lblSort);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Last Name", "First Name", "Grade", "Advisor", "Time In/Out", "Date", "Reason"}));
		comboBox.setBounds(66, 219, 128, 20);
		userInputPanel.add(comboBox);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSearch.setBounds(10, 255, 46, 14);
		userInputPanel.add(lblSearch);
		
		textField = new JTextField();
		textField.setBounds(66, 252, 128, 20);
		userInputPanel.add(textField);
		textField.setColumns(10);
	}

	public static void setModel() throws ClassNotFoundException, SQLException, IOException {
		table.setModel(MYSQL.getModel());
	}
	
}
