package dBManagement;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	JTabbedPane pane;
	QuestionPanel question;
	ProductPanel product;
	SolutionPanel solution;
	ImageIcon image;
	AttentionPanel attention;
	public MainPanel() {
		question=new QuestionPanel();
		product=new ProductPanel();
		solution=new SolutionPanel();
		attention=new AttentionPanel();
		pane=new JTabbedPane();
		setBackground(Color.WHITE);
		pane.addTab("ATTENZIONE!!", attention);
		pane.setMnemonicAt(0, KeyEvent.VK_1);
		pane.addTab("aggiungi prodotto", product);
		pane.setMnemonicAt(1, KeyEvent.VK_2);
		pane.addTab("aggiungi domanda", question);
		pane.setMnemonicAt(2, KeyEvent.VK_3);
		pane.addTab("aggiungi soluzione", solution);
		pane.setMnemonicAt(3, KeyEvent.VK_4);
		pane.setBackground(Color.WHITE);
		add(pane);
	}

}
