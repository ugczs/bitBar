package com.kobil.bitBarTesting;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Diese Klasse gibt die eingelesenen Daten als Datei aus.
 * @author Yuguan Zhao
 */
public class OutPut {
	private String destination;
	private ImageIcon icon = new ImageIcon(this.getClass().getResource("/kobil.png"));
	
	public OutPut(String destination) {
		this.destination = destination;
	}
	
	@SuppressWarnings("null")
	public void setText(String s) {
		PrintWriter os = null;
		try {
			os = new PrintWriter(this.destination);
			os.println(s);
			os.close();
		} 
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Enter a valid path!", "Error", JOptionPane.INFORMATION_MESSAGE, icon);
			UtilMethods.print(e);
			os.close();
		}
	
	}

	@SuppressWarnings("null")
	public void setText(SavedValue sv) {
		PrintWriter os = null;
		try {
			os = new PrintWriter(this.destination);
//			os.println(sv.getsProjectId() + sv.getsTestId() + sv.getsApiKey() + sv.getsStorage());
			os.println(sv.getsProjectId());
			os.println(sv.getsTestId());
			os.println(sv.getsApiKey());
			os.println(sv.getsStorage());
			os.close();
		} 
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Enter a valid path!", "Error", JOptionPane.INFORMATION_MESSAGE, icon);
			UtilMethods.print(e);
			os.close();
		}
	
	}
}
