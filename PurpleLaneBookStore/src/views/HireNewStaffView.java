package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controllers.UserController;

public class HireNewStaffView extends JFrame {

	JPanel top, mid, bot, rolePanel;
	JLabel header, labelRole, labelEmail, labelUsername, labelPassword;
	JRadioButton promoButton, adminButton;
	ButtonGroup groupRadio;
	JTextField txtEmail, txtUsername, txtPassword;
	JButton registerButton;
	
	public HireNewStaffView() {
		initialize();	
		addComponent();
		addListener();
		setSize(700, 300);	
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void initialize() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(4, 2));
		bot = new JPanel();
		rolePanel = new JPanel();
		groupRadio = new ButtonGroup();
		
		mid.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		
		header = new JLabel("Hire New Staff");
		header.setFont(new Font("Verdana", Font.BOLD, 20));
		labelRole = new JLabel("Assign Role: ");
		adminButton = new JRadioButton("Admin");
		adminButton.setActionCommand("2");
		promoButton = new JRadioButton("Promotion Team");
		promoButton.setActionCommand("3");
		
		labelEmail = new JLabel("Email: ");
		labelUsername = new JLabel("Username: ");
		labelPassword = new JLabel("Password: ");
		
		txtEmail = new JTextField();
		txtUsername = new JTextField();
		txtPassword = new JTextField();
		
		registerButton = new JButton("Register New Staff");
	}
	
	private void addComponent() {
		top.add(header);
		
		rolePanel.add(adminButton);
		rolePanel.add(promoButton);
		groupRadio.add(adminButton);
		groupRadio.add(promoButton);
		
		mid.add(labelRole);
		mid.add(rolePanel);
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
	
	private void addListener() {
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer roleId = 0;
				String email = txtEmail.getText();
				String username = txtUsername.getText();
				String password = txtPassword.getText();			
				
				if(adminButton.isSelected()) {
					// Manager Role
					roleId = 2;
				}
				else {
					// Promotion Team Role
					roleId = 3;
				}
//				System.out.println("" +roleId);
				
				UserController.getInstance().insert(roleId, email, username, password);
				dispose();
			}
		});
	}

}
