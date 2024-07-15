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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

// Define the VehicleInfo interface
interface VehicleInfo {
	int getId();

	String getBrand();

	String getFuelType();

	String getColor();

	int getPassengers();

	String getGearbox();

	double getPrice();

	void information();
}

// Create an abstract Vehicle class that implements VehicleInfo
abstract class Vehicle implements VehicleInfo {
	private int id;
	private String brand;
	private String fuelType;
	private String color;
	private int passengers;
	private String gearbox;
	private double price;

	// Constructor to initialize vehicle attributes
	public Vehicle(int id, String brand, String fuelType, String color, int passengers, String gearbox, double price) {
		this.id = id;
		this.brand = brand;
		this.fuelType = fuelType;
		this.color = color;
		this.passengers = passengers;
		this.gearbox = gearbox;
		this.price = price;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getBrand() {
		return brand;
	}

	@Override
	public String getFuelType() {
		return fuelType;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public int getPassengers() {
		return passengers;
	}

	@Override
	public String getGearbox() {
		return gearbox;
	}

	@Override
	public double getPrice() {
		return price;
	}

	// Method to display vehicle information
	public void information() {
		System.out.println("ID: " + getId());
		System.out.println("Brand: " + getBrand());
		System.out.println("Fuel Type: " + getFuelType());
		System.out.println("Color: " + getColor());
		System.out.println("Passengers: " + getPassengers());
		System.out.println("Gearbox: " + getGearbox());
		System.out.println("Price: " + getPrice());
	}
}

//Car class (extends Vehicle)
class Car extends Vehicle {
	public Car(int id, String brand, String fuelType, String color, int passengers, String gearbox, double price) {
		super(id, brand, fuelType, color, passengers, gearbox, price);
	}
}

public class carbrand extends JFrame {

	private JFrame frame;
	private JTextField text_ID;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private DefaultTableModel tableModel;
	private List<cars> cars = new ArrayList<>(); // Arraylist
	private ButtonGroup fuelTypeGroup;
	private ButtonGroup gearboxGroup;
	private JRadioButton rdbtnManual;
	private JRadioButton rdbtnAuto;
	private JRadioButton rdbtnNewRadioButtonPetrol;
	private JRadioButton rdbtnNewRadioButtonDiesel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					carbrand window = new carbrand();
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
	public carbrand() {
		initialize();
		populateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		fuelTypeGroup = new ButtonGroup();
		gearboxGroup = new ButtonGroup();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 25));
		frame.getContentPane().setBackground(new Color(2, 62, 138));
		frame.getContentPane().setLayout(null);

		// Label for ID
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setForeground(new Color(202, 240, 248));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(20, 175, 73, 44);
		frame.getContentPane().add(lblNewLabel_1);

		// Label for Brand
		JLabel lblNewLabel_1_1 = new JLabel("Brand");
		lblNewLabel_1_1.setForeground(new Color(202, 240, 248));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(20, 245, 73, 44);
		frame.getContentPane().add(lblNewLabel_1_1);

		// Label for Fuel
		JLabel lblNewLabel_1_1_1 = new JLabel("Fuel");
		lblNewLabel_1_1_1.setForeground(new Color(202, 240, 248));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_1.setBounds(20, 314, 73, 44);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		// Label for Colour
		JLabel lblNewLabel_1_1_2 = new JLabel("Colour");
		lblNewLabel_1_1_2.setForeground(new Color(202, 240, 248));
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_2.setBounds(20, 384, 73, 44);
		frame.getContentPane().add(lblNewLabel_1_1_2);

		// Label for Passengers
		JLabel lblNewLabel_1_1_3 = new JLabel("Passengers");
		lblNewLabel_1_1_3.setForeground(new Color(202, 240, 248));
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_3.setBounds(20, 459, 144, 44);
		frame.getContentPane().add(lblNewLabel_1_1_3);

		// Label for Gearbox
		JLabel lblNewLabel_1_1_4 = new JLabel("Gearbox");
		lblNewLabel_1_1_4.setForeground(new Color(202, 240, 248));
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_4.setBounds(20, 534, 144, 44);
		frame.getContentPane().add(lblNewLabel_1_1_4);

		// Label for Price / Day
		JLabel lblNewLabel_1_1_5 = new JLabel("Price / Day");
		lblNewLabel_1_1_5.setForeground(new Color(202, 240, 248));
		lblNewLabel_1_1_5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_5.setBounds(20, 607, 174, 44);
		frame.getContentPane().add(lblNewLabel_1_1_5);

		// Create and set up the JTextField for ID input
		text_ID = new JTextField();
		text_ID.setForeground(new Color(2, 62, 138));
		text_ID.setBackground(new Color(202, 240, 248));
		text_ID.setEditable(true);
		text_ID.setFont(new Font("Tahoma", Font.PLAIN, 25));
		text_ID.setHorizontalAlignment(JTextField.CENTER);
		text_ID.setBounds(177, 178, 245, 40);
		frame.getContentPane().add(text_ID);
		text_ID.setColumns(10);

		// Create and set up the JTextField for Brand input
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(2, 62, 138));
		textField_1.setBackground(new Color(202, 240, 248));
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_1.setColumns(10);
		textField_1.setBounds(177, 248, 245, 40);
		frame.getContentPane().add(textField_1);

