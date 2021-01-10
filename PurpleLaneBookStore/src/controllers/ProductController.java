package controllers;

import java.util.Vector;

import models.Product;
import views.ProductView;

public class ProductController {

	public static ProductController controller = null;
	public Product product;
	
	private ProductController() {
		product = new Product();
	}

	public static ProductController getInstance() {
		if(controller == null) {
			controller = new ProductController();
		}
		return controller;
	}
	
	public Vector<Product> getAll() {
		return product.getAll();
	}
	
	public void insert(Integer id, String name, String author, Integer price, Integer stock) {
		product = new Product(id, name, author, price, stock);
		product.insert();
		
	}
	
	public void update(Integer id, String name, String author, Integer price, Integer stock) {
		product = new Product(id, name, author, price, stock);
		product.update();
		
	}
	
	public void delete(Integer id) {
		product = new Product();
		product.setId(id);
		product.delete();
		
	}
	
	public void showProductView() {
		new ProductView();
	}
	
}
