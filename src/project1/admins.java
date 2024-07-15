
package project1;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dashboard.carbrand;
import dashboard.dashboard;

//Define the AdminDetails interface
interface AdminInfo {
	int getId();

	String getName();

	String getUsername();

	String getPassword();

	int getPhone();

	String getEmail();
}

//Creating an abstract admin class that implements AdminInfo
abstract class admin implements AdminInfo {
	private int id;
	private String name;
	private String username;
	private String password;
	private int phone;
	private String email;

	// constructors
	public admin(int id, String name, String username, String password, int phone, String email) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}

	// Implement methods from PersonInfo
	// Override
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void information() {
		System.out.println("ID: " + getId());
		System.out.println("Full Name: " + getName());
		System.out.println("Username: " + getUsername());
		System.out.println("Password: " + getPassword());
		System.out.println("Phone Number: " + getPhone());
		System.out.println("Email Address: " + getEmail());
	}
}

public class admins extends JFrame {

	private JFrame frame;
	private JTextField textField_ID;
	private JTextField txtField_Fname;
	private JTextField textField_Uname;
	private JTextField textField_phoneNo;
	private JTextField textField_email;
	private JTable table_2;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private List<adminDetails> adminDetails = new ArrayList<>();
	private JPasswordField textField_pwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admins window = new admins();
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
	public admins() {
		initialize();
		populateTable();
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

		JLabel ID_lbl = new JLabel("ID:");
		ID_lbl.setForeground(new Color(202, 240, 248));
		ID_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		ID_lbl.setBounds(200, 244, 60, 29);
		frame.getContentPane().add(ID_lbl);

		JLabel FName_lbl = new JLabel("Full Name:");
		FName_lbl.setForeground(new Color(202, 240, 248));
		FName_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		FName_lbl.setBounds(114, 297, 129, 29);
		frame.getContentPane().add(FName_lbl);

		JLabel UName_lbl = new JLabel("Username:");
		UName_lbl.setForeground(new Color(202, 240, 248));
		UName_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		UName_lbl.setBounds(114, 355, 129, 29);
		frame.getContentPane().add(UName_lbl);

		JLabel pwd_lbl = new JLabel(" Password:");
		pwd_lbl.setForeground(new Color(202, 240, 248));
		pwd_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pwd_lbl.setBounds(114, 408, 129, 29);
		frame.getContentPane().add(pwd_lbl);

		JLabel phoneNo_lbl = new JLabel(" Phone no:");
		phoneNo_lbl.setForeground(new Color(202, 240, 248));
		phoneNo_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		phoneNo_lbl.setBounds(114, 467, 129, 29);
		frame.getContentPane().add(phoneNo_lbl);

		JLabel email_lbl = new JLabel(" Email:");
		email_lbl.setForeground(new Color(202, 240, 248));
		email_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		email_lbl.setBounds(154, 522, 89, 29);
		frame.getContentPane().add(email_lbl);

		textField_ID = new JTextField();
		textField_ID.setBackground(new Color(202, 240, 248));
		textField_ID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_ID.setEditable(true);
		textField_ID.setHorizontalAlignment(JTextField.CENTER);
		textField_ID.setBounds(261, 244, 215, 29);
		frame.getContentPane().add(textField_ID);
		textField_ID.setColumns(10);

		txtField_Fname = new JTextField();
		txtField_Fname.setBackground(new Color(202, 240, 248));
		txtField_Fname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtField_Fname.setBounds(260, 302, 215, 29);
		frame.getContentPane().add(txtField_Fname);
		txtField_Fname.setColumns(10);

		textField_Uname = new JTextField();
		textField_Uname.setBackground(new Color(202, 240, 248));
		textField_Uname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_Uname.setColumns(10);
		textField_Uname.setBounds(260, 360, 215, 29);
		frame.getContentPane().add(textField_Uname);

		textField_phoneNo = new JTextField();
		textField_phoneNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_phoneNo.setBackground(new Color(202, 240, 248));
		textField_phoneNo.setColumns(10);
		textField_phoneNo.setBounds(261, 467, 215, 29);
		frame.getContentPane().add(textField_phoneNo);

		textField_email = new JTextField();
		textField_email.setBackground(new Color(202, 240, 248));
		textField_email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_email.setColumns(10);
		textField_email.setBounds(261, 522, 215, 29);
		frame.getContentPane().add(textField_email);

		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				dashboard.main(new String[] {});
			}
		});
		btnDashboard.setForeground(new Color(2, 62, 138));
		btnDashboard.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnDashboard.setBackground(new Color(202, 240, 248));
		btnDashboard.setBounds(58, 10, 204, 57);
		frame.getContentPane().add(btnDashboard);

		JButton btnCustomer = new JButton("Customers");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				myProject.customerForm.main(new String[] {});
			}
		});
		btnCustomer.setForeground(new Color(2, 62, 138));
		btnCustomer.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer.setBackground(new Color(202, 240, 248));
		btnCustomer.setBounds(292, 10, 210, 57);
		frame.getContentPane().add(btnCustomer);

		JButton btnReservations = new JButton("Reservations");
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				myProject.reservationForm.main(new String[] {});
			}
		});
		btnReservations.setForeground(new Color(2, 62, 138));
		btnReservations.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnReservations.setBackground(new Color(202, 240, 248));
		btnReservations.setBounds(534, 10, 241, 57);
		frame.getContentPane().add(btnReservations);

		JButton btnVehicleSpecifications = new JButton("Vehicle Specifications");
		btnVehicleSpecifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				carbrand.main(new String[] {});
			}
		});
		btnVehicleSpecifications.setForeground(new Color(2, 62, 138));
		btnVehicleSpecifications.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleSpecifications.setBackground(new Color(202, 240, 248));
		btnVehicleSpecifications.setBounds(806, 10, 369, 57);
		frame.getContentPane().add(btnVehicleSpecifications);

		JLabel lblDp = new JLabel("ADMINS");
		lblDp.setHorizontalAlignment(SwingConstants.CENTER);
		lblDp.setForeground(new Color(2, 62, 138));
		lblDp.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblDp.setBackground(new Color(114, 224, 239));
		lblDp.setBounds(305, 94, 626, 61);
		frame.getContentPane().add(lblDp);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(202, 240, 248));
		panel_1.setBounds(0, 89, 1266, 71);
		frame.getContentPane().add(panel_1);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(504, 201, 41, 446);
		frame.getContentPane().add(separator);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(555, 244, 682, 306);
		frame.getContentPane().add(scrollPane);

		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Admin ID", "Fullname", "Username", "Password", "Phone Number", "Email Address" }));

		int nextID = getNextID();
		textField_ID.setText(Integer.toString(nextID));

		JButton btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(2, 62, 138));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmailValid(textField_email.getText()) && isPasswordValid(textField_pwd.getText())) {
					// Password is valid, proceed with adding the admin
					addAdmin();
				} else if (!isPasswordValid(textField_pwd.getText())) {
					JOptionPane.showMessageDialog(frame,
							"Invalid password.Password must contain at least 8 characters, including uppercase letters and lowercase letters");
				} else if (!isEmailValid(textField_email.getText())) {
					JOptionPane.showMessageDialog(frame, "Invalid email address.");
				}
			}
		});
		btnAdd.setBackground(new Color(144, 224, 239));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnAdd.setBounds(539, 594, 136, 44);
		frame.getContentPane().add(btnAdd);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(new Color(2, 62, 138));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmailValid(textField_email.getText()) && isPasswordValid(textField_pwd.getText())) {
					// Password is valid, proceed with adding the admin
					updateAdmin();
				} else if (!isPasswordValid(textField_pwd.getText())) {
					JOptionPane.showMessageDialog(frame,
							"Invalid password. Password must contain at least 8 characters, including uppercase letters and lowercase letters");
				} else if (!isEmailValid(textField_email.getText())) {
					JOptionPane.showMessageDialog(frame, "Invalid email address.");
				}
			}
		});
		btnUpdate.setBackground(new Color(144, 224, 239));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnUpdate.setBounds(727, 594, 136, 44);
		frame.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setForeground(new Color(2, 62, 138));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAdmin();
			}
		});
		btnDelete.setBackground(new Color(144, 224, 239));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDelete.setBounds(919, 594, 136, 44);
		frame.getContentPane().add(btnDelete);

		JButton btnClear = new JButton("CLEAR");
		btnClear.setForeground(new Color(2, 62, 138));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setBackground(new Color(144, 224, 239));
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnClear.setBounds(1106, 594, 136, 44);
		frame.getContentPane().add(btnClear);

		textField_pwd = new JPasswordField();
		textField_pwd.setBackground(new Color(202, 240, 248));
		textField_pwd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_pwd.setBounds(261, 413, 215, 29);
		frame.getContentPane().add(textField_pwd);

		JLabel lbl_X = new JLabel(" X");
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

		table_2.getColumnModel().getColumn(0).setPreferredWidth(95);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(95);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(95);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(95);
		table_2.getColumnModel().getColumn(4).setPreferredWidth(90);
		table_2.getColumnModel().getColumn(5).setPreferredWidth(95);
	}

	private void populateTable() {
		tableModel = (DefaultTableModel) table_2.getModel();
		tableModel.setRowCount(0); // Clear existing data

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM admin";
				ResultSet resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					int id = resultSet.getInt("Admin_id");
					String name = resultSet.getString("Admin_name");
					String username = resultSet.getString("Admin_username");
					String password = resultSet.getString("Admin_password");
					int phone = resultSet.getInt("Admin_phone");
					String email = resultSet.getString("Admin_email");

					adminDetails admin = new adminDetails(id, name, username, password, phone, email);
					adminDetails.add(admin);

					Vector<Object> row = new Vector<>();
					row.add(admin.getId());
					row.add(admin.getName());
					row.add(admin.getUsername());
					row.add(admin.getPassword());
					row.add(admin.getPhone());
					row.add(admin.getEmail());
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

	private void clearFields() {
		// clear text fields

		textField_ID.setText("");
		txtField_Fname.setText("");
		textField_Uname.setText("");
		textField_pwd.setText("");
		textField_phoneNo.setText("");
		textField_email.setText("");
	}

	private void addAdmin() {
		try {
			// Get data from input fields
			String name = txtField_Fname.getText();
			String username = textField_Uname.getText();
			String password = textField_pwd.getText();
			int phone = Integer.parseInt(textField_phoneNo.getText());
			String email = textField_email.getText();

			// Create an adminDetails object
			adminDetails admin = new adminDetails(0, name, username, password, phone, email);

			// Add the adminDetails object to your collection or list
			// Assuming 'adminDetails.add(admin)' adds it to a list
			adminDetails.add(admin);

			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {
					String query = "INSERT INTO admin (Admin_name, Admin_username, Admin_password, Admin_phone, Admin_email) VALUES (?, ?, ?, ?, ?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query,
							Statement.RETURN_GENERATED_KEYS);

					preparedStatement.setString(1, admin.getName());
					preparedStatement.setString(2, admin.getUsername());
					preparedStatement.setString(3, admin.getPassword());
					preparedStatement.setInt(4, admin.getPhone());
					preparedStatement.setString(5, admin.getEmail());

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
						if (generatedKeys.next()) {
							int generatedRId = generatedKeys.getInt(1); // Get the generated id

							// Update the ID field with the generated ID
							textField_ID.setText(Integer.toString(generatedRId));
							JOptionPane.showMessageDialog(frame, "Admin added successfully!");
							clearFields();
							populateTable();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to add admin.");
						}

						preparedStatement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} catch (NumberFormatException e) {
			// Handle number format exceptions (e.g., when parsing ID or phone)
			e.printStackTrace();
		}
	}

	private void deleteAdmin() {
		// Get the selected row from the table
		int selectedRow = table_2.getSelectedRow();

		// Check if a row is selected
		if (selectedRow >= 0) {
			// Get the car ID from the selected row
			int id = (int) tableModel.getValueAt(selectedRow, 0);

			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {
					String query = "DELETE FROM admin WHERE Admin_id = ?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, id);

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						// Remove the car from the 'cars' list
						adminDetails.removeIf(admin -> admin.getId() == id);

						JOptionPane.showMessageDialog(frame, "Admin deleted successfully!");
						clearFields();
						populateTable();
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to delete admin.");
					}

					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select an admin to delete.");
		}
	}

	private void updateAdmin() {
		// Get the selected row from the table
		int selectedRow = table_2.getSelectedRow();

		// Check if a row is selected
		if (selectedRow >= 0) {
			// Get the car ID from the selected row
			int id = (int) tableModel.getValueAt(selectedRow, 0);

			// Get the updated data from input fields
			String name = txtField_Fname.getText();
			String username = textField_Uname.getText();
			@SuppressWarnings("deprecation")
			String password = textField_pwd.getText();
			int phone = Integer.parseInt(textField_phoneNo.getText());
			String email = textField_email.getText();

			// Update the admin in the 'adminDetails' list
			for (adminDetails admin : adminDetails) {
				if (admin.getId() == id) {
					admin.setName(name);
					admin.setUsername(username);
					admin.setPassword(password);
					admin.setPhone(phone);
					admin.setEmail(email);
					break;
				}
			}

			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {
					String query = "UPDATE admin SET Admin_name = ?, Admin_username = ?, Admin_password = ?, Admin_phone = ?, Admin_email = ? WHERE Admin_id = ?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, name);
					preparedStatement.setString(2, username);
					preparedStatement.setString(3, password);
					preparedStatement.setInt(4, phone);
					preparedStatement.setString(5, email);
					preparedStatement.setInt(6, id);

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(frame, "Admin updated successfully!");
						clearFields();
						populateTable();
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to update admin.");
					}

					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select an admin to update.");
		}
	}

	private boolean isPasswordValid(String password) {
		// Define a regular expression pattern for a strong password
		String pass = "^(?=.[a-z])(?=.[A-Z]).{8,}$";
		Pattern pattern = Pattern.compile(pass);

		// Check if the provided password matches the pattern
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	private boolean isEmailValid(String email) {
		// Define a regular expression pattern for a valid email address
		String mail = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(mail);

		// Check if the provided email matches the pattern
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private int getNextID() {
		int nextID = 1;
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT MAX(Admin_id) FROM admin";
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