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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class login {

	private JFrame frame;
	private JTextField textField;
	private JTextField Uname_txtF;
	private JPasswordField pwd_txtF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
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

		JButton login_btn = new JButton("Login");
		login_btn.setBackground(new Color(144, 224, 239));
		login_btn.setBounds(756, 425, 150, 36);
		login_btn.setFont(new Font("Tahoma", Font.PLAIN, 21));
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enteredUsername = Uname_txtF.getText();
				String enteredPassword = new String(pwd_txtF.getPassword());

				String adminName = authenticateUser(enteredUsername, enteredPassword);
				if (adminName != null) {
					dashboard.dashboard.main(new String[] { adminName });
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.");
				}
			}
		});
		JLabel lbl_X = new JLabel(" X");
		lbl_X.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		lbl_X.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lbl_X.setBounds(1179, 10, 46, 44);
		frame.getContentPane().add(lbl_X);

		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(login_btn);

		Uname_txtF = new JTextField();
		Uname_txtF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Uname_txtF.setBounds(729, 245, 325, 30);
		frame.getContentPane().add(Uname_txtF);
		Uname_txtF.setColumns(10);

		pwd_txtF = new JPasswordField();
		pwd_txtF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pwd_txtF.setBounds(729, 314, 325, 30);
		frame.getContentPane().add(pwd_txtF);

		JLabel Uname_lbl = new JLabel("Username:");
		Uname_lbl.setForeground(new Color(0, 119, 182));
		Uname_lbl.setFont(new Font("Tahoma", Font.BOLD, 25));
		Uname_lbl.setBounds(560, 245, 136, 22);
		frame.getContentPane().add(Uname_lbl);

		JLabel pwd_lbl = new JLabel(" Password:");
		pwd_lbl.setForeground(new Color(0, 119, 182));
		pwd_lbl.setFont(new Font("Tahoma", Font.BOLD, 25));
		pwd_lbl.setBounds(558, 314, 150, 22);
		frame.getContentPane().add(pwd_lbl);

		JCheckBox pwd_chkbox = new JCheckBox("Show password");
		pwd_chkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pwd_chkbox.isSelected()) {
					// Show the password
					pwd_txtF.setEchoChar((char) 0);
				} else {
					// Hide the password with dots (•)
					pwd_txtF.setEchoChar('\u2022');
				}
			}

		});
		pwd_chkbox.setBackground(new Color(192, 192, 192));
		pwd_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		pwd_chkbox.setBounds(941, 371, 113, 23);
		frame.getContentPane().add(pwd_chkbox);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(202, 240, 248));
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(421, 53, 18, 594);
		frame.getContentPane().add(separator_4);

		JLabel lblNewLabel = new JLabel("WHEELS4U");
		lblNewLabel.setForeground(new Color(202, 255, 255));
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.ITALIC, 55));
		lblNewLabel.setBounds(59, 278, 325, 54);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("VEHICLE RENTAL SYSTEM!");
		lblNewLabel_1.setForeground(new Color(202, 255, 255));
		lblNewLabel_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(20, 330, 376, 43);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Don't have an account?");
		lblNewLabel_2.setForeground(new Color(2, 62, 138));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(628, 511, 215, 30);
		frame.getContentPane().add(lblNewLabel_2);

		JButton Sign_up = new JButton("Sign Up");
		Sign_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUp.main(new String[] {});
				frame.dispose();
			}
		});
		Sign_up.setBounds(854, 512, 96, 30);
		frame.getContentPane().add(Sign_up);
		Sign_up.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Sign_up.setBackground(new Color(144, 224, 239));

		JLabel Login_title = new JLabel("LOGIN");
		Login_title.setBounds(707, 94, 199, 70);
		frame.getContentPane().add(Login_title);
		Login_title.setVerticalAlignment(SwingConstants.BOTTOM);
		Login_title.setHorizontalAlignment(SwingConstants.CENTER);
		Login_title.setBackground(new Color(202, 255, 255));
		Login_title.setForeground(new Color(2, 62, 138));
		Login_title.setFont(new Font("Times New Roman", Font.BOLD, 60));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(202, 240, 248));
		panel.setBounds(478, 70, 673, 531);
		frame.getContentPane().add(panel);
	}

	private String authenticateUser(String username, String password) {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT * FROM admin WHERE Admin_username = ? AND Admin_password = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);

				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					// User found with the provided credentials
					String adminName = resultSet.getString("Admin_username");
					DatabaseConnection.closeConnection(connection);
					return adminName;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
		return null;
	}
}