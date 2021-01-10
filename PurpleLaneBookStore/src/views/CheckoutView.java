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
import models.Promo;

public class CheckoutView extends JFrame{

	JPanel top, mid, bot, paymentPanel, promoPanel;
	JTable table;
	JScrollPane scrollPane;
	JLabel header, labelPaymentType, labelPaymentCardNumber, labelPromo;
	JRadioButton debitButton, creditButton;
	ButtonGroup groupRadio;
	JTextField txtPaymentType, txtPaymentCardNumber;
	JButton payNowButton;
	
	public CheckoutView() {
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
		paymentPanel = new JPanel();
		promoPanel = new JPanel();
		groupRadio =  new ButtonGroup();
		
		top.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		mid.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		
		table = new JTable();
		scrollPane = new JScrollPane(table);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		header = new JLabel("Checkout");
		header.setFont(new Font("Verdana", Font.BOLD, 20));
		labelPaymentType = new JLabel("Choose Payment Type: ");
		debitButton = new JRadioButton("Debit");
		debitButton.setActionCommand("debit");
		creditButton = new JRadioButton("Credit");
		creditButton.setActionCommand("credit");
		labelPaymentCardNumber = new JLabel("Card Number: ");
		labelPromo = new JLabel("Choose Promo: ");
		
		txtPaymentType = new JTextField();
		txtPaymentCardNumber = new JTextField();
		
		payNowButton = new JButton("Pay Now");
		payNowButton.setBackground(Color.GREEN);
		
		loadData();
	}
	
	private void addComponent() {
		top.add(header);
		
		paymentPanel.add(debitButton);
		paymentPanel.add(creditButton);
		groupRadio.add(debitButton);
		groupRadio.add(creditButton);
		
		mid.add(labelPaymentType);
		mid.add(paymentPanel);
		mid.add(labelPaymentCardNumber);
		mid.add(txtPaymentType);
		mid.add(labelPromo);
		mid.add(scrollPane);
		
		bot.add(payNowButton);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}
	
	private void addListener() {
		payNowButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Confirm Payment");
				if(dialogResult == JOptionPane.YES_OPTION){
					  JOptionPane.showMessageDialog(null, "Payment Success");
					  dispose();
					}
			}
		});
	}
	
	private void loadData() {
		Vector<String> header = new Vector<>();
		header.add("Promo Code");
		header.add("Discount");
		header.add("Note");
	
		DefaultTableModel dtm = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
		Vector<Promo> promos = PromoController.getInstance().getAll();
		
		try {
			for(Promo promo : promos) {
				Vector<Object> row = new Vector<>();
				row.add(promo.getCode());
				row.add(promo.getDiscount());
				row.add(promo.getNote());
				dtm.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		table.setModel(dtm);
	}
}
