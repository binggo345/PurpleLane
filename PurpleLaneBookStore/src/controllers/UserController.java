package controllers;

import java.util.Scanner;

import javax.swing.JOptionPane;

import models.User;
import views.HomeView;
import views.LoginView;
import views.RegisterView;
import views.View;

public class UserController {

	public static UserController controller = null;
	public User user;
	
	private UserController() {
		user = new User();
	}

	public static UserController getInstance() {
		if(controller == null) {
			controller = new UserController();
		}
		return controller;
	}
	
	public void insert(Integer roleId, String email, String username, String password) {		
		user = new User(roleId, email, username, password);
		user.insert();
		
	}
	
	public void getUser(String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.getUser();
		
		while(true) {
			if(email.isBlank()) {
				JOptionPane.showMessageDialog(null, "Email cannot be empty");
				break;
			}
			else {
				break;
			}	
		}
		
		while(true) {
			if(password.isBlank()) {
				JOptionPane.showMessageDialog(null, "Password cannot be empty");
				break;
			}
			else {
				break;
			}	
		}
		
	}
	
	public void showHomeView() {
		
	}
	
	public void showRegisterView() {
		new RegisterView();
	}
	
	public void showLoginView() {
		new LoginView();
	}
	
}
