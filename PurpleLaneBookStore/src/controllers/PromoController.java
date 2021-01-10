package controllers;

import java.util.Vector;
import models.Promo;
import views.PromoView;

public class PromoController {

	public static PromoController controller = null;
	public Promo promo;
	
	private PromoController() {
		promo = new Promo();
	}

	public static PromoController getInstance() {
		if(controller == null) {
			controller = new PromoController();
		}
		return controller;
	}
	
	public Vector<Promo> getAll() {
		return promo.getAll();
	}
	
	public void insert(Integer id, String code, Integer discount, String note) {
		promo = new Promo(id, code, discount, note);
		promo.insert();
		
	}
	
	public void update(Integer id, String code, Integer discount, String note) {
		promo = new Promo(id, code, discount, note);
		promo.update();
		
	}
	
	public void delete(Integer id) {
		promo = new Promo();
		promo.setId(id);
		promo.delete();
		
	}
	
	public void showPromoView() {
		new PromoView();
	}
	
}
