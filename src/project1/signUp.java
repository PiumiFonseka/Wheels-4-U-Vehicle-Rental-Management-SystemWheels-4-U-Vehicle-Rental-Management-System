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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class signUp {

	private JFrame frame;
	private JTextField txtField_Fname;
	private JTextField textField_Uname;
	private JTextField textField_phoneNo;
	private JTextField textField_email;
	private JPasswordField textField_pwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signUp window = new signUp();
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
	public signUp() {
		initialize();
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

		JLabel lblNewLabel = new JLabel("Sign Up");
		lblNewLabel.setForeground(new Color(2, 62, 138));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel.setBounds(547, 117, 182, 67);
		frame.getContentPane().add(lblNewLabel);

		JLabel FName_lbl = new JLabel("Full Name:");
		FName_lbl.setForeground(new Color(2, 62, 138));
		FName_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		FName_lbl.setBounds(404, 235, 129, 29);
		frame.getContentPane().add(FName_lbl);

		JLabel UName_lbl = new JLabel("Username:");
		UName_lbl.setForeground(new Color(2, 62, 138));
		UName_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		UName_lbl.setBounds(404, 297, 129, 29);
		frame.getContentPane().add(UName_lbl);

		JLabel pwd_lbl = new JLabel(" Password:");
		pwd_lbl.setForeground(new Color(2, 62, 138));
		pwd_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pwd_lbl.setBounds(401, 355, 132, 29);
		frame.getContentPane().add(pwd_lbl);

		JLabel phoneNo_lbl = new JLabel(" Phone no:");
		phoneNo_lbl.setForeground(new Color(2, 62, 138));
		phoneNo_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		phoneNo_lbl.setBounds(401, 419, 129, 29);
		frame.getContentPane().add(phoneNo_lbl);

		JLabel email_lbl = new JLabel(" Email:");
		email_lbl.setForeground(new Color(2, 62, 138));
		email_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		email_lbl.setBounds(445, 483, 89, 29);
		frame.getContentPane().add(email_lbl);

		txtField_Fname = new JTextField();
		txtField_Fname.setBackground(new Color(202, 240, 248));
		txtField_Fname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtField_Fname.setBounds(568, 237, 256, 29);
		frame.getContentPane().add(txtField_Fname);
		txtField_Fname.setColumns(10);

		textField_Uname = new JTextField();
		textField_Uname.setBackground(new Color(202, 240, 248));
		textField_Uname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_Uname.setColumns(10);
		textField_Uname.setBounds(568, 299, 256, 29);
		frame.getContentPane().add(textField_Uname);

		textField_phoneNo = new JTextField();
		textField_phoneNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_phoneNo.setBackground(new Color(202, 240, 248));
		textField_phoneNo.setColumns(10);
		textField_phoneNo.setBounds(568, 421, 256, 29);
		frame.getContentPane().add(textField_phoneNo);

		textField_email = new JTextField();
		textField_email.setBackground(new Color(202, 240, 248));
		textField_email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_email.setColumns(10);
		textField_email.setBounds(568, 485, 256, 29);
		frame.getContentPane().add(textField_email);

		textField_pwd = new JPasswordField();
		textField_pwd.setBackground(new Color(202, 240, 248));
		textField_pwd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_pwd.setBounds(568, 357, 256, 29);
		frame.getContentPane().add(textField_pwd);

		int nextID = getNextID();
		// textField_ID.setText(Integer.toString(nextID));

		JButton btnSignup = new JButton("SignUp");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmailValid(textField_email.getText()) && isPasswordValid(textField_pwd.getText())) {
					// Password is valid, proceed with adding the admin
					addAdmin();
					login.main(new String[] {});
					frame.dispose();
				} else if (!isPasswordValid(textField_pwd.getText())) {
					JOptionPane.showMessageDialog(frame,
							"Invalid password.Password must contain at least 8 characters, including uppercase letters and lowercase letters");
				} else if (!isEmailValid(textField_email.getText())) {
					JOptionPane.showMessageDialog(frame, "Invalid email address.");
				}
			}
		});
		btnSignup.setBackground(new Color(144, 224, 239));
		btnSignup.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSignup.setBounds(568, 562, 139, 39);
		frame.getContentPane().add(btnSignup);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(202, 240, 248));
		panel.setBounds(293, 93, 646, 550);
		frame.getContentPane().add(panel);

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

	}

	/*
	 * private void populateTable() { tableModel = (DefaultTableModel)
	 * table_2.getModel(); tableModel.setRowCount(0); // Clear existing data
	 * 
	 * Connection connection = DatabaseConnection.getConnection(); if (connection !=
	 * null) { try { Statement statement = connection.createStatement(); String
	 * query = "SELECT * FROM admin"; ResultSet resultSet =
	 * statement.executeQuery(query);
	 * 
	 * while (resultSet.next()) { int id = resultSet.getInt("Admin_id"); String name
	 * = resultSet.getString("Admin_name"); String username =
	 * resultSet.getString("Admin_username"); String password =
	 * resultSet.getString("Admin_password"); int phone =
	 * resultSet.getInt("Admin_phone"); String email =
	 * resultSet.getString("Admin_email");
	 * 
	 * adminDetails admin = new adminDetails(id, name, username, password, phone,
	 * email); adminDetails.add(admin);
	 * 
	 * Vector<Object> row = new Vector<>(); row.add(admin.getId());
	 * row.add(admin.getName()); row.add(admin.getUsername());
	 * row.add(admin.getPassword()); row.add(admin.getPhone());
	 * row.add(admin.getEmail()); tableModel.addRow(row); }
	 * 
	 * resultSet.close(); statement.close(); } catch (SQLException e) {
	 * e.printStackTrace(); } finally {
	 * DatabaseConnection.closeConnection(connection); } } }
	 */

	private void clearFields() {
		// clear text fields

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
							// textField_ID.setText(Integer.toString(generatedRId));
							JOptionPane.showMessageDialog(frame, "SignUp successful!");
							clearFields();
							// populateTable();
						} else {
							JOptionPane.showMessageDialog(frame, "SignUp failed");
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