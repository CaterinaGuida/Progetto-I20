package dBManagement;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import dBManagement.actionListener.AddProduct;

@SuppressWarnings("serial")
public class ProductPanel extends JPanel {
	JTextArea name,code;
	JButton addP;
	JPanel text,button;
	public ProductPanel() {
		setLayout(new GridLayout(2,1));
		text=new JPanel();
		text.setBackground(Color.WHITE);
		button=new JPanel();
		button.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		TitledBorder t=BorderFactory.createTitledBorder("nome");
		t.setTitleColor(Color.BLUE);
		TitledBorder n=BorderFactory.createTitledBorder("codice");
		n.setTitleColor(Color.BLUE);
		this.setBackground(Color.WHITE);
		name=new JTextArea(1,10);
		name.setBorder(t);
		code=new JTextArea(1,10);
		code.setBorder(n);
		addP=new JButton("Aggiungi prodotto");
		addP.setBackground(Color.CYAN);
		addP.addActionListener(new AddProduct(name,code));
		text.add(name);
		text.add(code);
		add(text);
		button.add(addP);
		add(button);
		}

}
