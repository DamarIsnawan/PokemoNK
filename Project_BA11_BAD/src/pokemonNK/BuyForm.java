package pokemonNK;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import pokemonk.DBConnect;

public class BuyForm extends JFrame implements ActionListener, MouseListener {
	JPanel upperPanel, midPanel, lowerPanel, mainPanel;
	JPanel IDPanel, QTYPanel, IDTP, QTYTP, bPanel;
	JLabel IDLabel, QTYLabel;
	JTextField inputID, inputQTY;
	JButton bInsert, bBack;

	JTable Table = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	DefaultTableModel dtmBuy;

	Vector<Vector> data = new Vector<>();
	Vector<String> header = new Vector<>();

	String ID = "";
	String Quantity = "";
	
	static Vector<String> vID = new Vector<>();
	static Vector<String> vQuantity = new Vector<>();

	DBConnect con = DBConnect.getConnection();

	public BuyForm() {
		initFrame();
		initComp();

		dtmBuy = new DefaultTableModel(data, header);
		header.add("Pokemon ID");
		header.add("Pokemon Name");
		header.add("Pokemon Level");
		header.add("Pokemon Type");

		Table.setModel(dtmBuy);
		Table.addMouseListener(this);
		scrollPane.setViewportView(Table);

		setupTabel();
	}

	public static void main(String[] args) {
		new BuyForm();

	}

	private void initFrame() {
		setTitle("1");
		setSize(600, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}

	private void initComp() {
		mainPanel = new JPanel(new BorderLayout());

		upperPanel = new JPanel();
		upperPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		upperPanel.setBackground(Color.CYAN);

		midPanel = new JPanel(new GridLayout(6, 1));
		midPanel.setBorder(new EmptyBorder(0, 60, 10, 60));
		midPanel.setBackground(Color.CYAN);

		lowerPanel = new JPanel(new GridLayout(1, 2));
		lowerPanel.setBorder(new EmptyBorder(0, 60, 10, 60));
		lowerPanel.setBackground(Color.CYAN);

		// Upper Panel
		upperPanel.add(scrollPane);

		// Mid Panel

		// ID
		JPanel IDPanel = new JPanel(new GridLayout(1, 1));
		IDPanel.setBackground(Color.CYAN);
		JLabel IDLabel = new JLabel("Insert Pokemon ID");
		IDLabel.setHorizontalAlignment(JLabel.LEADING);
		IDPanel.add(IDLabel);

		JTextField IDTF = new JTextField();
		IDPanel.add(IDTF);
		midPanel.add(IDPanel);

		// Quantity
		JPanel QTYPanel = new JPanel(new GridLayout(1, 1));
		QTYPanel.setBackground(Color.CYAN);
		JLabel QTYLabel = new JLabel("Quantity");
		QTYLabel.setHorizontalAlignment(JLabel.LEADING);
		QTYPanel.add(QTYLabel);

		JTextField QTYTF = new JTextField();
		QTYPanel.add(QTYTF);
		midPanel.add(QTYPanel);

		// Lower Panel
		bPanel = new JPanel();
		bPanel.setBackground(Color.CYAN);
		bInsert = new JButton("Insert");
		bBack = new JButton("Back to Main");

		lowerPanel.add(bInsert);
		lowerPanel.add(bBack);

		// Main Panel
		mainPanel.add(upperPanel, BorderLayout.NORTH);
		mainPanel.add(midPanel, BorderLayout.CENTER);
		mainPanel.add(lowerPanel, BorderLayout.SOUTH);

		bInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ID = IDTF.getText();
				Quantity = QTYTF.getText();
				
				vID.add(ID);
				vQuantity.add(Quantity);

				if (validateNumber(Quantity) == false) {
					JOptionPane.showMessageDialog(null, "Quantity must be a number!");

				} else {

					String query = "SELECT PokemonId FROM Pokemon WHERE PokemonId = '" + ID + "'";
					ResultSet rs = con.executeQuery(query);

					IDTF.setText("");
					QTYTF.setText("");

					try {
						if (rs.next()) {
							ID = rs.getString(1);
							JOptionPane.showMessageDialog(null, "Insert Success!");
						} else {
							JOptionPane.showMessageDialog(null, "ID is not exist!");
						}

					} catch (SQLException e2) {
						e2.printStackTrace();
					}

				}
			}

		});

		bBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainForm mf = new MainForm();

			}

		});

		add(mainPanel);
	}

	void setupTabel() {

		Table.setBackground(Color.CYAN);
		
		String query = "SELECT * FROM pokemon";
		ResultSet rs = con.executeQuery(query);

		try {

			data.clear();

			while (rs.next()) {

				String pokemonid = rs.getString(1);
				String name = rs.getString(2);
				String level = rs.getString(3);
				String type = rs.getString(4);

				Vector<String> vecRead = new Vector<>();

				vecRead.add(pokemonid);
				vecRead.add(name);
				vecRead.add(level);
				vecRead.add(type);

				data.add(vecRead);
			}

			dtmBuy.fireTableDataChanged();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean validateNumber(String valNumb) {

		char[] arraylevel = valNumb.toCharArray();

		for (char i : arraylevel) {
			if (!Character.isDigit(i)) {
				return false;
			}
		}

		return true;

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
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
