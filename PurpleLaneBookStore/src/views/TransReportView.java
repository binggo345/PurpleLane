package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.PromoController;
import controllers.TransactionReportController;
import models.Promo;
import models.TransactionReport;

public class TransReportView extends JFrame {

	JPanel top, mid, bot;
	JTable table;
	JLabel header;
	JScrollPane scrollPane;
	
	public TransReportView() {
		initialize();
		addComponent();
		addListener();
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
	}

	private void initialize() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(4, 2));
		bot = new JPanel();
	
		top.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		mid.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		
		table = new JTable();
		scrollPane = new JScrollPane(table);
		
		header = new JLabel("Transaction Reports");
		header.setFont(new Font("Verdana", Font.BOLD, 20));
		
		
		loadData();
	}
	
	private void addComponent() {
		top.add(header);
		
		mid.add(scrollPane);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}
	
	private void addListener() {

	}
	
	private void loadData() {
		Vector<String> header = new Vector<>();
		header.add("Date Created");
	
		DefaultTableModel dtm = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
		Vector<TransactionReport> transactionReports = TransactionReportController.getInstance().getAll();
		
		try {
			for(TransactionReport transactionReport : transactionReports) {
				Vector<Object> row = new Vector<>();
				row.add(transactionReport.getDate());
				dtm.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		table.setModel(dtm);
	}
	
}
