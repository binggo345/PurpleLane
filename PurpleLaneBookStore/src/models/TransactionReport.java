package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class TransactionReport {

	private Integer id;
	private Date date;
	private Connect connect = Connect.getConnection();
	private String table = "transactionsreport";
	
	public TransactionReport() {
		// TODO Auto-generated constructor stub
	}

	public TransactionReport(Integer id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	private TransactionReport map(ResultSet resultSet) {
		try {	
			Integer id = resultSet.getInt("Id");
			Date date = resultSet.getDate("created_at");
			
			return new TransactionReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public Vector<TransactionReport> getAll() {
		String query = "SELECT * FROM " + this.table;
		ResultSet resultSet = connect.executeQuery(query);
		
		try {
			Vector<TransactionReport> transactionReports = new Vector<>();
			while(resultSet.next()) {
				TransactionReport transactionReport = map(resultSet);
				transactionReports.add(transactionReport);
			}
			return transactionReports;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
}
