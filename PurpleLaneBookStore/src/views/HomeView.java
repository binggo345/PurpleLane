package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomeView extends JFrame {

	JPanel top, bot;
	JLabel header, header2, header3;
	JButton loginButton, registerButton;
	
	public HomeView() {
		intialize();
		addComponent();
		addListener();
		setSize(600, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
	}

	private void intialize() {
		top = new JPanel(new GridLayout(3, 1));
		bot = new JPanel(new GridLayout(1,1));
		
		top.setBackground(Color.YELLOW);
		top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		header = new JLabel("WELCOME", SwingConstants.CENTER);
		header.setFont(new Font("Verdana", Font.BOLD, 20));
		header2 = new JLabel("TO", SwingConstants.CENTER);
		header2.setFont(new Font("Verdana", Font.BOLD, 20));		
		header3 = new JLabel("PURPLE LANE BOOKSTORE", SwingConstants.CENTER);
		header3.setFont(new Font("Verdana", Font.BOLD, 20));
		
		loginButton = new JButton("LOGIN");
		loginButton.setFont(new Font("Verdana", Font.BOLD, 15));
		registerButton = new JButton("REGISTER");
		registerButton.setFont(new Font("Verdana", Font.BOLD, 15));
	}
	
	private void addComponent() {
		top.add(header);
		top.add(header2);
		top.add(header3);
		
		bot.add(loginButton);
		bot.add(registerButton);
		
		add(top, BorderLayout.NORTH);
		add(bot, BorderLayout.CENTER);
	}
	
	private void addListener() {
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new LoginView();
				dispose();
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new RegisterView();
				dispose();
			}
		});
	}
	
}
