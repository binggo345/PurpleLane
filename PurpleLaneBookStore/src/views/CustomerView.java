package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.ProductController;
import models.Product;

public class CustomerView extends JFrame {

	JPanel top, mid, bot,test;
	JTable table;
	JScrollPane scrollPane;
	JLabel title, quantity;
	JTextField txtQuantity;
	JButton cartButton, checkOutButton, logOutButton;
	
	public CustomerView() {
		initialize();
		addComponent();
		addListener();
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
	}

	private void initialize() {
		top = new JPanel();				
		mid = new JPanel(new GridLayout(1, 1));
		bot = new JPanel();
		
		top.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		mid.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		
		table = new JTable();
		scrollPane = new JScrollPane(table);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		title = new JLabel("Purple Lane Book Store");
		title.setFont(new Font("Verdana", Font.BOLD, 20));
		
		cartButton = new JButton("Cart");
		checkOutButton = new JButton("Check Out");
		logOutButton = new JButton("Logout");

		loadData();
	}

	private void addComponent() {
		top.add(title);
		
		mid.add(scrollPane);
		
		bot.add(cartButton);
		bot.add(checkOutButton);
		bot.add(logOutButton);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}
	
	private void addListener() {
		logOutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == logOutButton) {
					dispose();
					new LoginView();
				}
			}
		});
		
		checkOutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CheckoutView();
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
		for (Product product : products) {
			Vector<Object> row = new Vector<>();
			row.add(product.getId());
			row.add(product.getName());
			row.add(product.getAuthor());
			row.add(product.getPrice());
			row.add(product.getStock());
			dtm.addRow(row);
		}
		table.setModel(dtm);
	}
	
}
