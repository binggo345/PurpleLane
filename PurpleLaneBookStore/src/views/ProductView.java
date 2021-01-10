package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.mysql.jdbc.Connection;

import connect.Connect;
import controllers.ProductController;
import models.Product;

public class ProductView extends JFrame{
	
	JPanel top, mid, bot;
	JTable table;
	JScrollPane scrollPane;
	JLabel title, title2, labelId, labelName, labelAuthor, labelPrice, labelStock, labelValue;
	JTextField txtName, txtAuthor, txtPrice, txtStock;
	JButton insertButton, updateButton, deleteButton;
	
	public ProductView() {
		initialize();
		addComponent();
		addListener();
        setSize(500, 700);
        setLocationRelativeTo(null);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
	}
	
	private void initialize() {
		top = new JPanel();				
		mid = new JPanel(new GridLayout(6, 2));
		bot = new JPanel();

		top.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		mid.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
			
		table = new JTable();
		scrollPane = new JScrollPane(table);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		title = new JLabel("[Admin] Purple Lane Book Store ");
		title.setFont(new Font("Verdana", Font.BOLD, 20));
		title2 = new JLabel("Products View");	
		title2.setFont(new Font("Verdana", Font.BOLD, 15));
		
		labelId = new JLabel("Product ID: ");
		labelName = new JLabel("Product Name: ");
		labelAuthor = new JLabel("Product Author: ");
		labelPrice = new JLabel("Product Price: ");
		labelStock = new JLabel("Product Stock: ");
		labelValue = new JLabel("-");
		
		txtName = new JTextField();
		txtAuthor = new JTextField();
		txtPrice = new JTextField();
		txtStock = new JTextField();
		
		insertButton = new JButton("Insert");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");
		
		insertButton.setBackground(Color.GREEN);
		updateButton.setBackground(Color.ORANGE);
		deleteButton.setBackground(Color.RED);
		
		loadData();
	}
	
	private void addComponent() {
		top.add(title);
		top.add(title2);
		top.add(scrollPane);
		
		mid.add(labelId);
		mid.add(labelValue);
		mid.add(labelName);
		mid.add(txtName);
		mid.add(labelAuthor);
		mid.add(txtAuthor);
		mid.add(labelPrice);
		mid.add(txtPrice);
		mid.add(labelStock);
		mid.add(txtStock);
	
		bot.add(insertButton);
		bot.add(updateButton);
		bot.add(deleteButton);	
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}
	
	private void addListener() {
		/*
		 * INSERT NEW PRODUCT
		 */
		insertButton.addActionListener(new ActionListener() {

			private Integer id;

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String author = txtAuthor.getText();
				Integer price = 0;
				Integer stock = 0;
				
				price = Integer.parseInt(txtPrice.getText());
				stock = Integer.parseInt(txtStock.getText());
				
				ProductController.getInstance().insert(id, name, author, price, stock);
				loadData();
			}
		});
		
		/* 
		 * DISPLAY PRODUCT INFORMATION WHEN CLICKED
		 */
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				labelValue.setText(table.getValueAt(row, 0).toString());
				txtName.setText(table.getValueAt(row, 1).toString());
				txtAuthor.setText(table.getValueAt(row, 2).toString());
				txtPrice.setText(table.getValueAt(row, 3).toString());
				txtStock.setText(table.getValueAt(row, 4).toString());
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/*
		 * UPDATE NEW PRODUCT
		 */
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id = Integer.parseInt(labelValue.getText());
				String name = txtName.getText();
				String author = txtAuthor.getText();
				Integer price = 0;
				Integer stock = 0;
				
				price = Integer.parseInt(txtPrice.getText());
				stock = Integer.parseInt(txtStock.getText());
				
				ProductController.getInstance().update(id, name, author, price, stock);
				loadData();
			}
		});
		
		/*
		 * DELETE NEW PRODUCT
		 */
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id = Integer.parseInt(labelValue.getText());
				
				ProductController.getInstance().delete(id);
				loadData();
			}
		});
	
	}
	
	private void loadData() {		
		Vector<String> header = new Vector<>();
		header.add("ID");
		header.add("Name");
		header.add("Author");
		header.add("Price");
		header.add("Stock");
		
		DefaultTableModel dtm = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
		
		Vector<Product> products = ProductController.getInstance().getAll();
		
		try {
			for (Product product : products) {
				Vector<Object> row = new Vector<>();
				row.add(product.getId());
				row.add(product.getName());
				row.add(product.getAuthor());
				row.add(product.getPrice());
				row.add(product.getStock());
				dtm.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		table.setModel(dtm);
	}

}
