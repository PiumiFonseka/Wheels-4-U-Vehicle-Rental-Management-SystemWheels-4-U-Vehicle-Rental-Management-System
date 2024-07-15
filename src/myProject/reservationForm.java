package myProject;

//Import necessary Java libraries
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dashboard.DatabaseConnection;
import dashboard.carbrand;
import dashboard.dashboard;
import project1.admins;

public class reservationForm {

	private JFrame frame;
	private JTextField pick_address;
	private JTextField pick_time;
	private JTextField return_address;
	private JTextField return_time;
	private JTextField pick_date;
	private JTextField return_date;
	private JTextField rID;
	private DefaultTableModel tableModel;

	private List<reservation> reservation = new ArrayList<>();
	private JComboBox<String> select_cus;
	private JComboBox<String> select_veh;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Create an instance of the reservationForm window and make it visible
					reservationForm window = new reservationForm();
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
	public reservationForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Create the main application frame
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(2, 62, 138));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Create a button for navigating to the Dashboard section
		JButton btnVehicleSpecifications = new JButton("Dashboard");
		btnVehicleSpecifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				dashboard.main(new String[] {}); // Open the Dashboard
			}
		});
		btnVehicleSpecifications.setForeground(new Color(2, 62, 138));
		btnVehicleSpecifications.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleSpecifications.setBackground(new Color(202, 240, 248));
		btnVehicleSpecifications.setBounds(68, 10, 211, 57);
		frame.getContentPane().add(btnVehicleSpecifications);

		// Create a button for navigating to the Vehicle Specifications section
		JButton btnCustomer = new JButton("Vehicle Specifications");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				carbrand.main(new String[] {}); // Open the Vehicle Specifications
			}
		});
		btnCustomer.setForeground(new Color(2, 62, 138));
		btnCustomer.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer.setBackground(new Color(202, 240, 248));
		btnCustomer.setBounds(296, 10, 383, 57);
		frame.getContentPane().add(btnCustomer);

		// Create a button for navigating to the Customers section
		JButton btnReservations = new JButton("Customers");
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				customerForm.main(new String[] {}); // Open the Customers section
			}
		});
		btnReservations.setForeground(new Color(2, 62, 138));
		btnReservations.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnReservations.setBackground(new Color(202, 240, 248));
		btnReservations.setBounds(689, 10, 242, 57);
		frame.getContentPane().add(btnReservations);

		// Create a button for navigating to the Admins section
		JButton btnAdmins = new JButton("Admins");
		btnAdmins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				admins.main(new String[] {}); // Open the Admins section
			}
		});
		btnAdmins.setForeground(new Color(2, 62, 138));
		btnAdmins.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnAdmins.setBackground(new Color(202, 240, 248));
		btnAdmins.setBounds(941, 10, 211, 57);
		frame.getContentPane().add(btnAdmins);

		// Create a label for closing the frame
		JLabel close = new JLabel("X");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0); // Close the application when the "X" is clicked
			}
		});
		close.setBounds(1220, 10, 21, 43);
		frame.getContentPane().add(close);
		close.setFont(new Font("Tahoma", Font.PLAIN, 35));

		// Create a vertical separator
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(631, 165, 48, 265);
		frame.getContentPane().add(separator);

		// Create another horizontal separator
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(27, 440, 1214, 17);
		frame.getContentPane().add(separator_2);

		// Create a label for "Reservation ID"
		JLabel lblNewLabel_3_4 = new JLabel("Reservation ID:");
		lblNewLabel_3_4.setForeground(new Color(202, 240, 248));
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3_4.setBounds(27, 165, 200, 35);
		frame.getContentPane().add(lblNewLabel_3_4);

		// Create a label for "Vehicle Model"
		JLabel lblNewLabel_1 = new JLabel("Vehicle Model:");
		lblNewLabel_1.setForeground(new Color(202, 240, 248));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(27, 218, 234, 41);
		frame.getContentPane().add(lblNewLabel_1);

		// Create a label for "Customer Name"
		JLabel lblNewLabel_1_1 = new JLabel("Customer Name:");
		lblNewLabel_1_1.setForeground(new Color(202, 240, 248));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(27, 276, 234, 41);
		frame.getContentPane().add(lblNewLabel_1_1);

		// Create a label for "Pick Up Details"
		JLabel lblNewLabel_1_2 = new JLabel("Pick Up Details");
		lblNewLabel_1_2.setForeground(new Color(202, 240, 248));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_2.setBounds(821, 165, 234, 41);
		frame.getContentPane().add(lblNewLabel_1_2);

		// Create a label for "Return Details"
		JLabel lblNewLabel_1_2_1 = new JLabel("Return Details");
		lblNewLabel_1_2_1.setForeground(new Color(202, 240, 248));
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_2_1.setBounds(1038, 165, 184, 41);
		frame.getContentPane().add(lblNewLabel_1_2_1);

		// Create a JTextField for entering "Reservation ID"
		rID = new JTextField();
		rID.setBackground(new Color(202, 240, 248));
		rID.setEditable(true);
		rID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rID.setHorizontalAlignment(JTextField.CENTER);
		rID.setColumns(10);
		rID.setBounds(237, 165, 129, 35);
		frame.getContentPane().add(rID);

		// Create a JTextField for entering "Pick Up Address"
		pick_address = new JTextField();
		pick_address.setBackground(new Color(202, 240, 248));
		pick_address.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pick_address.setColumns(10);
		pick_address.setBounds(785, 215, 218, 35);
		frame.getContentPane().add(pick_address);

		// Create a JTextField for entering "Pick Up Time"
		pick_time = new JTextField();
		pick_time.setBackground(new Color(202, 240, 248));
		pick_time.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pick_time.setColumns(10);
		pick_time.setBounds(785, 260, 218, 35);
		frame.getContentPane().add(pick_time);

		// Create a JTextField for entering "Return Address"
		return_address = new JTextField();
		return_address.setBackground(new Color(202, 240, 248));
		return_address.setFont(new Font("Tahoma", Font.PLAIN, 20));
		return_address.setColumns(10);
		return_address.setBounds(1023, 215, 218, 35);
		frame.getContentPane().add(return_address);

		// Create a JTextField for entering "Return Time"
		return_time = new JTextField();
		return_time.setBackground(new Color(202, 240, 248));
		return_time.setFont(new Font("Tahoma", Font.PLAIN, 20));
		return_time.setColumns(10);
		return_time.setBounds(1023, 260, 218, 35);
		frame.getContentPane().add(return_time);

		// Create labels for "Address," "Time," and "Date" for Pick Up Details
		JLabel lblNewLabel_3 = new JLabel("Address:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_3.setBounds(695, 217, 80, 35);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Time:");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(721, 262, 54, 35);
		frame.getContentPane().add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("Date:");
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_3_2.setBounds(721, 307, 54, 35);
		frame.getContentPane().add(lblNewLabel_3_2);

		// Get the next available Reservation ID and set it in the rID field
		int nextReservationID = getNextR_ID();
		rID.setText(Integer.toString(nextReservationID));

		// Create buttons for adding, editing, clearing, and deleting reservations
		// ADD button
		JButton addReservation = new JButton("ADD");
		addReservation.setForeground(new Color(2, 62, 138));
		addReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addReservation(); // Calls the addReservation method when clicked
			}
		});
		addReservation.setBackground(new Color(202, 240, 248));
		addReservation.setFont(new Font("Arial", Font.BOLD, 20));
		addReservation.setBounds(672, 374, 103, 49);
		frame.getContentPane().add(addReservation);

		// EDIT button
		JButton editReservation = new JButton("EDIT");
		editReservation.setForeground(new Color(2, 62, 138));
		editReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateReservation(); // Calls the updateReservation method when clicked
			}
		});
		editReservation.setBackground(new Color(202, 240, 248));
		editReservation.setFont(new Font("Arial", Font.BOLD, 20));
		editReservation.setBounds(1144, 374, 97, 49);
		frame.getContentPane().add(editReservation);

		// CLEAR button
		JButton clearReservation = new JButton("CLEAR");
		clearReservation.setForeground(new Color(2, 62, 138));
		clearReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearReservation(); // Calls the clearReservation method when clicked
			}
		});
		clearReservation.setBackground(new Color(202, 240, 248));
		clearReservation.setFont(new Font("Arial", Font.BOLD, 20));
		clearReservation.setBounds(821, 374, 122, 49);
		frame.getContentPane().add(clearReservation);

		// Pick Up Date text field
		pick_date = new JTextField();
		pick_date.setBackground(new Color(202, 240, 248));
		pick_date.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pick_date.setColumns(10);
		pick_date.setBounds(785, 305, 218, 35);
		frame.getContentPane().add(pick_date);

		// Return Date text field
		return_date = new JTextField();
		return_date.setBackground(new Color(202, 240, 248));
		return_date.setFont(new Font("Tahoma", Font.PLAIN, 20));
		return_date.setColumns(10);
		return_date.setBounds(1023, 305, 218, 35);
		frame.getContentPane().add(return_date);

		// Create a scroll pane for displaying reservation data
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 455, 1216, 163);
		frame.getContentPane().add(scrollPane);

		// Create a table to display reservation details
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Reservation ID", "Vehicle Model", "Customer Name", "PickUp Address", "PickUp Time",
						"PickUp Date", "Return Address", "Return Time", "Return Date", "Days", "Payment" }));

		// DELETE button
		JButton deleteReservation = new JButton("DELETE");
		deleteReservation.setForeground(new Color(2, 62, 138));
		deleteReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteReservation(); // Calls the deleteReservation method when clicked
			}
		});
		deleteReservation.setFont(new Font("Arial", Font.BOLD, 20));
		deleteReservation.setBackground(new Color(202, 240, 248));
		deleteReservation.setBounds(981, 374, 117, 49);
		frame.getContentPane().add(deleteReservation);

		// Create a panel for the header
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(0, 119, 182));
		headerPanel.setBounds(0, 0, 1266, 90);
		frame.getContentPane().add(headerPanel);

		// Label for additional payment and damage information
		JLabel additionalInfoLabel = new JLabel(
				"Payment should be done once the vehicle is returned. If the car sustains any damage, there will be additional costs.");
		additionalInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		additionalInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		additionalInfoLabel.setForeground(new Color(202, 240, 248));
		additionalInfoLabel.setBounds(0, 620, 1266, 53);
		frame.getContentPane().add(additionalInfoLabel);

		// Create a panel for the title
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(202, 240, 248));
		titlePanel.setBounds(0, 89, 1266, 66);
		frame.getContentPane().add(titlePanel);

		// Label for the title
		JLabel titleLabel = new JLabel("MAKE A RESERVATION");
		titlePanel.add(titleLabel);
		titleLabel.setBackground(new Color(202, 240, 248));
		titleLabel.setForeground(new Color(2, 62, 138));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 50));

		// Create a drop-down list to select a customer from the customer list
		select_cus = new JComboBox();
		select_cus.setBackground(new Color(202, 240, 248));
		select_cus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		select_cus.setBounds(238, 276, 242, 35);
		frame.getContentPane().add(select_cus);
		fillCusComboBox(); // Populate the customer drop-down list

		// Create a drop-down list to select a vehicle from the vehicle list
		select_veh = new JComboBox();
		select_veh.setBackground(new Color(202, 240, 248));
		select_veh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		select_veh.setBounds(238, 219, 242, 35);
		frame.getContentPane().add(select_veh);
		fillVehiComboBox(); // Populate the vehicle drop-down list

		// Button to navigate to the Customer Table
		JButton btnCustomerTable = new JButton("Customer Table");
		btnCustomerTable.setBackground(new Color(202, 240, 248));
		btnCustomerTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				customerForm.main(new String[] {});
			}
		});
		btnCustomerTable.setBounds(490, 282, 130, 28);
		frame.getContentPane().add(btnCustomerTable);

		// Button to navigate to the Vehicle Table
		JButton btnVehicleTable = new JButton("Vehicle Table");
		btnVehicleTable.setBackground(new Color(202, 240, 248));
		btnVehicleTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				carbrand.main(new String[] {});
			}
		});
		btnVehicleTable.setBounds(490, 224, 130, 28);
		frame.getContentPane().add(btnVehicleTable);

		// Label for the number of days
		JLabel lblNumberOfDays = new JLabel("No. of days:");
		lblNumberOfDays.setForeground(new Color(202, 240, 248));
		lblNumberOfDays.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNumberOfDays.setBounds(27, 334, 200, 35);
		frame.getContentPane().add(lblNumberOfDays);

		// Text field for entering the number of days
		textField = new JTextField();
		textField.setBackground(new Color(202, 240, 248));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(237, 336, 129, 35);
		frame.getContentPane().add(textField);

		// Label for the payment
		JLabel lblPayment = new JLabel("Payment:");
		lblPayment.setForeground(new Color(202, 240, 248));
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPayment.setBounds(27, 395, 200, 35);
		frame.getContentPane().add(lblPayment);

		// Text field for displaying the payment amount
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(202, 240, 248));
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(237, 395, 129, 35);
		// Make the text field read-only
		textField_1.setEditable(false);
		frame.getContentPane().add(textField_1);

		// Call the displayTable method to populate the JTable with reservation details
		displayTable();
	}

	// clear the fields
	private void clearReservation() {
		rID.setText("");
		pick_address.setText("");
		pick_time.setText("");
		pick_date.setText("");
		return_address.setText("");
		return_time.setText("");
		return_date.setText("");
		textField_1.setText("");
		textField.setText("");

	}

	// Method to add a reservation when the ADD button is clicked
	private void addReservation() {
		// Get data from input fields
		String selectedVehicleName = (String) select_veh.getSelectedItem();
		String selectedCustomerName = (String) select_cus.getSelectedItem();
		String pAddress = pick_address.getText();
		String pTime = pick_time.getText();
		String pDate = pick_date.getText();
		String rAddress = return_address.getText();
		String rTime = return_time.getText();
		String rDate = return_date.getText();
		int days = Integer.parseInt(textField.getText());
		double price = getVehiclePrice(selectedVehicleName);
		double payment = price * days;
		textField_1.setText(String.valueOf(payment));

		// Create a reservation object with the entered data
		reservation res = new reservation(0, selectedVehicleName, selectedCustomerName, pAddress, pTime, pDate,
				rAddress, rTime, rDate, days, payment);

		// Establish a database connection
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// SQL query to insert a new reservation into the database
				String query = "INSERT INTO reservations (vehi_model, cus_name, pick_address, pick_time, pick_date, ret_address, ret_time,ret_date,days,payment) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);

				// Set values in the prepared statement
				preparedStatement.setString(3, res.getPick_address());
				preparedStatement.setString(4, res.getPick_time());
				preparedStatement.setString(5, res.getPick_date());
				preparedStatement.setString(6, res.getReturn_address());
				preparedStatement.setString(7, res.getReturn_time());
				preparedStatement.setString(8, res.getReturn_date());
				preparedStatement.setInt(9, res.getDays());
				preparedStatement.setDouble(10, res.getPayment());

				// Update the vehicle model and customer name in the reservation
				res.setcName(selectedVehicleName);
				preparedStatement.setString(1, selectedVehicleName); // Include selected customer name in the update
																		// query

				res.setcName(selectedCustomerName);
				preparedStatement.setString(2, selectedCustomerName); // Include selected customer name in the update
																		// query

				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
					if (generatedKeys.next()) {
						int generatedRId = generatedKeys.getInt(1); // Get the generated r_id

						// Update the rID field with the generated ID
						rID.setText(Integer.toString(generatedRId));
						JOptionPane.showMessageDialog(frame, "Reservation added successfully!");
						clearReservation();
						displayTable();
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to add Reservation.");
					}

					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

	// Method to populate the JTable with reservation details
	void displayTable() {
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM reservations";
				ResultSet resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					int id = resultSet.getInt("r_id");
					String model = resultSet.getString("vehi_model");
					String cusname = resultSet.getString("cus_name");
					String paddress = resultSet.getString("pick_address");
					String ptime = resultSet.getString("pick_time");
					String pdate = resultSet.getString("pick_date");
					String raddress = resultSet.getString("ret_address");
					String rtime = resultSet.getString("ret_time");
					String rdate = resultSet.getString("ret_date");
					int days = resultSet.getInt("days");
					double payment = resultSet.getDouble("payment");

					// Create a reservation object
					reservation res = new reservation(id, model, cusname, paddress, ptime, pdate, raddress, rtime,
							rdate, days, payment);
					reservation.add(res);

					// Create a vector containing reservation details
					Vector<Object> row = new Vector<>();
					row.add(res.getRid());
					row.add(res.getvModel());
					row.add(res.getcName());
					row.add(res.getPick_address());
					row.add(res.getPick_time());
					row.add(res.getPick_date());
					row.add(res.getReturn_address());
					row.add(res.getReturn_time());
					row.add(res.getReturn_date());
					row.add(res.getDays());
					row.add(res.getPayment());

					// Add the row to the table model
					tableModel.addRow(row);
				}

				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

	// Method to delete a reservation when the DELETE button is clicked
	private void deleteReservation() {
		int selectedRow = table.getSelectedRow();

		if (selectedRow >= 0) {
			// Get the reservation ID from the selected row
			int id = (int) tableModel.getValueAt(selectedRow, 0);

			// Establish a database connection
			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {
					// SQL query to delete a reservation based on its ID
					String query = "DELETE FROM reservations WHERE r_id = ?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, id);

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						// Remove the reservation from the local list
						reservation.removeIf(res -> res.getRid() == id);

						JOptionPane.showMessageDialog(frame, "Reservation deleted successfully!");
						displayTable();
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to delete reservation.");
					}

					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Select a reservation to delete.");
		}
	}

	// Method to update a reservation when the EDIT button is clicked
	private void updateReservation() {
		int selectedRow = table.getSelectedRow();

		// Check if a row is selected
		if (selectedRow >= 0) {
			// Get the reservation ID from the selected row
			int id = (int) tableModel.getValueAt(selectedRow, 0);

			// Get the updated data from input fields
			String paddress = pick_address.getText();
			String ptime = pick_time.getText();
			String pdate = pick_date.getText();
			String raddress = return_address.getText();
			String rtime = return_time.getText();
			String rdate = return_date.getText();
			String selectedVehicleName = (String) select_veh.getSelectedItem(); // Assuming select_vehicle is the
																				// JComboBox for vehicles
			String selectedCustomerName = (String) select_cus.getSelectedItem(); // Assuming select_cus is the JComboBox
																					// for customers

			int days = Integer.parseInt(textField.getText());
			double price = getVehiclePrice(selectedVehicleName);
			double payment = price * days;

			// Find and update the reservation in the local list
			for (reservation res : reservation) {
				if (res.getRid() == id) {
					res.setcName(selectedVehicleName); // Set the vehicle name directly
					res.setcName(selectedCustomerName); // Set the customer name directly
					res.setPick_address(paddress);
					res.setPick_time(ptime);
					res.setPick_date(pdate);
					res.setReturn_address(raddress);
					res.setReturn_time(rtime);
					res.setReturn_date(rdate);
					res.setDays(days);
					res.setPayment(payment);
					break;
				}
			}

			// Establish a database connection
			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {
					// SQL query to update the reservation based on its ID
					String query = "UPDATE reservations SET vehi_model=?, cus_name=?, pick_address=?, pick_time=?, pick_date=?, ret_address=?, ret_time=?, ret_date=?, days=?, payment=? WHERE r_id=?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, selectedVehicleName); // vehi_model
					preparedStatement.setString(2, selectedCustomerName); // cus_name
					preparedStatement.setString(3, paddress);
					preparedStatement.setString(4, ptime);
					preparedStatement.setString(5, pdate);
					preparedStatement.setString(6, raddress);
					preparedStatement.setString(7, rtime);
					preparedStatement.setString(8, rdate);
					preparedStatement.setInt(9, days);
					preparedStatement.setDouble(10, payment);
					preparedStatement.setInt(11, id);

					// Update the vehicle model & customer name in the reservation
					for (reservation res : reservation) {
						if (res.getRid() == id) {
							res.setcName(selectedVehicleName);
							preparedStatement.setString(1, selectedVehicleName); // Include selected vehicle name in the
																					// update query
							res.setcName(selectedCustomerName);
							preparedStatement.setString(2, selectedCustomerName); // Include selected customer name in
																					// the update query
							break;
						}
					}

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(frame, "Reservation updated successfully!");
						clearReservation();
						displayTable();
					} else {
						JOptionPane.showMessageDialog(frame, "Unable to update Reservation.");
					}

					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select a Reservation...");
		}
	}

	// Fill the Customer selection dropdown list with customer names from the
	// database
	public void fillCusComboBox() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT * FROM customers";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery(query);

				while (resultSet.next()) {
					select_cus.addItem(resultSet.getString("cus_name"));
				}

				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

	// Fill the Vehicle selection dropdown list with vehicle brands from the
	// database
	public void fillVehiComboBox() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT * FROM vehicle_specifications";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery(query);

				while (resultSet.next()) {
					select_veh.addItem(resultSet.getString("vehi_brand"));
				}

				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

	// Get the price of the selected vehicle based on its brand
	private double getVehiclePrice(String selectedBrand) {
		Connection connection = DatabaseConnection.getConnection();
		double price = 0.0;

		if (connection != null) {
			try {
				String query = "SELECT vehi_price FROM vehicle_specifications WHERE vehi_brand = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, selectedBrand);
				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					price = resultSet.getDouble("vehi_price");
				}

				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}

		return price;
	}

	// Get the next available reservation ID by finding the maximum existing ID and
	// incrementing it
	private int getNextR_ID() {
		int nextID = 1; // Default value if no reservations exist
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT MAX(r_id) FROM reservations";
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				if (resultSet.next()) {
					nextID = resultSet.getInt(1) + 1;
				}
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
		return nextID;
	}
}