		// Create and set up the JTextField for Colour input
		textField_2 = new JTextField();
		textField_2.setForeground(new Color(2, 62, 138));
		textField_2.setBackground(new Color(202, 240, 248));
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_2.setColumns(10);
		textField_2.setBounds(177, 387, 245, 40);
		frame.getContentPane().add(textField_2);

		// Create and set up the JTextField for Passengers input
		textField_3 = new JTextField();
		textField_3.setForeground(new Color(2, 62, 138));
		textField_3.setBackground(new Color(202, 240, 248));
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_3.setColumns(10);
		textField_3.setBounds(177, 462, 245, 40);
		frame.getContentPane().add(textField_3);

		// Create and set up the JTextField for Price / Day input
		textField_4 = new JTextField();
		textField_4.setForeground(new Color(2, 62, 138));
		textField_4.setBackground(new Color(202, 240, 248));
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_4.setBounds(177, 610, 245, 40);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		// Create and set up a Radio Button for Diesel fuel type
		rdbtnNewRadioButtonDiesel = new JRadioButton("Diesel");
		rdbtnNewRadioButtonDiesel.setForeground(new Color(2, 62, 138));
		rdbtnNewRadioButtonDiesel.setBackground(new Color(202, 240, 248));
		rdbtnNewRadioButtonDiesel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rdbtnNewRadioButtonDiesel.setBounds(177, 316, 112, 40);
		frame.getContentPane().add(rdbtnNewRadioButtonDiesel);
		fuelTypeGroup.add(rdbtnNewRadioButtonDiesel);

		// Create and set up a Radio Button for Petrol fuel type
		rdbtnNewRadioButtonPetrol = new JRadioButton("Petrol");
		rdbtnNewRadioButtonPetrol.setForeground(new Color(2, 62, 138));
		rdbtnNewRadioButtonPetrol.setBackground(new Color(202, 240, 248));
		rdbtnNewRadioButtonPetrol.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rdbtnNewRadioButtonPetrol.setBounds(315, 316, 107, 40);
		frame.getContentPane().add(rdbtnNewRadioButtonPetrol);
		fuelTypeGroup.add(rdbtnNewRadioButtonPetrol);

		// Create and set up a Radio Button for Manual gearbox
		rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setForeground(new Color(2, 62, 138));
		rdbtnManual.setBackground(new Color(202, 240, 248));
		rdbtnManual.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rdbtnManual.setBounds(182, 536, 107, 40);
		frame.getContentPane().add(rdbtnManual);
		gearboxGroup.add(rdbtnManual);

		// Create and set up a Radio Button for Automatic gearbox
		rdbtnAuto = new JRadioButton("Auto");
		rdbtnAuto.setForeground(new Color(2, 62, 138));
		rdbtnAuto.setBackground(new Color(202, 240, 248));
		rdbtnAuto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rdbtnAuto.setBounds(315, 536, 107, 40);
		frame.getContentPane().add(rdbtnAuto);
		gearboxGroup.add(rdbtnAuto);

