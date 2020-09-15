package dBManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	MainPanel panel;
	public Frame() {

		setTitle("Gestione Database");
		setSize(900,500);
		panel=new MainPanel();
		setLayout(new BorderLayout());
		getContentPane().add(panel,BorderLayout.CENTER);
		set();
	}
	private void set() {
		setTitle("Gestione Database");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height+200;
		int screenWidth = screenSize.width+400;
		setSize(screenWidth/2,screenHeight/2);
		setLocation(screenWidth/4,screenHeight/4);
	}
}
