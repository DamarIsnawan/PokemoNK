package pokemonNK;

// Notes : untuk roles kami random Kak. oleh karena itu, untuk mendapatkan role antara member dan admin, bisa untuk melakukan login hingga mendapatkan role yang diinginkan

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pokemonk.DBConnect;


public class Login extends JFrame {
	

	static String role = "";
//	HashMap<String,String> login = new HashMap<String,String>();
	
	DBConnect con = DBConnect.getConnection();

	
	public Login() {

				JPanel panel1 = new JPanel(new GridLayout(9,1));
				panel1.setBackground(Color.CYAN);
				JLabel loginlbl = new JLabel ("Login");
				loginlbl.setHorizontalAlignment(JLabel.CENTER);
				
				panel1.setBorder(new EmptyBorder(30, 100, 30, 100));
				panel1.add(loginlbl);
				
				//username
				JPanel usernamepnl = new JPanel(new GridLayout(2,1));
				usernamepnl.setBackground(Color.CYAN);
				JLabel usernamelbl = new JLabel("Username");
				usernamelbl.setHorizontalAlignment(JLabel.LEADING);
				usernamepnl.add(usernamelbl);
				
				JTextField usernameTF = new JTextField();
				usernamepnl.add(usernameTF);
				panel1.add(usernamepnl);
				
				//pass
				
				JPanel passpnl = new JPanel(new GridLayout(2,1));
				passpnl.setBackground(Color.CYAN);
				JLabel passlbl = new JLabel("Password");
				passlbl.setHorizontalAlignment(JLabel.LEADING);
				passpnl.add(passlbl);
				
				JPasswordField passTA = new JPasswordField();
				passpnl.add(passTA);
				panel1.add(passpnl);
				
				//button
				JPanel bPanel = new JPanel();
				bPanel.setBackground(Color.CYAN);
				JButton bLogin = new JButton("Login");		
				
				JButton bRegist = new JButton("Register");		
				bLogin.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String username = usernameTF.getText();
						String password = String.valueOf(passTA.getPassword());

						if (username.isEmpty() || password.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Username or Password cannot be Empty!");
						} else {

							String query = "SELECT Username, Name, Password FROM user WHERE Username = '"+username+"' AND Password = '"+password+"'";
							ResultSet rs = con.executeQuery(query);
												
							usernameTF.setText("");
							passTA.setText("");

							try {
								if (rs.next()) {
									username = rs.getString(1);
									String name = rs.getString(2);
							        Random rd = new Random();
							        String [] arr = {"Admin", "Player"};
							         // randomly selects an index from the arr
							        int select = rd.nextInt(arr.length); 
							        role = arr[select];
							        
									JOptionPane.showMessageDialog(null, "Welcome, " + name);
									dispose();
									MainForm mf = new MainForm();
								} else {
									JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
								}

							} catch (SQLException e2) {
								e2.printStackTrace();
							}

						}

					}
				});

				bRegist.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						Register r = new Register();
						
					}
					
				});
				
				bPanel.add(bLogin);
				bPanel.add(bRegist);
				panel1.add(bPanel);
				
				add(panel1);
				init();

		
				
				
	}
	
	public void init() {
		this.setSize(600, 400);
		setTitle("PokemoNK");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Login();
	}

}