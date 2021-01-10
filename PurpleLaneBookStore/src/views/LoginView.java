package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controllers.UserController;

public class LoginView extends JFrame {

	JPanel top, mid, bot;
	JLabel header, labelEmail, labelPassword;
	JTextField txtEmail, txtPassword;
	JButton loginButton;
	
	public LoginView() {
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
		mid = new JPanel(new GridLayout(2, 2));
		bot = new JPanel();
		
		mid.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		
		header = new JLabel("Login");
		header.setFont(new Font("Verdana", Font.BOLD, 20));
		labelEmail = new JLabel("Email: ");
		labelPassword = new JLabel("Password: ");
		
		txtEmail = new JTextField();
		txtPassword = new JPasswordField();
		
		loginButton = new JButton("Login");
	}
	
	private void addComponent() {
		top.add(header);
		
		mid.add(labelEmail);
		mid.add(txtEmail);
		mid.add(labelPassword);
		mid.add(txtPassword);
		
		bot.add(loginButton);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}
	
	private void addListener() {
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String password = txtPassword.getText();
				
				UserController.getInstance().getUser(email, password);
				dispose();
			}
		});
	}
	
}
