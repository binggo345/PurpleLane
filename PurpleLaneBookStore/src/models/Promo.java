package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class Promo {

	private Integer id;
	private String code;
	private Integer discount;
	private String note;
	private Connect connect = Connect.getConnection();
	private String table = "promos";
	
	public Promo() {
		// TODO Auto-generated constructor stub
	}

	public Promo(Integer id, String code, Integer discount, String note) {
		super();
		this.id = id;
		this.code = code;
		this.discount = discount;
		this.note = note;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	private Promo map(ResultSet resultSet) {
		try {
			Integer id = resultSet.getInt("PromoId");
			String code = resultSet.getString("PromoCode");
			Integer discount = resultSet.getInt("PromoDiscount");
			String note = resultSet.getString("PromoNote");
			
			return new Promo(id, code, discount, note);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<Promo> getAll() {
		String query = "SELECT * FROM " + this.table;
		ResultSet resultSet = connect.executeQuery(query);
		
		try {
			Vector<Promo> promotions = new Vector<>();
			while(resultSet.next()) {
				Promo promotion = map(resultSet);
				promotions.add(promotion);
			}
			return promotions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert() {
		String query = String.format("INSERT INTO promos VALUES(NULL, ?, ?, ?)", this.table);
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		
		try {
			preparedStatement.setString(1, code);
			preparedStatement.setInt(2, discount);
			preparedStatement.setString(3, note);
			preparedStatement.executeUpdate();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}
	
	public void update() {
		String query = String.format("UPDATE promos SET PromoCode = ?, PromoDiscount =  ?, PromoNote = ? WHERE PromoId = ?");
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		
		try {
			preparedStatement.setString(1, code);
			preparedStatement.setInt(2, discount);
			preparedStatement.setString(3, note);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}
	
	public void delete() {
		String query = String.format("DELETE FROM promos where PromoId = ?");
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		
		try {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

}
