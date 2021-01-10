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

public class ManagerView extends JFrame {

	JPanel top, bot;
	JLabel header;
	JButton loginButton, registerButton;
	
	public ManagerView() {
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
		top = new JPanel(new GridLayout(1, 1));
		bot = new JPanel(new GridLayout(1,1));
		
		top.setBackground(Color.CYAN);
		top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		header = new JLabel("MANAGER VIEW", SwingConstants.CENTER);
		header.setFont(new Font("Verdana", Font.BOLD, 20));
		
		loginButton = new JButton("TRANSACTION REPORT");
		loginButton.setFont(new Font("Verdana", Font.BOLD, 15));
		registerButton = new JButton("HIRE NEW STAFF");
		registerButton.setFont(new Font("Verdana", Font.BOLD, 15));
	}
	
	private void addComponent() {
		top.add(header);
		
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
				new TransReportView();
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HireNewStaffView();
			}
		});
	}
}
