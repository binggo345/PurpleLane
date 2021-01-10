package controllers;

import views.HomeView;

public class Controller {

	public static Controller controller = null;
	
	private Controller() {
		// TODO Auto-generated constructor stub
	}
	
	public static Controller getInstance() {
		if(controller == null) {
			controller = new Controller();
		}
		return controller;
	}
	
	public void showHomeView() {
		new HomeView();
	}

}
