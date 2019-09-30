package com.kobil.bitBarTesting;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

/**
 * Login Fenster von dem Programm
 * @author Yuguan Zhao
 */
public class Login {
	private JFrame frame;
	private JTextField textFieldName;
	private JPasswordField passwordField;
	private JButton logButton;
	private ImageIcon icon;

	/**
	 * Create the application.
	 */
	public Login() {
		icon  = new ImageIcon(this.getClass().getResource("/kobil.png"));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(370, 220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel loginName = new JLabel("Name: ");
		loginName.setBounds(136, 41, 67, 16);
		frame.getContentPane().add(loginName);
		
		JLabel loginPass = new JLabel("Password: ");
		loginPass.setBounds(136, 87, 67, 16);
		frame.getContentPane().add(loginPass);
		
		textFieldName = new JTextField();
		textFieldName.setText("Kobil123");
		textFieldName.setBounds(213, 36, 130, 26);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(215, 84, 130, 21);
		frame.getContentPane().add(passwordField);
		
		JLabel kLogo = new JLabel(icon);
		kLogo.setBounds(22, 36, 88, 80);
		frame.getContentPane().add(kLogo);
		
		logButton = new JButton("Log In");
		logButton.setBounds(136, 142, 117, 29);
		frame.getContentPane().add(logButton);
		
		Handler handler = new Handler();
		logButton.addActionListener(handler); 
		
		KeyListener kl = new Keyhandler();
		passwordField.addKeyListener(kl);
		textFieldName.addKeyListener(kl);
		logButton.addKeyListener(kl);
	}
	@SuppressWarnings("deprecation")
	private void logButtonFunction() {
		if (!textFieldName.getText().equals("Kobil123") || !passwordField.getText().equals("Kobil123")) {
			JOptionPane.showMessageDialog(null, "Invalid username or password!", "Error", JOptionPane.INFORMATION_MESSAGE, icon);
		}
		else {
			try {
				frame.dispose();
				UserInterFace window = new UserInterFace();
				window.getFrame().setVisible(true);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				window.getFrame().setLocation(dim.width/2 - window.getFrame().getSize().width/2, dim.height/2 - window.getFrame().getSize().height/2);
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private class Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {	
			if(arg0.getSource() == logButton) {
				logButtonFunction();
			}
		}
	}
	
	private class Keyhandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()== KeyEvent.VK_ENTER) {
				logButton.doClick();
			}	
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
