package dBManagement;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import dBManagement.actionListener.AddProduct;
import dBManagement.actionListener.AddSolution;

@SuppressWarnings("serial")
public class SolutionPanel extends JPanel {
	JTextArea description,code,product;
	JButton addS;
	JPanel text,button;

	public SolutionPanel()  {
		setLayout(new GridLayout(2,1));
		text=new JPanel();
		text.setBackground(Color.WHITE);
		button=new JPanel();
		button.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		TitledBorder t=BorderFactory.createTitledBorder("descrizione");
		t.setTitleColor(Color.BLUE);
		TitledBorder n=BorderFactory.createTitledBorder("codice");
		n.setTitleColor(Color.BLUE);
		TitledBorder p=BorderFactory.createTitledBorder("prodotto");
		p.setTitleColor(Color.BLUE);
		this.setBackground(Color.WHITE);
		description=new JTextArea(1,50);
		description.setBorder(t);
		code=new JTextArea(1,10);
		code.setBorder(n);
		product=new JTextArea(1,10);
		product.setBorder(p);
		addS=new JButton("Aggiungi soluzione");
		addS.setBackground(Color.CYAN);
		addS.addActionListener(new AddSolution(description,code,product));
		text.add(description);
		text.add(code);
		text.add(product);
		add(text);
		button.add(addS);
		add(button);

	}

}
