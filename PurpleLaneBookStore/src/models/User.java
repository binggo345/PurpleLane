package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import connect.Connect;
import views.CustomerView;
import views.HomeView;
import views.LoginView;
import views.ManagerView;
import views.ProductView;
import views.PromoView;

public class User {

	private Integer roleId;
	private String email;
	private String username;
	private String password;
	private Connect connect = Connect.getConnection();
	private String table = "users";
	
	public User() {
		
	}
	
	public User(Integer roleId, String email, String username, String password) {
		super();
		this.roleId = roleId;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void insert() {
		String query = String.format("Insert INTO users VALUES(null, ?, ?, ?, ?)", this.table);
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		try {
			preparedStatement.setInt(1, roleId);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, password);
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Register Successfull!!");
			new LoginView();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}
	
	public void getUser() {
		String query = String.format("SELECT * FROM users WHERE Email = ? AND Password = ?");
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = null;
		
		try {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			if(resultSet.first()) {
				JOptionPane.showMessageDialog(null, "Login Success!");
				roleId = resultSet.getInt("RoleId");
				/*
				 * Role ID
				 * 1 - Manager 			-> redirect to TransactionReportView();
				 * 2 - Admin       		-> redirect to ProductView();
				 * 3 - Promotion Team 	-> redirect to PromotionView();
				 * 4 - Customer			-> redirect to CustomerView();
				 */
				if(roleId == 1) {
					new ManagerView();
				}
				else if(roleId == 2) {
					new ProductView();
				}
				else if(roleId == 3) {
					new PromoView();
				}
				else {
					new CustomerView();
				}
			} 
			else {
				JOptionPane.showMessageDialog(null, "Login Failed!");
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}
	
}
