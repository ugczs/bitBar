package com.kobil.bitBarTesting;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Hauptfenster zur Berechnung von Messergebnissen
 * @author Yuguan Zhao
 */
public class UserInterFace {
	private JFrame frame;
	private JTextField projectId;
	private JTextField testId;
	private JTextField apiKey;
	private JTextField path;
	
	private JButton okButton;
	private JButton selectFile;
	
	private JMenuItem getLinkContent;
	private JMenuItem bitBarProject;
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem exit;
	
	private String version = "Bitbar Fetcher v 1.3.2";
	private String pId;
	private String tId;
	private String auth;
	private String url;
	private String url1;
	private String url2;
	private String url3;
	private String storage;
	private String path2;
	private ImageIcon icon;
	
	private JMenu menu1;
	private JMenu menu2;

	/**
	 * Create the application.
	 */
	public UserInterFace() {
		url1 = "https://cloud.bitbar.com/api/v2/me/projects/";
		url2 = "/runs/";
		url3 = "/device-sessions?limit=250";
		icon  = new ImageIcon(this.getClass().getResource("/kobil.png"));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(version);
//		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setSize(400, 240);
		
		JLabel lblNewLabel = new JLabel("Project ID:");
		lblNewLabel.setBounds(30, 38, 98, 16);
		frame.getContentPane().add(lblNewLabel);
		
		projectId = new JTextField();
		projectId.setBounds(129, 33, 258, 26);
		frame.getContentPane().add(projectId);
		projectId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Test ID:");
		lblNewLabel_1.setBounds(30, 71, 98, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		testId = new JTextField();
		testId.setBounds(129, 66, 258, 26);
		frame.getContentPane().add(testId);
		testId.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("API KEY:");
		lblNewLabel_2.setBounds(30, 104, 98, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		apiKey = new JTextField("AnDVcMDiWB4BqGRi3r7j7LUYBYWucywv");
		apiKey.setBounds(129, 99, 258, 26);
		frame.getContentPane().add(apiKey);
		apiKey.setColumns(10);
		
		selectFile = new JButton("Storage...");
		selectFile.setBounds(19, 132, 98, 29);
		frame.getContentPane().add(selectFile);
		
		path = new JTextField("validDevices.csv");
		path.setBounds(129, 132, 258, 26);
		frame.getContentPane().add(path);
		path.setColumns(10);
		
		okButton = new JButton("OK");
		okButton.setBounds(127, 170, 117, 29);
		frame.getContentPane().add(okButton);
		
		JMenuBar menubar = new JMenuBar();
		menubar.setBounds(0, 0, frame.getWidth(), 26);
		menu1 = new JMenu("Option");
		menu2 = new JMenu("Info");
		getLinkContent = new JMenuItem("Get Link Content");
		bitBarProject = new JMenuItem("Bitbar Project");
		save = new JMenuItem("Save Input");
		load = new JMenuItem("Load Input");
		exit = new JMenuItem("Exit");
		
		getLinkContent.setAccelerator(KeyStroke.getKeyStroke('G', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		bitBarProject.setAccelerator(KeyStroke.getKeyStroke('B', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		save.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		load.setAccelerator(KeyStroke.getKeyStroke('L', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		exit.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		
		menu1.add(getLinkContent);
		menu1.add(bitBarProject);
		menu1.add(save);
		menu1.add(load);
		menu1.add(exit);
		menubar.add(menu1);
		menubar.add(menu2);
		frame.getContentPane().add(menubar);
		
		Handler handler = new Handler();
		okButton.addActionListener(handler);
		selectFile.addActionListener(handler);
		getLinkContent.addActionListener(handler);
		bitBarProject.addActionListener(handler);
		save.addActionListener(handler);
		load.addActionListener(handler);
		exit.addActionListener(handler);
		
		MenuHandler menuHandler = new MenuHandler();
		menu2.addMouseListener(menuHandler);
			
		KeyListener kl = new Keyhandler();
		projectId.addKeyListener(kl);
		testId.addKeyListener(kl);
		apiKey.addKeyListener(kl);
		selectFile.addKeyListener(kl);
		path.addKeyListener(kl);
		okButton.addKeyListener(kl);
	}
	
	private void getResult() {
		try {
			url = url1 + pId + url2 + tId + url3;
			RestApi ra;
			ra = new RestApi(url, auth);
			JsParser jp = new JsParser(ra.getResponse());
	        jp.getBody().setValidDevices2();
	        if(path.getText() == null) {
	        	JOptionPane.showMessageDialog(null, "Choose a location to save the result!", "Error", JOptionPane.INFORMATION_MESSAGE, this.icon);
	        }
	        else {
	        	OutPut o = new OutPut(path.getText().trim());
				o.setText(jp.getBody().getAllValidDevices());
				JOptionPane.showMessageDialog(null, "Done!", "Success", JOptionPane.INFORMATION_MESSAGE, this.icon);
	        }  
		} 
		catch(Exception e) {
			UtilMethods.print(e);
			JOptionPane.showMessageDialog(null, "Invalid input combination!", "Error", JOptionPane.INFORMATION_MESSAGE, this.icon);
		}
	}
	
	private void okFunction() {
		pId = projectId.getText().trim();
		tId = testId.getText().trim();
		auth = apiKey.getText().trim();			
		getResult();
	}
	
	private void saveFunction() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Save");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			path2 = chooser.getSelectedFile().toString() + File.separator + "bBConfig.dat" ;
			SavedValue sv = new SavedValue(projectId.getText() + ";", testId.getText() + ";", apiKey.getText() + ";", path.getText());
			OutPut o2 = new OutPut(path2);
			o2.setText(sv);
		} 
		else {
			UtilMethods.print("No Selection");
		}
	}
	
	private void loadingFunction() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("dat, txt or csv", "txt", "dat", "csv");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(filter);	
		chooser.setDialogTitle("Load Saved Input");
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String loadingPath = chooser.getSelectedFile().toString();
			try {
				JsReader jr = new JsReader(loadingPath);
				JsParser jp = new JsParser();
				jp.splitSemicolon(jr.getContent());
				projectId.setText(jp.getBody().getContent(0));
				testId.setText(jp.getBody().getContent(1));
				apiKey.setText(jp.getBody().getContent(2));
				path.setText(jp.getBody().getContent(3));
				UtilMethods.print(jr.getContent());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else {
			UtilMethods.print("No Selection");
		}
	}
	
	private void selectFileFunction() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choose Directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			storage = chooser.getSelectedFile().toString() + File.separator + "validDevices.csv" ;
			path.setText(storage);
		} 
		else {
			UtilMethods.print("No Selection");
		}
	}
	
	private void getLinkContentFunction() {
		String result = JOptionPane.showInputDialog(frame, "Enter URL from a Bitbar testing run: ", "Enter URL", JOptionPane.PLAIN_MESSAGE);
		if (result!= null) {
			if(!result.startsWith("https://cloud.bitbar.com/#testing/test-run")) {
				JOptionPane.showMessageDialog(null, "Enter a valid Bitbar testing run URL like: "
						+ "\n" + "https://cloud.bitbar.com/#testing/test-run/xxxxxxxxx/xxxxxxxxx", "Error", JOptionPane.INFORMATION_MESSAGE, this.icon);
			}
			else {
				ArrayList<String> l = new ArrayList<>(Arrays.asList(result.split("test-run/")));
				if(l.size() <= 1 || !l.get(1).contains("/")) {
					JOptionPane.showMessageDialog(null, "Enter a valid Bitbar testing run URL like: "
							+ "\n" + "https://cloud.testdroid.com/#testing/test-run/xxxxxxxxx/xxxxxxxxx", "Error", JOptionPane.INFORMATION_MESSAGE, this.icon);
				}
				else {
					ArrayList<String> l2 = new ArrayList<>(Arrays.asList(l.get(1).split("/")));
					projectId.setText(l2.get(0));
					testId.setText(l2.get(1));
					UtilMethods.print(l.get(0));
					UtilMethods.print(l.get(1));
					UtilMethods.print(l2.get(0));
					UtilMethods.print(l2.get(1));
				}
	
			}
		}
		else {
			UtilMethods.print("No Bitbar URL entered!");
		} 
	}
	
	private void getInfo() {
		JOptionPane.showMessageDialog(null, version + "\n" + "Copyright 2018 Yuguan Zhao\nAll rights reserved\n" + "Contact: yuguan.zhao@kobil.com", "Info", JOptionPane.INFORMATION_MESSAGE, this.icon);
	}
	
	private void bitBarProjectFunction() {
		try {
			  Desktop desktop = java.awt.Desktop.getDesktop();
			  URI l = new URI("https://cloud.bitbar.com/#testing/projects");
			  desktop.browse(l);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Link is not valid anymore!", "Error", JOptionPane.INFORMATION_MESSAGE, this.icon);
			}
	}
	
	
	private class Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {	
			if(arg0.getSource() == okButton) {
				okFunction();
			}
			else if(arg0.getSource() == bitBarProject) {
				bitBarProjectFunction();
			}
			else if(arg0.getSource() == getLinkContent) {
				getLinkContentFunction();
			}
			else if(arg0.getSource() == save) {
				saveFunction();
			}
			else if(arg0.getSource() == load) {
				loadingFunction();
			}
			else if(arg0.getSource() == selectFile) {
				selectFileFunction();
			}
			else if(arg0.getSource() == exit) {
				System.exit(0);
			}
		}
	}
	
	private class MenuHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getSource() == menu2) {
				getInfo();
			}
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
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	
	}
	
	private class Keyhandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()== KeyEvent.VK_ENTER && arg0.getSource() == selectFile) {
				selectFile.doClick();
			}
			else if(arg0.getKeyCode()== KeyEvent.VK_ENTER) {
				okButton.doClick();
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
		return this.frame;
	}

}
