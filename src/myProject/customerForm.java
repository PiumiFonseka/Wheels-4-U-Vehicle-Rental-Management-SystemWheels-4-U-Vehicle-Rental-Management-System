package myProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import dashboard.DatabaseConnection;
import dashboard.carbrand;
import dashboard.dashboard;
import project1.admins;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Define the PersonInfo interface
interface PersonInfo {
	String getFullName();
	String getDOB();
	String getEmail();
	int getPhone();
	String getAddress();
	
	void information();

}

// Create an Person class that implements PersonInfo
abstract class Person extends JFrame implements PersonInfo {
	private int id;
	private String fullname;
	private String dob;
	private String email;
	private int phone;
	private String address;
	private String personId;

	//constructor
	public Person(int id, String fullname, String dob, String email, int phone, String address, String personId) {
		this.id = id;
		this.fullname = fullname;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.personId = personId;
	}

	// Implement methods from PersonInfo
	public String getFullName() {
		return fullname;
	}

	public String getDOB() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public int getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getPersonId() {
		return personId;
	}

	public void information() {
		System.out.println(getFullName() + " is a customer. ");
	}
}

/**
 * the customerForm class extends JFrame( class provided by the Swing
 * framework.)
 */
public class customerForm extends JFrame {
	private JFrame frame;
	private JTextField cus_name;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField cus_email;
	private JTextField cus_phone;
	private JTextField cus_address;
	private JTextField cus_id;
	private JTable table;
	private JButton btnRemove;
	private JButton btnEdit;
	private JButton btnAdd;
	private DefaultTableModel tableModel;
	private List<customer> customers = new ArrayList<>();
	private JTextField cus_dob;
	private JLabel lblNewLabel_8;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerForm window = new customerForm();
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
	public customerForm() {
		initialize();
		displayTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(2, 62, 138));
		frame.setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// header
		JButton btnVehicleSpecifications = new JButton("Dashboard");
		btnVehicleSpecifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				dashboard.main(new String[] {});
			}
		});

		JLabel lblNewLabel_6 = new JLabel("CUSTOMER DETAILS");
		lblNewLabel_6.setForeground(new Color(2, 62, 138));
		lblNewLabel_6.setBackground(Color.WHITE);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel_6.setBounds(304, 96, 572, 57);
		frame.getContentPane().add(lblNewLabel_6);

		btnVehicleSpecifications.setForeground(new Color(2, 62, 138));
		btnVehicleSpecifications.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleSpecifications.setBackground(new Color(202, 240, 248));
		btnVehicleSpecifications.setBounds(68, 10, 211, 57);
		frame.getContentPane().add(btnVehicleSpecifications);

		JButton btnCustomer = new JButton("Vehicle Specifications");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				carbrand.main(new String[] {});
			}
		});
		btnCustomer.setForeground(new Color(2, 62, 138));
		btnCustomer.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer.setBackground(new Color(202, 240, 248));
		btnCustomer.setBounds(296, 10, 383, 57);
		frame.getContentPane().add(btnCustomer);

		JButton btnReservations = new JButton("Reservations");
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				reservationForm.main(new String[] {});
			}
		});
		btnReservations.setForeground(new Color(2, 62, 138));
		btnReservations.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnReservations.setBackground(new Color(202, 240, 248));
		btnReservations.setBounds(689, 10, 242, 57);
		frame.getContentPane().add(btnReservations);

		JButton btnAdmins = new JButton("Admins");
		btnAdmins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				admins.main(new String[] {});
			}
		});
		btnAdmins.setForeground(new Color(2, 62, 138));
		btnAdmins.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnAdmins.setBackground(new Color(202, 240, 248));
		btnAdmins.setBounds(941, 10, 211, 57);
		frame.getContentPane().add(btnAdmins);

		JLabel lbl_X = new JLabel(" X");
		lbl_X.setForeground(new Color(0, 0, 0));
		lbl_X.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		lbl_X.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lbl_X.setBounds(1208, 16, 46, 44);
		frame.getContentPane().add(lbl_X);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 119, 182));
		panel.setBounds(0, 0, 1266, 90);
		frame.getContentPane().add(panel);
		// header close

		/**
		 * Taking user Inputs
		 * 
		 * ID Full Name Date of Birth Email Phone Address
		 */

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(105, 179, 45, 42);
		frame.getContentPane().add(lblNewLabel);

		cus_id = new JTextField();
		cus_id.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cus_id.setEditable(true);
		cus_id.setHorizontalAlignment(JTextField.CENTER);
		cus_id.setColumns(10);
		cus_id.setBackground(new Color(202, 240, 248));
		cus_id.setBounds(163, 183, 96, 35);
		frame.getContentPane().add(cus_id);

		JLabel lblNewLabel_1 = new JLabel("Full Name:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(44, 226, 114, 42);
		frame.getContentPane().add(lblNewLabel_1);

		cus_name = new JTextField();
		cus_name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cus_name.setBackground(new Color(202, 240, 248));
		cus_name.setBounds(168, 234, 242, 35);
		frame.getContentPane().add(cus_name);
		cus_name.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Date of Birth:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(26, 300, 127, 24);
		frame.getContentPane().add(lblNewLabel_2);

		cus_dob = new JTextField();
		cus_dob.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cus_dob.setColumns(10);
		cus_dob.setBackground(new Color(202, 240, 248));
		cus_dob.setBounds(168, 300, 242, 35);
		frame.getContentPane().add(cus_dob);

		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(81, 378, 69, 35);
		frame.getContentPane().add(lblNewLabel_3);

		cus_email = new JTextField();
		cus_email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cus_email.setColumns(10);
		cus_email.setBackground(new Color(202, 240, 248));
		cus_email.setBounds(168, 378, 242, 35);
		frame.getContentPane().add(cus_email);

		lblNewLabel_4 = new JLabel("Phone:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(76, 448, 77, 47);
		frame.getContentPane().add(lblNewLabel_4);

		cus_phone = new JTextField();
		cus_phone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cus_phone.setColumns(10);
		cus_phone.setBackground(new Color(202, 240, 248));
		cus_phone.setBounds(168, 458, 242, 35);
		frame.getContentPane().add(cus_phone);

		lblNewLabel_5 = new JLabel("Address:");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(76, 521, 77, 47);
		frame.getContentPane().add(lblNewLabel_5);

		cus_address = new JTextField();
		cus_address.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cus_address.setColumns(10);
		cus_address.setBackground(new Color(202, 240, 248));
		cus_address.setBounds(168, 526, 242, 70);
		frame.getContentPane().add(cus_address);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(598, 191, 634, 320);
		frame.getContentPane().add(scrollPane);

		// creating the Table to display Data
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "DOB", "Phone", "Email", "Address" }));

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(561, 163, 27, 498);
		frame.getContentPane().add(separator);
		
		int nextCusID = getNextCusID();
	    cus_id.setText(Integer.toString(nextCusID));

		/**
		 * Displaying buttons REMOVE Button EDIT Button ADD Button CLEAR Button
		 */

		// REMOVE Button
		btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCustomer();
			}
		});

		btnRemove.setForeground(new Color(2, 62, 138));
		btnRemove.setBackground(new Color(202, 240, 248));
		btnRemove.setFont(new Font("Arial", Font.BOLD, 30));
		btnRemove.setBounds(1055, 596, 177, 47);
		frame.getContentPane().add(btnRemove);

		// EDIT Button
		btnEdit = new JButton("EDIT");
		btnEdit.setForeground(new Color(2, 62, 138));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCustomer();
			}
		});

		btnEdit.setBackground(new Color(202, 240, 248));
		btnEdit.setFont(new Font("Arial", Font.BOLD, 30));
		btnEdit.setBounds(839, 596, 162, 47);
		frame.getContentPane().add(btnEdit);

		// ADD Button
		btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(2, 62, 138));
		btnAdd.setBackground(new Color(202, 240, 248));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validateEmail(cus_email.getText()) && validatePhoneNumber(cus_phone.getText())) {
					// Email and phone are valid, proceed to add the customer
					addCustomer();
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter valid email and phone number.");
				}
			}
		});
		{
			btnAdd.setFont(new Font("Arial", Font.BOLD, 30));
			btnAdd.setBounds(629, 596, 162, 47);
			frame.getContentPane().add(btnAdd);

			// CLEAR Button
			JButton clearCustomer = new JButton("CLEAR");
			clearCustomer.setForeground(new Color(2, 62, 138));
			clearCustomer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearbtn();
				}
			});

			clearCustomer.setFont(new Font("Arial", Font.BOLD, 25));
			clearCustomer.setBackground(new Color(202, 240, 248));
			clearCustomer.setBounds(398, 626, 127, 35);
			frame.getContentPane().add(clearCustomer);
		}

		// Getting the total count of customers in the table
		lblNewLabel_8 = new JLabel("Total Number of Customers: " + getTotalNumberOfCustomers());
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_8.setBounds(648, 520, 523, 42);
		frame.getContentPane().add(lblNewLabel_8);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(202, 240, 248));
		panel_1.setBounds(0, 86, 1266, 70);
		frame.getContentPane().add(panel_1);
	}

	// addCustomer() method is called in ADD Button
	private void addCustomer() {
		// Get data from user input
		String fullname = cus_name.getText();
		String dob = cus_dob.getText();
		int phone = Integer.parseInt(cus_phone.getText());
		String email = cus_email.getText();
		String address = cus_address.getText();

		customer cus = new customer(0, fullname, dob, phone, email, address);
		customers.add(cus);

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Enter data into Database
				String query = "INSERT INTO customers (cus_name,cus_birthdate,cus_phone,cus_email,cus_address) VALUES (?,?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setString(1, cus.getFullname());
				preparedStatement.setString(2, cus.getDob());
				preparedStatement.setInt(3, cus.getPhone());
				preparedStatement.setString(4, cus.getEmail());
				preparedStatement.setString(5, cus.getAddress());

				int rowsAffected = preparedStatement.executeUpdate();

				// Check if a row is selected
				if (rowsAffected > 0) {
					ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				    if (generatedKeys.next()) {
				        int generatedRId = generatedKeys.getInt(1); // Get the generated id

				        // Update the ID field with the generated ID
				        cus_id.setText(Integer.toString(generatedRId));
					    JOptionPane.showMessageDialog(frame, "Customer added successfully!");
					    clearbtn();
					    displayTable(); // display table with added customer details
					    updateTotalCustomerCount(); // Update the count
				} else {
					JOptionPane.showMessageDialog(frame, "Unable to add Customer.");
				}
				preparedStatement.close();

			} }catch (SQLException e) {
				e.printStackTrace();

			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}

	}

	private void displayTable() {
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0); // Clear existing data

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM customers";
				ResultSet resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					int id = resultSet.getInt("cus_id");
					String fullname = resultSet.getString("cus_name");
					String dob = resultSet.getString("cus_birthdate");
					int phone = resultSet.getInt("cus_phone");
					String email = resultSet.getString("cus_email");
					String address = resultSet.getString("cus_address");

					customer cus = new customer(id, fullname, dob, phone, email, address);
					customers.add(cus);

					Vector<Object> row = new Vector<>();
					row.add(cus.getId());
					row.add(cus.getFullname());
					row.add(cus.getDob());
					row.add(cus.getPhone());
					row.add(cus.getEmail());
					row.add(cus.getAddress());

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

	// updateCustomer() method is called in EDIT Button
	private void updateCustomer() {
		int selectedRow = table.getSelectedRow();

		// Check if a row is selected
		if (selectedRow >= 0) {

			int id = (int) tableModel.getValueAt(selectedRow, 0);

			// Get the updated data from input fields
			String fullname = cus_name.getText();
			String dob = cus_dob.getText();
			int phone = Integer.parseInt(cus_phone.getText());
			String email = cus_email.getText();
			String address = cus_address.getText();

			for (customer cus : customers) {
				if (cus.getId() == id) {

					// Update the customer data with new values
					cus.setFullname(fullname);
					cus.setDob(dob);
					cus.setPhone(phone);
					cus.setEmail(email);
					cus.setAddress(address);
					break;
				}
			}

			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {
					String query = "UPDATE customers SET cus_id=?, cus_name=?, cus_birthdate=?, cus_phone=?, cus_email=?, cus_address=? WHERE cus_id=?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, fullname);
					preparedStatement.setString(3, dob);
					preparedStatement.setInt(4, phone);
					preparedStatement.setString(5, email);
					preparedStatement.setString(6, address);
					preparedStatement.setInt(7, id); // Set the ID for WHERE condition

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(frame, "Customer updated successfully!");
						clearbtn();
						displayTable();

					} else {
						JOptionPane.showMessageDialog(frame, "Unable to update Customer.");
					}

					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select a Customer...");
		}
	}

	// removeCustomer() method is called in REMOVE Button
	private void removeCustomer() {

		int selectedRow = table.getSelectedRow();

		// Check if a row is selected
		if (selectedRow >= 0) {

			int id = (int) tableModel.getValueAt(selectedRow, 0);

			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {

					String query = "DELETE FROM customers WHERE cus_id = ?";

					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, id);

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {

						customers.removeIf(cus -> cus.getId() == id);

						JOptionPane.showMessageDialog(frame, "Deleted successfully!");
						clearbtn();
						displayTable();
						updateTotalCustomerCount(); // Update the count
					} else {
						JOptionPane.showMessageDialog(frame, "Unable to delete Customer.");
					}

					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Select a Customer...");
		}
	}

	// clearbtn() method is called in CLEAR Button
	private void clearbtn() {
		cus_id.setText("");
		cus_name.setText("");
		cus_dob.setText("");
		cus_phone.setText("");
		cus_email.setText("");
		cus_address.setText("");
	}

	// Validation method for email
	private boolean validateEmail(String email) {
		String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
		return Pattern.matches(emailPattern, email);
	}

	// Validation method for phone number
	private boolean validatePhoneNumber(String phoneNumber) {
		String phonePattern = "^[0-9]{10,}$";
		return Pattern.matches(phonePattern, phoneNumber);
	}

	private int getTotalNumberOfCustomers() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT COUNT(*) as total FROM customers";
				ResultSet resultSet = statement.executeQuery(query);

				if (resultSet.next()) {
					int totalCustomers = resultSet.getInt("total");
					resultSet.close();
					statement.close();
					return totalCustomers;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}

		return 0; // Return 0 if there's an error or no customers found
	}

	private void updateTotalCustomerCount() {
		int totalCustomers = getTotalNumberOfCustomers();
		lblNewLabel_8.setText("Total Number of Customers: " + totalCustomers);
		lblNewLabel_8.repaint(); // Force the label to repaint
	}
	
	private int getNextCusID() {
	    int nextID = 1; 
	    Connection connection = DatabaseConnection.getConnection();
	    if (connection != null) {
	        try {
	            String query = "SELECT MAX(cus_id) FROM customers";
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