		// Get the next available car ID and set it in the text field
		int nextCarID = getNextCarID();
		text_ID.setText(Integer.toString(nextCarID));

		// Create and set up the "ADD" button
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setForeground(new Color(2, 62, 138));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCar();
			}
		});

		btnNewButton.setBackground(new Color(202, 240, 248));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(523, 607, 132, 44);
		frame.getContentPane().add(btnNewButton);

		// Create and set up the "UPDATE" button
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(new Color(2, 62, 138));
		btnUpdate.setBackground(new Color(202, 240, 248));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnUpdate.setBounds(704, 607, 132, 44);
		frame.getContentPane().add(btnUpdate);

		// Add an action listener for the "UPDATE" button
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCar(); // Call the updateCar() method
			}
		});

		// Create and set up the "DELETE" button
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setForeground(new Color(2, 62, 138));
		btnDelete.setBackground(new Color(202, 240, 248));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDelete.setBounds(881, 607, 132, 44);
		frame.getContentPane().add(btnDelete);

		// Add an action listener for the "DELETE" button
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCar(); // Call the deleteCar() method
			}
		});

		// Create and set up the "CLEAR" button
		JButton btnClear = new JButton("CLEAR");
		btnClear.setForeground(new Color(2, 62, 138));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});

		btnClear.setBackground(new Color(202, 240, 248));
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnClear.setBounds(1050, 607, 125, 44);
		frame.getContentPane().add(btnClear);

		// Create and set up the "Dashboard" button
		JButton btnVehicleSpecifications = new JButton("Dashboard");
		btnVehicleSpecifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				dashboard.main(new String[] {}); // Open the dashboard
			}
		});

		// Create and set up the "Dashboard" button
		btnVehicleSpecifications.setForeground(new Color(2, 62, 138));
		btnVehicleSpecifications.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleSpecifications.setBackground(new Color(202, 240, 248));
		btnVehicleSpecifications.setBounds(68, 10, 275, 57);
		frame.getContentPane().add(btnVehicleSpecifications);

		// Create and set up the "Customers" button
		JButton btnCustomer = new JButton("Customers");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				myProject.customerForm.main(new String[] {});
			}
		});

		btnCustomer.setForeground(new Color(2, 62, 138));
		btnCustomer.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer.setBackground(new Color(202, 240, 248));
		btnCustomer.setBounds(389, 10, 211, 57);
		frame.getContentPane().add(btnCustomer);

		// Create and set up the "Reservations" button
		JButton btnReservations = new JButton("Reservations");
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				myProject.reservationForm.main(new String[] {});
			}
		});

		btnReservations.setForeground(new Color(2, 62, 138));
		btnReservations.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnReservations.setBackground(new Color(202, 240, 248));
		btnReservations.setBounds(657, 10, 241, 57);
		frame.getContentPane().add(btnReservations);

		// Create and set up the "Admins" button
		JButton btnAdmins = new JButton("Admins");
		btnAdmins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				project1.admins.main(new String[] {});
			}
		});

		btnAdmins.setForeground(new Color(2, 62, 138));
		btnAdmins.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnAdmins.setBackground(new Color(202, 240, 248));
		btnAdmins.setBounds(941, 10, 211, 57);
		frame.getContentPane().add(btnAdmins);

		// Create and set up a label for "VEHICLE SPECIFICATIONS"
		JLabel lblDp = new JLabel("VEHICLE SPECIFICATIONS");
		lblDp.setHorizontalAlignment(SwingConstants.CENTER);
		lblDp.setForeground(new Color(2, 62, 138));
		lblDp.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblDp.setBackground(new Color(114, 224, 239));
		lblDp.setBounds(302, 94, 683, 61);
		frame.getContentPane().add(lblDp);

		// Create a panel for the header background
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(202, 240, 248));
		panel_1.setBounds(0, 81, 1266, 84);
		frame.getContentPane().add(panel_1);

		// Create a label for closing the window
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		lblNewLabel.setBounds(1220, 10, 21, 43);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));

		// Create a panel for the top header background
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 119, 182));
		panel.setBounds(0, 0, 1266, 84);
		frame.getContentPane().add(panel);

		// Create a scroll pane for the table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(465, 181, 776, 406);
		frame.getContentPane().add(scrollPane);

		// Create a JTable for displaying vehicle data
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "vehi_id", "vehi_brand", "vehi_fuel",
				"vehi_colour", "vehi_passengers", "vehi_gearbox", "vehi_price" }));

		// Create a vertical separator
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(443, 175, 5, 498);
		frame.getContentPane().add(separator);

		// Set the dimensions and close operation for the main frame
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//Populate the table with data from the database
	private void populateTable() {
		// Get the table model and clear any existing data
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0); // Clear existing data

		// Establish a database connection
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Create a SQL statement and query the database
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM vehicle_specifications";
				ResultSet resultSet = statement.executeQuery(query);

				// Iterate through the result set and populate the JTable
				while (resultSet.next()) {
					int id = resultSet.getInt("vehi_id");
					String brand = resultSet.getString("vehi_brand");
					String fuelType = resultSet.getString("vehi_fuel");
					String color = resultSet.getString("vehi_colour");
					int passengers = resultSet.getInt("vehi_passengers");
					String gearbox = resultSet.getString("vehi_gearbox");
					double price = resultSet.getDouble("vehi_price");

					// Create a 'cars' object with retrieved data
					cars car = new cars(id, brand, fuelType, color, passengers, gearbox, price);
					cars.add(car);

					// Create a vector to add data to the table model
					Vector<Object> row = new Vector<>();
					row.add(car.getId());
					row.add(car.getBrand());
					row.add(car.getFuelType());
					row.add(car.getColor());
					row.add(car.getPassengers());
					row.add(car.getGearbox());
					row.add(car.getPrice());

					// Add the row to the table model
					tableModel.addRow(row);
				}

				// Close the result set and statement
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// Close the database connection
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

