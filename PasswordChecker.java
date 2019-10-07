import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class PasswordChecker extends JDialog {

	private JPasswordField pfPassword, confirmPwd;
	private JTextField newPwd;
	private JLabel lbPassword, lbNP, lbCP;
	private JButton btnLogin, createPwd;
	private boolean succeeded;
	private int attempts =1;
	private File pwd = new File("pwd.txt");
	
	public PasswordChecker(Frame parent) throws IOException {
		super(parent, "Login", true);
			//add image
		URL url = new URL("https://icon-library.net/images/user-icon-jpg/user-icon-jpg-7.jpg");
		BufferedImage pic = ImageIO.read(url);
		JLabel picLabel = new JLabel (new ImageIcon(pic));
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.white);
		JPanel icon = new JPanel();
		icon.setBackground(Color.white);
		icon.add(picLabel);
		GridBagConstraints cs = new GridBagConstraints();
 
		
		cs.fill = GridBagConstraints.HORIZONTAL;
 
		lbPassword = new JLabel("Password: ");
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(lbPassword, cs);
 
		pfPassword = new JPasswordField(20);
		cs.gridx = 2;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(pfPassword, cs);
		panel.setBorder(new LineBorder(Color.GRAY));
 
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (authenticate(getPassword())) {
						JOptionPane.showMessageDialog(PasswordChecker.this,
								"You have successfully logged in!",
								"Login",
								JOptionPane.INFORMATION_MESSAGE);
						succeeded = true;
						dispose();
					} else {
						
						if(attempts>=3){
							JOptionPane.showMessageDialog(PasswordChecker.this, "You have entered the incorrect password 3 times. Exiting program", "Login", JOptionPane.ERROR_MESSAGE);
							System.exit(0);;
							
						}else{
						JOptionPane.showMessageDialog(PasswordChecker.this,
								"Invalid password, please try again. Attempt left: " + (3-attempts),
								"Login",
								JOptionPane.ERROR_MESSAGE);
						// reset password
						pfPassword.setText("");
						succeeded = false;
						attempts++;
						}
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	 
		JPanel bp = new JPanel();
		bp.setBackground(Color.white);
		bp.add(btnLogin);
		getContentPane().add(icon, BorderLayout.PAGE_START);
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
		}
 
 
	@SuppressWarnings("deprecation")
	public void setPassword(Frame parent){
		lbNP = new JLabel("Please set a password"); 
		lbCP = new JLabel("Password: ");
		JButton b = new JButton("submit"); 
		newPwd = new JTextField(20); 
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		// addActionListener to button 
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter("pwd.txt"));
					writer.write(newPwd.getText());
					JOptionPane.showMessageDialog(parent, "Password set successfully! Please run application again");
					writer.close();
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}});
  
		// create a panel to add buttons and textfield 
		JPanel p = new JPanel(new GridBagLayout()); 
  
		// add buttons and textfield to panel 
		cs.gridy=0;
		cs.gridx=0;
		p.add(lbNP, cs); 
		cs.gridy=1;
		cs.gridx=0;
		p.add(lbCP, cs); 
		cs.gridx=1;
		p.add(newPwd, cs);
		cs.gridy=2;
		p.add(b, cs); 
  
		parent.add(p); 
		parent.setSize(300, 300); 
  
		parent.show();
		
	}
	public String getPassword() {
		return new String(pfPassword.getPassword());
	}
 
	public boolean isSucceeded() {
		return succeeded;
	}
	
	public static boolean authenticate(String password) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("pwd.txt"));
		String pass = reader.readLine();
		if (password.equals(pass)) {
			return true;
		}
		reader.close();
		return false;
	}
}
