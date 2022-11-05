package pokemonNK;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import pokemonk.DBConnect;

public class Bag extends JFrame implements ActionListener, MouseListener {

	JPanel topPanel, firstPanel, secondPanel, thirdPanel, forthPanel, centerPanel, bottomPanel, mainPanel;
	JLabel idLabel, menuIdLabel, nameLabel, menuNameLabel, qtyLabel, menuLabel, cartLabel, totalLabel, valueTotalLabel;
	JSpinner qtySpinner;
	JButton addBtn, updateBtn, removeBtn, cancelBtn, finishBtn;

	JTable cartTable = new JTable();
	JScrollPane scrollPaneCart = new JScrollPane();
	DefaultTableModel dtmCart;

	Vector<String> headerCart = new Vector<>();
	Vector<Vector> dataCart = new Vector<>();

	Random rd = new Random();

//	String ID = BuyForm.ID;
//	String Quantity = BuyForm.Quantity;

	Vector<String> ID = BuyForm.vID;
	Vector<String> Quantity = BuyForm.vQuantity;

	DBConnect con = DBConnect.getConnection();

	public Bag() {
		initFrame();

		mainPanel = setMainPanel();
		add(mainPanel);
		
		showCart();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private void initFrame() {
		setTitle("Manage Bag");
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}

	private JPanel setMainPanel() {

		topPanel = setTopPanel();
		centerPanel = setCenterPanel();
		bottomPanel = setBottomPanel();

		mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		return mainPanel;
	}

	private JPanel setTopPanel() {
		topPanel = new JPanel(new GridLayout(1, 1));
		topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		topPanel.setBackground(Color.CYAN);

		dtmCart = new DefaultTableModel(dataCart, headerCart);
		headerCart.add("Pokemon Id");
		headerCart.add("Pokemon Name");
		headerCart.add("Pokemon Level");
		headerCart.add("Pokemon Type");
		headerCart.add("Quantity");

		cartTable.setModel(dtmCart);
		cartTable.addMouseListener(this);
		scrollPaneCart.setViewportView(cartTable);

		topPanel.add(scrollPaneCart);

		return topPanel;
	}

	private JPanel setCenterPanel() {
		centerPanel = new JPanel(new GridLayout(3, 1));
		centerPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		centerPanel.setBackground(Color.CYAN);

		// ID
		JPanel idpnl = new JPanel(new GridLayout(1, 1));
		idpnl.setBackground(Color.CYAN);
		JLabel idlbl = new JLabel("PokemonId");
		idlbl.setHorizontalAlignment(JLabel.LEADING);
		idpnl.add(idlbl);

		JTextField idTF = new JTextField();
		idpnl.add(idTF);
		centerPanel.add(idpnl);

		// Button delete
		JPanel bPanel = new JPanel();
		bPanel.setBackground(Color.CYAN);
		JButton bDelete = new JButton("Delete");

		centerPanel.add(bDelete, BorderLayout.SOUTH);

		bDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String remove = idTF.getText();
				String cart = "";
				Integer row = 0;

				for (int i = 0; i < cartTable.getRowCount(); i++) {
					cart = cartTable.getValueAt(i, 0).toString();

					if (cart.equals(remove)) {
						row = i;
						break;
					}
				}
				dataCart.removeElementAt(row);
				dtmCart.fireTableDataChanged();

			}

		});

		return centerPanel;
	}

	private JPanel setBottomPanel() {
		bottomPanel = new JPanel(new GridLayout(1, 1));
		bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		bottomPanel.setBackground(Color.CYAN);

		JButton bCheck = new JButton("Checkout");
		JButton bBack = new JButton("Back to Main");

		bottomPanel.add(bCheck);
		bottomPanel.add(bBack);

		bBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainForm mf = new MainForm();

			}

		});

		bCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer transactionId = 0;
				Integer id = 1;
				Integer userId = 1;
				LocalDateTime transactionTime = LocalDateTime.now();

				String queryTH = "INSERT INTO headertransaction VALUES ('" + transactionId + "', '" + userId + "', '"
						+ transactionTime + "')";
				con.executeUpdate(queryTH);
				for (int i = 0; i < cartTable.getRowCount(); i++) {
					String queryTD = "INSERT INTO detailtransaction VALUES ('" +id+ "', '"  +cartTable.getValueAt(i, 0) + "', '"
							+  "', '" + cartTable.getValueAt(i, 4) + "')";
					con.executeUpdate(queryTD);

				}
				dataCart.removeAllElements();
				dtmCart.fireTableDataChanged();
			}

		});

		return bottomPanel;
	}

	void showCart() {

		cartTable.setBackground(Color.CYAN);
		
		for (int i = 0; i < ID.size(); i++) {
			for (int j = i + 1; j < ID.size(); j++)
				if (ID.get(i).compareToIgnoreCase(ID.get(j)) > 0) {
					String tempID = ID.get(i);
					ID.set(i, ID.get(j));
					ID.set(j, tempID);

					String tempQty = Quantity.get(i);
					Quantity.set(i, Quantity.get(j));
					Quantity.set(j, tempQty);

				}
			String query = "SELECT PokemonId, PokemonName, PokemonLevel, PokemonType FROM Pokemon WHERE PokemonId = '"
					+ ID.get(i) + "'";
			ResultSet rs = con.executeQuery(query);

			try {

				while (rs.next()) {

					String pokemonID = rs.getString(1);
					String name = rs.getString(2);
					String level = rs.getString(3);
					String type = rs.getString(4);

					Vector<String> vecRead = new Vector<>();

					vecRead.add(pokemonID);
					vecRead.add(name);
					vecRead.add(level);
					vecRead.add(type);
					vecRead.add(Quantity.get(i));

					dataCart.add(vecRead);
				}
				dtmCart.fireTableDataChanged();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	Integer randomNum() {

		Integer random, j;
		return random = randomInteger(0, 100);

	}

	int randomInteger(int min, int max) {
		return min + rd.nextInt(max - min + 1);

	}

//	void addToCart() {
//		String addCart = menuIdLabel.getText();
//		String cartExist = "";
//		Integer qty = Integer.parseInt(qtySpinner.getValue().toString());
//		Integer newQty = 0;
//		Integer row = 0;
//		Integer qtyExist = 0;
//
//		for (int i = 0; i < dataCart.size(); i++) {
//			cartExist = cartTable.getValueAt(i, 0).toString();
//
//			if (cartExist.equals(addCart)) {
//				row = i;
//				break;
//			}
//		}
//
//		qtyExist = Integer.parseInt(cartTable.getValueAt(row, 3).toString());
//		newQty = qtyExist + qty;
//
//		cartTable.setValueAt(String.valueOf(newQty), row, 3);
//		dtmCart.fireTableDataChanged();
//
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
