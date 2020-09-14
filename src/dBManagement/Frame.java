package dBManagement;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	MainPanel panel;
	public Frame() {
		setTitle("gestione DataBase");
		setSize(850,500);
		panel=new MainPanel();
		getContentPane().add(panel);
	}
}
