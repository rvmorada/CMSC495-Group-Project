import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
 
public class PasswordChecker extends JDialog {
 
	private JPasswordField pfPassword;
	private JLabel lbPassword;
	private JButton btnLogin;
	private boolean succeeded;
	private int attempts =1;
 
	public PasswordChecker(Frame parent) throws IOException {
		super(parent, "Login", true);
		//
		//add image
		URL url = new URL("https://icon-library.net/images/user-icon-jpg/user-icon-jpg-7.jpg");
		BufferedImage pic = ImageIO.read(url);
		JLabel picLabel = new JLabel (new ImageIcon(pic));
		
		JPanel panel = new JPanel(new GridBagLayout());
		JPanel icon = new JPanel();
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
			}
		});
	 
		JPanel bp = new JPanel();
		bp.add(btnLogin);
		getContentPane().add(icon, BorderLayout.PAGE_START);
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);
 
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
 
 
	public String getPassword() {
		return new String(pfPassword.getPassword());
	}
 
	public boolean isSucceeded() {
		return succeeded;
	}
	
	public static boolean authenticate(String password) {
		if (password.equals("test")) {
			return true;
		}
		return false;
	}
}