package pokemonNK;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pokemonk.DBConnect;

public class History extends JFrame implements ActionListener, MouseListener {

	JPanel topPanel, firstPanel, secondPanel, thirdPanel, forthPanel, centerPanel, bottomPanel, mainPanel;
	JSpinner qtySpinner;
	JButton bBack;

	JTable transactionTbl = new JTable();
	JScrollPane transactionSP = new JScrollPane();
	JTable detailTable = new JTable();
	JScrollPane detailSP = new JScrollPane();
	DefaultTableModel dtmTransaction, dtmDetail;

	Vector<String> headertransaction = new Vector<>();
	Vector<Vector> datatransaction = new Vector<>();

	Vector<String> headerDetail = new Vector<>();
	Vector<Vector> dataDetail = new Vector<>();

	JLabel txtlbl = new JLabel();
	String ID = txtlbl.getText();

	DBConnect con = DBConnect.getConnection();

	public History() {
		initFrame();

		mainPanel = setMainPanel();
		add(mainPanel);

		setupTabel();
		setupDetail();
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

	private JPanel setBottomPanel() {
		bottomPanel = new JPanel(new GridLayout(1, 1, 10, 10));
		bottomPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		bottomPanel.setBackground(Color.CYAN);

		bBack = new JButton("Back to the menu");
		bBack.setPreferredSize(new Dimension(470, 30));
		bBack.addActionListener(this);

		bottomPanel.add(bBack);

		bBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainForm mf = new MainForm();

			}

		});

		return bottomPanel;
	}

	private JPanel setCenterPanel() {
		centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		centerPanel.setBorder(new EmptyBorder(10, 20, 10, 10));
		centerPanel.setBackground(Color.CYAN);

		// Transaction
		dtmTransaction = new DefaultTableModel(datatransaction, headertransaction);
		headertransaction.add("TransactionId");
		headertransaction.add("Time");

		transactionTbl.setModel(dtmTransaction);
		transactionTbl.addMouseListener(this);
		transactionSP.setViewportView(transactionTbl);

		// Cart
		dtmDetail = new DefaultTableModel(dataDetail, headerDetail);
		headerDetail.add("Transaction Id");
		headerDetail.add("Pokemon Id");
		headerDetail.add("Pokemon Name");
		headerDetail.add("Pokemon Level");
		headerDetail.add("Pokemon Type");
		headerDetail.add("Quantity");

		detailTable.setModel(dtmDetail);
		detailTable.addMouseListener(this);
		detailSP.setViewportView(detailTable);

		centerPanel.add(transactionSP);
		centerPanel.add(detailSP);

		return centerPanel;
	}

	private JPanel setTopPanel() {

		topPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		topPanel.setBorder(new EmptyBorder(20, 20, 10, 20));
		topPanel.setBackground(Color.CYAN);

		JPanel txtpnl = new JPanel(new GridLayout(2, 1));
		txtpnl.setBackground(Color.CYAN);
		JLabel title = new JLabel("Transaction History");
		title.setBackground(Color.CYAN);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Sans Serif", Font.BOLD, 32));
		txtpnl.add(title);

		JLabel details = new JLabel("Select the row for details");
		details.setBackground(Color.CYAN);
		details.setHorizontalAlignment(JLabel.CENTER);
		details.setFont(new Font("Sans Serif", Font.BOLD, 16));
		txtpnl.add(details);

		topPanel.add(txtpnl);

		return topPanel;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private void initFrame() {
		setTitle("View Transaction History");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}

	void setupTabel() {

		transactionTbl.setBackground(Color.CYAN);

		String query = "SELECT * FROM headertransaction";
		ResultSet rs = con.executeQuery(query);

		try {

			datatransaction.clear();

			while (rs.next()) {

				String transactionid = rs.getString(1);
				String time = rs.getString(3);

				Vector<String> vecRead = new Vector<>();

				vecRead.add(transactionid);
				vecRead.add(time);

				datatransaction.add(vecRead);
			}

			dtmTransaction.fireTableDataChanged();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	void setupDetail() {
		detailTable.setBackground(Color.CYAN);
		String query = "SELECT  TransactionId, dt.PokemonId, PokemonName, PokemonLevel, PokemonType, Quantity FROM detailtransaction dt JOIN headertransaction ht ON ht.TransactionId = dt.TransactionId WHERE ht.TransactionId = 1 '"
				+ ID + "'";
		ResultSet rs = con.executeQuery(query);

		try {

			while (rs.next()) {

				String transactionID = rs.getString(1);
				String pokemonID = rs.getString(2);
				String name = rs.getString(3);
				String level = rs.getString(4);
				String type = rs.getString(5);
				String qty = rs.getString(6);

				Vector<String> vecRead = new Vector<>();

				vecRead.add(transactionID);
				vecRead.add(pokemonID);
				vecRead.add(name);
				vecRead.add(level);
				vecRead.add(type);
				vecRead.add(qty);

				dataDetail.add(vecRead);
			}
			dtmDetail.fireTableDataChanged();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg) {
		if (arg.getSource() == transactionTbl) {
			txtlbl.setText(transactionTbl.getValueAt(transactionTbl.getSelectedRow(), 0).toString());

		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
