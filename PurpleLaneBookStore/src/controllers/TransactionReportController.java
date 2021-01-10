package controllers;

import java.util.Vector;

import models.TransactionReport;
import views.TransReportView;

public class TransactionReportController {

	public static TransactionReportController controller = null;
	public TransactionReport transactionReport;
	
	public TransactionReportController() {
		transactionReport = new TransactionReport();
	}

	public static TransactionReportController getInstance() {
		if(controller == null) {
			controller = new TransactionReportController();
		}
		return controller;
	}
	
	public Vector<TransactionReport> getAll() {
		return transactionReport.getAll();
	}
	
	public void showTransactionReportView() {
		new TransReportView();
	}
}
