package dBManagement;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AnswerPanel extends JPanel {
	JTextArea text,num;
	public AnswerPanel() {
		TitledBorder t=BorderFactory.createTitledBorder("testo");
		t.setTitleColor(Color.BLUE);
		TitledBorder n=BorderFactory.createTitledBorder("numero");
		n.setTitleColor(Color.BLUE);
		this.setBackground(Color.WHITE);
		text=new JTextArea(1,10);
		text.setBorder(t);
		num=new JTextArea(1,10);
		num.setBorder(n);
		add(text);
		add(num);
	}
	public String getText() {
		return text.getText();
	}
	public String getNumber() {
		return num.getText();
	}
	public void setText() {
		this.text.setText("");
	}
	public void setNumber() {
		num.setText("");
	}
}
