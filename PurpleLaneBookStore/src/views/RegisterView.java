package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import controllers.UserController;

public class RegisterView extends JFrame {

	JPanel top, mid, bot;
	JLabel header, labelEmail, labelUsername, labelPassword;
	JTextField txtEmail, txtUsername, txtPassword;
	JButton registerButton;
	
	public RegisterView() {	
		initialize();	
		addComponent();
		addListener();
		setSize(700, 200);	
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void initialize() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(3, 3));
		bot = new JPanel();	
		header = new JLabel("Register Form");
		header.setFont(new Font("Verdana", Font.BOLD, 20));
		
		mid.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		
		labelEmail = new JLabel("Email: ");
		labelUsername = new JLabel("Username: ");
		labelPassword = new JLabel("Password: ");
		
		txtEmail = new JTextField();
		txtUsername = new JTextField();
		txtPassword = new JPasswordField();
		
		registerButton = new JButton("Register");
	}

	private void addComponent() {
		top.add(header);
		
		mid.add(labelEmail);
		mid.add(txtEmail);
		mid.add(labelUsername);
		mid.add(txtUsername);
		mid.add(labelPassword);
		mid.add(txtPassword);
		
		bot.add(registerButton);

		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}

	public void addListener() {
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer roleId = 4;
				String email = txtEmail.getText();
				String username = txtUsername.getText();
				String password = txtPassword.getText();			
				
				UserController.getInstance().insert(roleId, email, username, password);
				dispose();
			}
		});
	}

}