//Custom checked exception and unchecked exception classes
	public interface MyCheckedExceptionInfo {
		String getCustomMessage();
	}

	public class MyCustomCheckedException extends Exception implements MyCheckedExceptionInfo {
		private String customMessage;

		public MyCustomCheckedException() {
			super("This is a custom checked exception.");
			customMessage = "This is a custom checked exception.";
		}

		public MyCustomCheckedException(String message) {
			super(message);
			customMessage = message;
		}

		@Override
		public String getCustomMessage() {
			return customMessage;
		}
	}

	public interface MyUncheckedExceptionInfo {
		String getCustomMessage();
	}

	public class MyCustomUncheckedException extends RuntimeException implements MyUncheckedExceptionInfo {
		private String customMessage;

		public MyCustomUncheckedException() {
			super("This is a custom unchecked exception.");
			customMessage = "This is a custom unchecked exception.";
		}

		public MyCustomUncheckedException(String message) {
			super(message);
			customMessage = message;
		}

		@Override
		public String getCustomMessage() {
			return customMessage;
		}
	}

//Clear input fields and radio button selections
	private void clearFields() {
		// Clear input fields
		text_ID.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");

		// Clear radio button selection
		fuelTypeGroup.clearSelection();
		gearboxGroup.clearSelection();
	}

	// Add a new car to the database and update the JTable
	private void addCar() {
		// Get data from input fields
		String brand = textField_1.getText();
		String fuelType = getSelectedFuelType();
		String color = textField_2.getText();
		String passengersText = textField_3.getText();
		String gearbox = getSelectedGearbox();
		String priceText = textField_4.getText();

		// Check if any of the fields are empty
		if (brand.isEmpty() || fuelType.isEmpty() || color.isEmpty() || passengersText.isEmpty() || gearbox.isEmpty()
				|| priceText.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Please fill in all the details.");
			return; // Exit the method to prevent further execution
		}

		int passengers = Integer.parseInt(passengersText);
		double price = Double.parseDouble(priceText);

		// Create a 'cars' object with the entered data
		cars car = new cars(0, brand, fuelType, color, passengers, gearbox, price);
		cars.add(car);

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Insert the car data into the database
				String query = "INSERT INTO vehicle_specifications (vehi_brand, vehi_fuel, vehi_colour, vehi_passengers, vehi_gearbox, vehi_price) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);

				preparedStatement.setString(1, car.getBrand());
				preparedStatement.setString(2, car.getFuelType());
				preparedStatement.setString(3, car.getColor());
				preparedStatement.setInt(4, car.getPassengers());
				preparedStatement.setString(5, car.getGearbox());
				preparedStatement.setDouble(6, car.getPrice());

				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
					if (generatedKeys.next()) {
						int generatedRId = generatedKeys.getInt(1); // Get the generated id

						// Update the ID field with the generated ID
						text_ID.setText(Integer.toString(generatedRId));
						JOptionPane.showMessageDialog(frame, "Vehicle added successfully!");
						clearFields();
						populateTable();
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to add the vehicle.");
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

	// Get the selected gearbox type (Manual or Auto) based on radio button
	// selection
	private String getSelectedGearbox() {
		if (rdbtnManual.isSelected()) {
			return "Manual";
		} else if (rdbtnAuto.isSelected()) {
			return "Auto";
		} else {
			return "";
		}
	}

	// Get the selected fuel type (Diesel or Petrol) based on radio button selection
	private String getSelectedFuelType() {
		if (rdbtnNewRadioButtonDiesel.isSelected()) {
			return "Diesel";
		} else if (rdbtnNewRadioButtonPetrol.isSelected()) {
			return "Petrol";
		} else {
			return "";
		}
	}

	// Delete a selected car from the database and update the JTable
	private void deleteCar() {
		// Get the selected row from the table
		int selectedRow = table.getSelectedRow();

		// Check if a row is selected
		if (selectedRow >= 0) {
			// Get the car ID from the selected row
			int id = (int) tableModel.getValueAt(selectedRow, 0);

			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {
					// Delete the car from the database based on its ID
					String query = "DELETE FROM vehicle_specifications WHERE vehi_id = ?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, id);

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						// Remove the car from the 'cars' list
						cars.removeIf(car -> car.getId() == id);

						JOptionPane.showMessageDialog(frame, "Vehicle deleted successfully!");
						clearFields();
						populateTable();
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to delete the vehicle.");
					}

					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select a vehicle to delete.");
		}
	}

	// Update the selected car's data in the database and JTable
	private void updateCar() {
		// Get the selected row from the table
		int selectedRow = table.getSelectedRow();

		// Check if a row is selected
		if (selectedRow >= 0) {
			// Get the car ID from the selected row
			int id = (int) tableModel.getValueAt(selectedRow, 0);

			// Get the updated data from input fields
			String brand = textField_1.getText();
			String fuelType = getSelectedFuelType();
			String color = textField_2.getText();
			String passengersText = textField_3.getText();
			String gearbox = getSelectedGearbox();
			String priceText = textField_4.getText();

			// Check if any of the fields are empty
			if (brand.isEmpty() || fuelType.isEmpty() || color.isEmpty() || passengersText.isEmpty()
					|| gearbox.isEmpty() || priceText.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please fill in all the details.");
				return; // Exit the method to prevent further execution
			}

			int passengers = Integer.parseInt(passengersText);
			double price = Double.parseDouble(priceText);

			// Update the car in the 'cars' list
			for (cars car : cars) {
				if (car.getId() == id) {
					car.setBrand(brand);
					car.setFuelType(fuelType);
					car.setColor(color);
					car.setPassengers(passengers);
					car.setGearbox(gearbox);
					car.setPrice(price);
					break;
				}
			}

			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {
					// Update the car data in the database
					String query = "UPDATE vehicle_specifications SET vehi_brand = ?, vehi_fuel = ?, vehi_colour = ?, vehi_passengers = ?, vehi_gearbox = ?, vehi_price = ? WHERE vehi_id = ?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, brand);
					preparedStatement.setString(2, fuelType);
					preparedStatement.setString(3, color);
					preparedStatement.setInt(4, passengers);
					preparedStatement.setString(5, gearbox);
					preparedStatement.setDouble(6, price);
					preparedStatement.setInt(7, id);

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(frame, "Vehicle updated successfully!");
						clearFields();
						populateTable();
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to update the vehicle.");
					}

					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select a vehicle to update.");
		}
	}

	// Get the next available car ID by finding the maximum existing ID and adding 1
	private int getNextCarID() {
		int nextID = 1;
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT MAX(vehi_id) FROM vehicle_specifications";
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