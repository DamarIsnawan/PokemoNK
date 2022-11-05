package pokemonNK;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pokemonk.DBConnect;


public class Register extends JFrame {
	
	HashMap<String,String> login = new HashMap<String,String>();
	
	DBConnect con = DBConnect.getConnection();
	static String role1 = "";
	Random rd = new Random();
	
	public Register() {
				JPanel panel1 = new JPanel(new GridLayout(10,1));
				panel1.setBackground(Color.CYAN);
				JLabel registlbl = new JLabel ("Register");
				registlbl.setHorizontalAlignment(JLabel.CENTER);
				panel1.add(registlbl);
				
				panel1.setBorder(new EmptyBorder(30, 100, 30, 100));
				
				//username
				JPanel usernamepnl = new JPanel(new GridLayout(2,1));
				usernamepnl.setBackground(Color.CYAN);
				JLabel usernamelbl = new JLabel("Username");
				usernamelbl.setHorizontalAlignment(JLabel.LEADING);
				usernamepnl.add(usernamelbl);
				
				JTextField usernameTF = new JTextField();
				usernamepnl.add(usernameTF);
				panel1.add(usernamepnl);
				
				
				
				//firstname
				
				JPanel firstpnl = new JPanel(new GridLayout(2,1));
				firstpnl.setBackground(Color.CYAN);
				JLabel firstlbl = new JLabel("First Name");
				firstlbl.setHorizontalAlignment(JLabel.LEADING);
				firstpnl.add(firstlbl);
				
				JTextField firstTA = new JTextField();
				firstpnl.add(firstTA);
				panel1.add(firstpnl);
				
				//lastname
				
				JPanel lastpnl = new JPanel(new GridLayout(2,1));
				lastpnl.setBackground(Color.CYAN);
				JLabel lastlbl = new JLabel("Last Name");
				lastlbl.setHorizontalAlignment(JLabel.LEADING);
				lastpnl.add(lastlbl);
				
				JTextField lastTA = new JTextField();
				lastpnl.add(lastTA);
				panel1.add(lastpnl);
				
				//email
				
				JPanel emailpnl = new JPanel(new GridLayout(2,1));
				emailpnl.setBackground(Color.CYAN);
				JLabel emaillbl = new JLabel("Email");
				emaillbl.setHorizontalAlignment(JLabel.LEADING);
				emailpnl.add(emaillbl);
				
				JTextField emailTA = new JTextField();
				emailpnl.setPreferredSize(new Dimension (100, 50));
				emailpnl.add(emailTA);
				panel1.add(emailpnl);
				
				//gender
				JPanel genderPnl = new JPanel(new GridLayout(2,1));
				genderPnl.setBackground(Color.CYAN);
				JRadioButton maleRB = new JRadioButton("Male");
				JRadioButton femaleRB = new JRadioButton("Female");
				maleRB.setBackground(Color.CYAN);
				femaleRB.setBackground(Color.CYAN);
				ButtonGroup genderBG = new ButtonGroup();
				genderBG.add(maleRB);
				genderBG.add(femaleRB);
				
				
				genderPnl.add(maleRB);
				genderPnl.add(femaleRB);
				panel1.add(genderPnl);
				
				//Spinner
				JPanel agepnl = new JPanel(new GridLayout(2,1));
				agepnl.setBackground(Color.CYAN);
				JLabel agelbl = new JLabel("Age");
				agelbl.setHorizontalAlignment(JLabel.LEADING);
				agepnl.add(agelbl);
				SpinnerModel ageModel = new SpinnerNumberModel(
						0,
						0,
						100,
						1
						);
						
				JSpinner ageSpinner = new JSpinner(ageModel);
				ageSpinner.setPreferredSize(new Dimension (100, 25));
				agepnl.add(ageSpinner);
				panel1.add(agepnl);

				//pass
				
				JPanel passpnl = new JPanel(new GridLayout(2,1));
				passpnl.setBackground(Color.CYAN);
				JLabel passlbl = new JLabel("Password");
				passlbl.setHorizontalAlignment(JLabel.LEADING);
				passpnl.add(passlbl);
				
				JPasswordField passTA = new JPasswordField();
				passpnl.add(passTA);
				panel1.add(passpnl);
				
				//cpass
				
				JPanel cpasspnl = new JPanel(new GridLayout(2,1));
				cpasspnl.setBackground(Color.CYAN);
				JLabel cpasslbl = new JLabel("Confirm Password");
				cpasslbl.setHorizontalAlignment(JLabel.LEADING);
				cpasspnl.add(cpasslbl);
				
				JPasswordField cpassTA = new JPasswordField();
				cpasspnl.add(cpassTA);
				panel1.add(cpasspnl);
				
				//button
				JPanel bPanel = new JPanel();
				bPanel.setBackground(Color.CYAN);
				JButton bregist = new JButton("Register");		
				
				JButton bBack = new JButton("Back to Login");		
				
				bregist.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String username = usernameTF.getText();
						String firstname = firstTA.getText();
						String lastname = lastTA.getText();
						String name = firstname + lastname;
						String email = emailTA.getText();
						Integer age = (Integer) ageSpinner.getValue();
						String gender = getSelectedButtonText(genderBG);
						String password = String.valueOf(passTA.getPassword());
						String cpassword = String.valueOf(cpassTA.getPassword());
						Integer userId = randomNum();

						if (username.isEmpty() || password.isEmpty() || email.isEmpty() || firstname.isEmpty() || lastname.isEmpty() ||gender.isEmpty() || age.equals(0) ||cpassword.isEmpty()) {
							JOptionPane.showMessageDialog(null, "User should fill all the field!");
						} else if (password == cpassword) {
							JOptionPane.showMessageDialog(null, "Password and confirm password should be the same value!");
						} else if (age < 11) {
							JOptionPane.showMessageDialog(null, "Age must more than equal to 11");
						} else if ((!email.contains("@")) || (!email.contains(".com"))) {
							JOptionPane.showMessageDialog(null, "Email should use '@' and '.com'");
						} else {
							
							String query = "INSERT INTO user VALUES ('"+userId+"', '"+username+"', '"+name+"', '"+age+"', '"+email+"', '"+gender+"', '"+password+"')";
							con.executeUpdate(query);
												
							usernameTF.setText("");
							firstTA.setText("");
							lastTA.setText("");
							emailTA.setText("");
							cpassTA.setText("");
							passTA.setText("");

							Random rd = new Random();
					        String [] arr = {"Admin", "Player"};
					         // randomly selects an index from the arr
					        int select = rd.nextInt(arr.length); 
					        role1 = arr[select];
					        
					        JOptionPane.showMessageDialog(null, "Register Success");
							JOptionPane.showMessageDialog(null, "Welcome, " + name);
							dispose();
							MainForm mf = new MainForm();

							
						}

					}
				});
				
				bBack.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						Login l = new Login();
						
					}
					
				});
				
				bPanel.add(bregist);
				bPanel.add(bBack);
				panel1.add(bPanel);
				
				add(panel1);
				init();

				
				
	}
	
	Integer randomNum() {

		Integer random, j;
		return random = randomInteger(0, 100);


	}
	
	int randomInteger(int min, int max) {
		return min + rd.nextInt(max - min + 1);

	}
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
	}
	
	public void init() {
		setTitle("PokemoNK");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
	}

}