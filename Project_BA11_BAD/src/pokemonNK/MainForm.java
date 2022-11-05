package pokemonNK;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import pokemonk.DBConnect;

public class MainForm extends JFrame implements MenuListener, ActionListener {

	JDesktopPane dp;
	JMenuBar menuBar;
	JMenu userMenu, adventureMenu, transactionMenu, manageMenu;
	JMenuItem miLogout, miPokemonMarket, miBag, miViewHistory, miManagePokemon;

	DBConnect con = DBConnect.getConnection();

	String role = Login.role;
	String role1 = Register.role1;

	public MainForm() {
		setDesktopPane();
		setFrame();
		setMenu();
	}

	private void setDesktopPane() {
		
		JPanel main = new JPanel(new GridLayout(1, 1));
		JPanel txtpnl = new JPanel(new GridLayout(1,1));
		txtpnl.setBackground(Color.CYAN);
		JLabel title = new JLabel("PokemoNK");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Sans Serif", Font.BOLD, 64));
		title.setBackground(Color.CYAN);
		txtpnl.add(title);
		
		main.add(txtpnl);
		add(main);
	}

	private void setMenu() {
		menuBar = new JMenuBar();
		
		userMenu = new JMenu("User");
		adventureMenu = new JMenu("Adventure");
		transactionMenu = new JMenu("Transaction History");
		manageMenu = new JMenu("Manage");

		miLogout = new JMenuItem("Logout");
		miPokemonMarket = new JMenuItem("Pokemon Market");
		miBag = new JMenuItem("Bag");
		miViewHistory = new JMenuItem("View Transaction History");
		miManagePokemon = new JMenuItem("Manage Pokemon");

		userMenu.setMnemonic('U');
		adventureMenu.setMnemonic('A');
		transactionMenu.setMnemonic('T');
		manageMenu.setMnemonic('M');

		miLogout.setMnemonic('L');
		miPokemonMarket.setMnemonic('P');
		miBag.setMnemonic('B');
		miViewHistory.setMnemonic('H');
		miManagePokemon.setMnemonic('J');

		userMenu.add(miLogout);
		adventureMenu.add(miPokemonMarket);
		adventureMenu.add(miBag);
		manageMenu.add(miManagePokemon);
		transactionMenu.add(miViewHistory);

		userMenu.addMenuListener(this);
		adventureMenu.addMenuListener(this);
		transactionMenu.addMenuListener(this);
		manageMenu.addMenuListener(this);

		miLogout.addActionListener(this);
		miPokemonMarket.addActionListener(this);
		miBag.addActionListener(this);
		miManagePokemon.addActionListener(this);
		miViewHistory.addActionListener(this);

		role();
		role1();
		setJMenuBar(menuBar);
		JPanel mainPanel = new JPanel();
		JLabel pokemonNK = new JLabel("PokemonNK");
		mainPanel.add(pokemonNK);

	}

	private void role() {
		if (role.equals("Player")) {
			menuBar.add(userMenu);
			menuBar.add(adventureMenu);
			menuBar.add(transactionMenu);
			setTitle("Welcome User");
		} else if (role.equals("Admin")) {
			menuBar.add(userMenu);
			menuBar.add(manageMenu);
			setTitle("Welcome Admin");
		}
	}

	private void role1() {
		if (role1.equals("Player")) {
			menuBar.add(userMenu);
			menuBar.add(adventureMenu);
			menuBar.add(transactionMenu);
		} else if (role1.equals("Admin")) {
			menuBar.add(userMenu);
			menuBar.add(manageMenu);
		}
	}

	private void setFrame() {
		setVisible(true);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void menuCanceled(MenuEvent e) {

	}

	@Override
	public void menuDeselected(MenuEvent e) {

	}

	@Override
	public void menuSelected(MenuEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == miLogout) {
			this.dispose();
			Login lf = new Login();

		} else if (arg0.getSource() == miPokemonMarket) {
			this.dispose();
			BuyForm bf = new BuyForm();

		} else if (arg0.getSource() == miManagePokemon) {
			this.dispose();
			ManagePokemon mp = new ManagePokemon();
		} else if (arg0.getSource() == miBag) {
			this.dispose();
			Bag b = new Bag();
		} else if (arg0.getSource() == miViewHistory) {
			this.dispose();
			History h = new History();
		}
	}

	public static void main(String[] args) {
	}
}
