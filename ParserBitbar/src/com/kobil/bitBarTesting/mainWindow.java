package com.kobil.bitBarTesting;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

/**
 * Mainmethode des Programms
 * @author Yuguan Zhao
 */
public class mainWindow {

	public static void main(String[] args) {
//		UserInterFace window = new UserInterFace();
//		window.setVisible(true);
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setSize(400, 200);
//		window.setResizable(false);
//		window.pack();
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//		window.setLocation(dim.width/2 - window.getSize().width/2, dim.height/2 - window.getSize().height/2);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
//				try {
//					UserInterFace2 window = new UserInterFace2();
//					window.getFrame().setVisible(true);
//					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//					window.getFrame().setLocation(dim.width/2 - window.getFrame().getSize().width/2, dim.height/2 - window.getFrame().getSize().height/2);
//				} 
//				catch (Exception e) {
//					e.printStackTrace();
//				}
				
				try {
					Login window = new Login();
					window.getFrame().setVisible(true);
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					window.getFrame().setLocation(dim.width/2 - window.getFrame().getSize().width/2, dim.height/2 - window.getFrame().getSize().height/2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
