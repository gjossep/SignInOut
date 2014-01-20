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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class MainWindow {

	private JFrame frame;
	private JTable table;
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
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Doe", "John", "12", "Mr. Bob", "9:15 IN", "16 Jan 2014", "Embassy"},
				{"Smith", "Jane", "10", "Mrs. Bob", "10:30 OUT", "16 Jan 2014", "Doctor's"},
			},
			new String[] {
				"Last Name", "First Name", "Grade", "Advisor", "Time In/Out", "Date", "Reason"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(104);
		table.getColumnModel().getColumn(2).setPreferredWidth(46);
		table.getColumnModel().getColumn(4).setPreferredWidth(76);
		table.getColumnModel().getColumn(5).setPreferredWidth(69);
		table.getColumnModel().getColumn(6).setPreferredWidth(107);
		scrollPane.setViewportView(table);
		
		
		
		JPanel userInputPanel = new JPanel();
		userInputPanel.setBounds(569, 0, 215, 462);
		frame.getContentPane().add(userInputPanel);
		userInputPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign In/Out");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(36, 0, 140, 24);
		userInputPanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 23, 195, 10);
		userInputPanel.add(separator);
		
		JButton btnSignInout = new JButton("Sign In/Out");
		btnSignInout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				//New sign in/out
				NewSignInOut.start(frame);
				
			}
		});
		btnSignInout.setBounds(46, 35, 113, 23);
		userInputPanel.add(btnSignInout);
		
		JButton btnEditData = new JButton("Edit data");
		btnEditData.setBounds(46, 67, 113, 23);
		userInputPanel.add(btnEditData);
		
		JLabel lblSort = new JLabel("Sort:");
		lblSort.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSort.setBounds(10, 221, 46, 14);
		userInputPanel.add(lblSort);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Last Name", "First Name", "Grade", "Advisor", "Time In/Out", "Date", "Reason"}));
		comboBox.setBounds(66, 218, 128, 20);
		userInputPanel.add(comboBox);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSearch.setBounds(10, 254, 46, 14);
		userInputPanel.add(lblSearch);
		
		textField = new JTextField();
		textField.setBounds(66, 251, 128, 20);
		userInputPanel.add(textField);
		textField.setColumns(10);
	}
}
