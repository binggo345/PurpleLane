package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class Product {

	private Integer id;
	private String name;
	private String author;
	private Integer price;
	private Integer stock;
	private Connect connect = Connect.getConnection();
	private String table = "products";
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(Integer id, String name, String author, Integer price, Integer stock) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.stock = stock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	private Product map(ResultSet resultSet) {
		try {	
			Integer id = resultSet.getInt("ProductId");
			String name = resultSet.getString("ProductName");
			String author = resultSet.getString("ProductAuthor");
			Integer price = resultSet.getInt("ProductPrice");
			Integer stock = resultSet.getInt("ProductStock");
			
			return new Product(id, name, author, price, stock);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public Vector<Product> getAll() {
		String query = "SELECT * FROM " + this.table;
		ResultSet resultSet = connect.executeQuery(query);
		
		try {
			Vector<Product> products = new Vector<>();
			while(resultSet.next()) {
				Product product = map(resultSet);
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	public void insert() {
		String query = String.format("INSERT INTO products VALUES(NULL, ?, ?, ?, ?)");
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		
		try {
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, author);
			preparedStatement.setInt(3, price);
			preparedStatement.setInt(4, stock);
			preparedStatement.executeUpdate();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}
	
	public void update() {
		String query = String.format("UPDATE products SET ProductName = ?, ProductAuthor =  ?, ProductPrice = ?, ProductStock = ? WHERE ProductId = ?");
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		
		try {
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, author);
			preparedStatement.setInt(3, price);
			preparedStatement.setInt(4, stock);
			preparedStatement.setInt(5, id);
			preparedStatement.executeUpdate();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void delete() {
		String query = String.format("DELETE FROM products where ProductId = ?");
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		
		try {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
}
