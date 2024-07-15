package dashboard;

//Import necessary Java libraries
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class dashboard {

	private JFrame frame;
	private final JLabel lblNewLabel = new JLabel("X");
	JLabel lblNewLabel_4_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboard window = new dashboard(args); // Pass the admin's username
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public dashboard(String[] args) {
		initialize();
		if (args.length > 0) {
			String adminUsername = args[0];
			lblNewLabel_4_1.setText(adminUsername); // Set the admin's username
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(2, 62, 138));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Create and display the count of rows
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(countRows1())); // Set the count as text
		lblNewLabel_2.setBackground(new Color(0, 119, 182));
		lblNewLabel_2.setForeground(new Color(2, 18, 138));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel_2.setBounds(81, 383, 291, 149);
		frame.getContentPane().add(lblNewLabel_2);

		// Create a panel for displaying car information
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(114, 224, 239));
		panel_1.setBounds(81, 325, 291, 207);
		frame.getContentPane().add(panel_1);

		// Label for 'Cars'
		JLabel lblNewLabel_1 = new JLabel("Cars");
		lblNewLabel_1.setForeground(new Color(2, 18, 138));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel_1.add(lblNewLabel_1);

		// Create and display the count of rows
		JLabel lblNewLabel_2_1 = new JLabel(Integer.toString(countRows2())); // Set the count as text
		lblNewLabel_2_1.setBackground(new Color(2, 18, 138));
		lblNewLabel_2_1.setForeground(new Color(2, 18, 138));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel_2_1.setBounds(479, 383, 291, 149);
		frame.getContentPane().add(lblNewLabel_2_1);

		// Create a panel for displaying customer information
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setForeground(new Color(2, 18, 138));
		panel_1_1.setBackground(new Color(114, 224, 239));
		panel_1_1.setBounds(479, 325, 291, 207);
		frame.getContentPane().add(panel_1_1);

		// Label for 'Customers'
		JLabel lblNewLabel_1_1 = new JLabel("Customers");
		lblNewLabel_1_1.setBackground(new Color(2, 18, 138));
		lblNewLabel_1_1.setForeground(new Color(2, 18, 138));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel_1_1.add(lblNewLabel_1_1);

		// Create and display the count of rows
		JLabel lblNewLabel_2_1_1 = new JLabel(Integer.toString(countRows3())); // Set the count as text
		lblNewLabel_2_1_1.setForeground(new Color(2, 18, 138));
		lblNewLabel_2_1_1.setBackground(new Color(0, 119, 182));
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel_2_1_1.setBounds(896, 383, 291, 149);
		frame.getContentPane().add(lblNewLabel_2_1_1);

		// Create a panel for displaying booked items
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(new Color(114, 224, 239));
		panel_1_1_1.setBounds(896, 325, 291, 207);
		frame.getContentPane().add(panel_1_1_1);

		// Label for 'Booked'
		JLabel lblNewLabel_1_1_1 = new JLabel("Booked");
		lblNewLabel_1_1_1.setForeground(new Color(2, 18, 138));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel_1_1_1.add(lblNewLabel_1_1_1);

		// Create a panel for the main navigation buttons
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 119, 182));
		panel.setBounds(0, 0, 1266, 90);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// Button for 'Customers'
		JButton btnCustomer = new JButton("Customers");
		btnCustomer.setForeground(new Color(2, 62, 138));
		btnCustomer.setBackground(new Color(202, 240, 248));
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				myProject.customerForm.main(new String[] {});
			}
		});
		btnCustomer.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer.setBounds(466, 17, 211, 57);
		panel.add(btnCustomer);

		// Button for 'Reservations'
		JButton btnReservations = new JButton("Reservations");
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				myProject.reservationForm.main(new String[] {});
			}
		});
		btnReservations.setForeground(new Color(2, 62, 138));
		btnReservations.setBackground(new Color(202, 240, 248));
		btnReservations.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnReservations.setBounds(687, 17, 241, 57);
		panel.add(btnReservations);

		// Button for 'Admins'
		JButton btnAdmins = new JButton("Admins");
		btnAdmins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				project1.admins.main(new String[] {});
			}
		});
		btnAdmins.setForeground(new Color(2, 62, 138));
		btnAdmins.setBackground(new Color(202, 240, 248));
		btnAdmins.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnAdmins.setBounds(938, 17, 211, 57);
		panel.add(btnAdmins);

		// Button for 'Vehicle Specifications' with an action
		JButton btnVehicleSpecifications = new JButton("Vehicle Specifications");
		btnVehicleSpecifications.addMouseListener(new MouseAdapter() {
		});
		btnVehicleSpecifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				carbrand.main(new String[] {}); // Open the 'carbrand' window
			}
		});
		btnVehicleSpecifications.setForeground(new Color(2, 62, 138));
		btnVehicleSpecifications.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleSpecifications.setBackground(new Color(202, 240, 248));
		btnVehicleSpecifications.setBounds(58, 17, 398, 57);
		panel.add(btnVehicleSpecifications);

		// Add a click listener to the 'lblNewLabel' for exiting the application
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(1204, 10, 33, 36);
		panel.add(lblNewLabel);

		// Panel for title
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(202, 240, 248)); // Set background color
		panel_2.setBounds(0, 87, 1266, 90);
		frame.getContentPane().add(panel_2);

		JLabel lblDp = new JLabel("DASHBOARD");
		lblDp.setBounds(418, 19, 370, 61);
		panel_2.add(lblDp);
		lblDp.setForeground(new Color(2, 62, 138)); // Set text color
		lblDp.setBackground(new Color(114, 224, 239)); // Set background color
		lblDp.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		lblDp.setFont(new Font("Tahoma", Font.BOLD, 50)); // Set font

		// Panel for the welcome message
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(0, 119, 182)); // Set background color
		panel_3.setBounds(0, 593, 1266, 90);
		frame.getContentPane().add(panel_3);

		JLabel lblNewLabel_3 = new JLabel("WELCOME TO WHEELS4U VEHICLE RENTAL MANAGEMENT SYSTEM!!!");
		lblNewLabel_3.setBounds(0, 10, 1266, 77);
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new Color(114, 224, 239)); // Set text color
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 38)); // Set font

		// Panel for profile information
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(new Color(0, 119, 182)); // Set background color
		panel_3_1.setBounds(0, 175, 1266, 90);
		frame.getContentPane().add(panel_3_1);

		lblNewLabel_4_1 = new JLabel("Profile Name");
		lblNewLabel_4_1.setForeground(new Color(202, 240, 248)); // Set text color
		lblNewLabel_4_1.setBounds(992, 10, 242, 68);
		panel_3_1.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 40)); // Set font

		JLabel lblNewLabel_4 = new JLabel("Profile :");
		lblNewLabel_4.setForeground(new Color(202, 240, 248)); // Set text color
		lblNewLabel_4.setBounds(848, 10, 138, 68);
		panel_3_1.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 40)); // Set font

		// 'Log Out' button with action to log out and open the login window
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Successfully logged out.");
				frame.dispose(); // Close the current window
				project1.login.main(new String[] {}); // Open the login window
			}
		});

		btnLogOut.setForeground(new Color(2, 62, 138)); // Set text color
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 35)); // Set font
		btnLogOut.setBackground(new Color(202, 240, 248)); // Set background color
		btnLogOut.setBounds(43, 18, 211, 57); // Set button position and size
		panel_3_1.add(btnLogOut); // Add the 'Log Out' button to the panel
	}

	// Counts the number of rows in the 'vehicle_specifications' table and returns
	// the count
	private int countRows1() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT COUNT(*) FROM vehicle_specifications";
				ResultSet resultSet = statement.executeQuery(query);

				if (resultSet.next()) {
					int rowCount = resultSet.getInt(1);
					return rowCount;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
		return 0; // Return 0 if there was an issue or no rows found
	}

	// Counts the number of rows in the 'customers' table and returns the count
	private int countRows2() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT COUNT(*) FROM customers";
				ResultSet resultSet = statement.executeQuery(query);

				if (resultSet.next()) {
					int rowCount = resultSet.getInt(1);
					return rowCount;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
		return 0; // Return 0 if there was an issue or no rows found
	}

	// Counts the number of rows in the 'reservations' table and returns the count
	private int countRows3() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT COUNT(*) FROM reservations";
				ResultSet resultSet = statement.executeQuery(query);

				if (resultSet.next()) {
					int rowCount = resultSet.getInt(1);
					return rowCount;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
		return 0; // Return 0 if there was an issue or no rows found
	}
}