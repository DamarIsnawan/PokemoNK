package pokemonNK;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import pokemonk.DBConnect;

public class ManagePokemon extends JFrame implements ActionListener, MouseListener {
	JPanel upperPanel, midPanel, lowerPanel, mainPanel;
	JPanel insertPanel, deletePanel, updatePanel, IDTP, QTYTP, bPanel;
	JLabel nameLabel, levelLabel, typeLabel;
	JTextField inputID, inputQTY;
	JButton bInsert, bBack;

	JTable menuTable = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	DefaultTableModel dtmManage;

	Vector<Vector> data = new Vector<>();
	Vector<String> header = new Vector<>();
	Random rd = new Random();

	DBConnect con = DBConnect.getConnection();

	public ManagePokemon() {
		initFrame();
		initComp();

		dtmManage = new DefaultTableModel(data, header);
		header.add("Pokemon ID");
		header.add("Pokemon Name");
		header.add("Pokemon Level");
		header.add("Pokemon Type");

		menuTable.setModel(dtmManage);
		menuTable.addMouseListener(this);
		scrollPane.setViewportView(menuTable);

		setupTabel();
	}

	public static void main(String[] args) {

	}

	private void initFrame() {
		setTitle("Welcome Admin");
		setSize(900, 700);
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

		midPanel = new JPanel(new GridLayout(1,1));
		midPanel.setBackground(Color.CYAN);
		
		deletePanel = new JPanel(new GridLayout(2, 2));
		deletePanel.setBorder(new EmptyBorder(60, 60, 60, 60));
		deletePanel.setBackground(Color.CYAN);

		lowerPanel = new JPanel(new GridLayout(1, 2));
		lowerPanel.setBorder(new EmptyBorder(0, 60, 10, 60));
		lowerPanel.setBackground(Color.CYAN);

		insertPanel = new JPanel(new GridLayout(4, 4));
		insertPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		insertPanel.setBackground(Color.CYAN);

		updatePanel = new JPanel(new GridLayout(5, 5));
		updatePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		updatePanel.setBackground(Color.CYAN);

		// Upper Panel
		upperPanel.add(scrollPane);

		// Insert
		JPanel InamePanel = new JPanel(new GridLayout(1, 1));
		InamePanel.setBackground(Color.CYAN);
		JLabel InameLabel = new JLabel("Pokemon Name: ");
		InameLabel.setHorizontalAlignment(JLabel.LEADING);
		InamePanel.add(InameLabel);

		JTextField InameTF = new JTextField();
		InamePanel.add(InameTF);
		insertPanel.add(InamePanel);

		JPanel IlevelPanel = new JPanel(new GridLayout(1, 1));
		IlevelPanel.setBackground(Color.CYAN);
		JLabel IlevelLabel = new JLabel("Pokemon Level: ");
		IlevelLabel.setHorizontalAlignment(JLabel.LEADING);
		IlevelPanel.add(IlevelLabel);

		JTextField IlevelTF = new JTextField();
		IlevelPanel.add(IlevelTF);
		insertPanel.add(IlevelPanel);

		JPanel ItypePanel = new JPanel(new GridLayout(1, 1));
		ItypePanel.setBackground(Color.CYAN);
		JLabel ItypeLabel = new JLabel("Pokemon Type: ");
		ItypeLabel.setHorizontalAlignment(JLabel.LEADING);
		ItypePanel.add(ItypeLabel);

		JTextField ItypeTF = new JTextField();
		ItypePanel.add(ItypeTF);
		insertPanel.add(ItypePanel);

		JButton bInsert = new JButton("Insert");
		insertPanel.add(bInsert);

		// Delete
		JPanel DIdPanel = new JPanel(new GridLayout(1, 1));
		DIdPanel.setBackground(Color.CYAN);
		JLabel DIdLabel = new JLabel("Pokemon ID: ");
		DIdLabel.setHorizontalAlignment(JLabel.LEADING);
		DIdPanel.add(DIdLabel);

		JTextField DIdTF = new JTextField();
		DIdPanel.add(DIdTF);
		deletePanel.add(DIdPanel);

		JButton bDelete = new JButton("Delete");
		deletePanel.add(bDelete);

		// Update
		JPanel UIdPanel = new JPanel(new GridLayout(1, 1));
		UIdPanel.setBackground(Color.CYAN);
		JLabel UIdLabel = new JLabel("Pokemon ID: ");
		UIdLabel.setHorizontalAlignment(JLabel.LEADING);
		UIdPanel.add(UIdLabel);

		JTextField UIdTF = new JTextField();
		UIdPanel.add(UIdTF);
		updatePanel.add(UIdPanel);

		JPanel UnamePanel = new JPanel(new GridLayout(1, 1));
		UnamePanel.setBackground(Color.CYAN);
		JLabel UnameLabel = new JLabel("Pokemon Name: ");
		UnameLabel.setHorizontalAlignment(JLabel.LEADING);
		UnamePanel.add(UnameLabel);

		JTextField UnameTF = new JTextField();
		UnamePanel.add(UnameTF);
		updatePanel.add(UnamePanel);

		JPanel UlevelPanel = new JPanel(new GridLayout(1, 1));
		UlevelPanel.setBackground(Color.CYAN);
		JLabel UlevelLabel = new JLabel("Pokemon Level: ");
		UlevelLabel.setHorizontalAlignment(JLabel.LEADING);
		UlevelPanel.add(UlevelLabel);

		JTextField UlevelTF = new JTextField();
		UlevelPanel.add(UlevelTF);
		updatePanel.add(UlevelPanel);

		JPanel UtypePanel = new JPanel(new GridLayout(1, 1));
		UtypePanel.setBackground(Color.CYAN);
		JLabel UtypeLabel = new JLabel("Pokemon Type: ");
		UtypeLabel.setHorizontalAlignment(JLabel.LEADING);
		UtypePanel.add(UtypeLabel);

		JTextField UtypeTF = new JTextField();
		UtypePanel.add(UtypeTF);
		updatePanel.add(UtypePanel);

		JButton bUpdate = new JButton("Update");
		updatePanel.add(bUpdate);

		midPanel.add(insertPanel, BorderLayout.WEST);
		midPanel.add(deletePanel, BorderLayout.CENTER);
		midPanel.add(updatePanel, BorderLayout.EAST);
		// Lower Panel
		bPanel = new JPanel();
		bPanel.setBackground(Color.CYAN);
		bBack = new JButton("Back to Menu");

		lowerPanel.add(bBack);

		// Main Panel
		mainPanel.add(upperPanel, BorderLayout.NORTH);
		mainPanel.add(midPanel, BorderLayout.CENTER);
		mainPanel.add(lowerPanel, BorderLayout.SOUTH);

		bInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String sName, sLevel, sType;
				Integer Id = randomNum();
				sName = InameTF.getText();
				sLevel = IlevelTF.getText();
				sType = ItypeTF.getText();

				if (validateAll(sName, sLevel, sType) == false) {
					JOptionPane.showMessageDialog(null, "All Fields Must Be filled!");

				} else if (validateNumber(sLevel) == false) {
					JOptionPane.showMessageDialog(null, "Level must be a number!");

				} else if (sLevel.equals("0")) {
					JOptionPane.showMessageDialog(null, "Level must more than equal to 1");

				} else if (validateNumber(sLevel) == true) {
					String query = "INSERT INTO pokemon VALUES ('" + Id + "', '" + sName + "', '"
							+ Integer.parseInt(sLevel) + "', '" + sType + "')";
					con.executeUpdate(query);
					setupTabel();
					JOptionPane.showMessageDialog(null, "Insert Success!");

				}

			}

		});

		bDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String Id;
				Id = DIdTF.getText();

				if (Id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please input the ID!");

				} else {
					String query = "DELETE FROM pokemon WHERE PokemonId = '" + Id + "'";
					con.executeUpdate(query);
					setupTabel();
					JOptionPane.showMessageDialog(null, "Delete Success!");
				}

			}

		});

		bUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String Id, sName, sLevel, sType;
				Id = UIdTF.getText();
				sName = UnameTF.getText();
				sLevel = UlevelTF.getText();
				sType = UtypeTF.getText();

				if (Id.isEmpty() || sName.isEmpty() || sLevel.isEmpty() || sType.isEmpty()) {
					JOptionPane.showMessageDialog(null, "All Fields Must Be filled!");

				} else if (validateNumber(Id) == false) {
					JOptionPane.showMessageDialog(null, "ID must be a number!");

				} else if (sLevel.equals("0")) {
					JOptionPane.showMessageDialog(null, "Level must more than equal to 1");

				} else if (validateNumber(sLevel) == false) {
					JOptionPane.showMessageDialog(null, "Level must be a number!");

				} else {
					String query = "UPDATE pokemon SET PokemonName = '" + sName + "', PokemonLevel = '"
							+ Integer.parseInt(sLevel) + "', PokemonType = '" + sType + "' WHERE PokemonId = '" + Id
							+ "'";
					con.executeUpdate(query);
					setupTabel();
					JOptionPane.showMessageDialog(null, "Update Success!");
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

			dtmManage.fireTableDataChanged();

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

	public boolean validateAll(String valName, String valLevel, String valType) {

		if (valName.equals("") || valLevel.equals("") || valType.equals("")) {
			return false;
		}

		return true;

	}

	Integer randomNum() {

		Integer random, j;
		return random = randomInteger(40, 100);

	}

	int randomInteger(int min, int max) {
		return min + rd.nextInt(max - min + 1);

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
