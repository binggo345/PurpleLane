package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import controllers.PromoController;
import models.Promo;

public class PromoView extends JFrame{

	JPanel top, mid, bot;
	JTable table;
	JScrollPane scrollPane;
	JLabel title, title2, labelId, labelCode, labelDiscount, labelNote, labelValue;
	JTextField txtCode, txtDiscount, txtNote;
	JButton insertButton, updateButton, deleteButton;
	
	public PromoView() {
		initialize();
		addComponent();
		addListener();
        setSize(600, 700);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
	}

	private void initialize() {
		top = new JPanel();				
		mid = new JPanel(new GridLayout(5, 2));
		bot = new JPanel();

		top.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		mid.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		
		table = new JTable();
		scrollPane = new JScrollPane(table);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		title = new JLabel("[Promotion Team] Purple Lane Book Store");	
		title.setFont(new Font("Verdana", Font.BOLD, 20));
		title2 = new JLabel("Promotion View");	
		title2.setFont(new Font("Verdana", Font.BOLD, 15));
		
		labelId = new JLabel("Promo ID: ");
		labelCode = new JLabel("Promo Code: ");
		labelDiscount = new JLabel("Promo Discount: ");
		labelNote = new JLabel("Product Note: ");
		labelValue = new JLabel("-");
		
		txtCode = new JTextField();
		txtDiscount = new JTextField();
		txtNote = new JTextField();
		
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
		mid.add(labelCode);
		mid.add(txtCode);
		mid.add(labelDiscount);
		mid.add(txtDiscount);
		mid.add(labelNote);
		mid.add(txtNote);
		
		bot.add(insertButton);
		bot.add(updateButton);
		bot.add(deleteButton);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}

	public void addListener() {
		/*
		 * INSERT NEW PRODUCT
		 */
		insertButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String code = txtCode.getText();
				Integer discount = 0;
				String note = txtNote.getText();
				
				discount = Integer.parseInt(txtDiscount.getText());
				
				PromoController.getInstance().insert(discount, code, discount, note);
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
				txtCode.setText(table.getValueAt(row, 1).toString());
				txtDiscount.setText(table.getValueAt(row, 2).toString());
				txtNote.setText(table.getValueAt(row, 3).toString());
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
				String code = txtCode.getText();
				Integer discount = 0;
				String note = txtNote.getText();
			
				discount = Integer.parseInt(txtDiscount.getText());
				
				PromoController.getInstance().update(id, code, discount, note);
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
				
				PromoController.getInstance().delete(id);
				loadData();
			}
		});
		
	}
	
	private void loadData() {		
		Vector<String> header = new Vector<>();
		header.add("ID");
		header.add("Code");
		header.add("Discount");
		header.add("Note");
		
		DefaultTableModel dtm = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
		
		Vector<Promo> promos = PromoController.getInstance().getAll();
		for (Promo promo : promos) {
			Vector<Object> row = new Vector<>();
			row.add(promo.getId());
			row.add(promo.getCode());
			row.add(promo.getDiscount());
			row.add(promo.getNote());
			dtm.addRow(row);
		}
		table.setModel(dtm);
	}

}
