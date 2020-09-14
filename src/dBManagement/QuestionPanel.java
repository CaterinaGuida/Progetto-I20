package dBManagement;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import dBManagement.actionListener.AddQuestion;

@SuppressWarnings("serial")
public class QuestionPanel extends JPanel{
	AnswerPanel[] answers;
	JPanel question,buttons;
	JTextArea id,textQ,product;
	JButton addQuestion;
	public QuestionPanel() {
		setLayout(new GridLayout(7,1));
		TitledBorder iD=BorderFactory.createTitledBorder("id");
		iD.setTitleColor(Color.BLUE);
		TitledBorder t=BorderFactory.createTitledBorder("testo");
		t.setTitleColor(Color.BLUE);
		TitledBorder p=BorderFactory.createTitledBorder("prodotto");
		p.setTitleColor(Color.BLUE);
		answers=new AnswerPanel[5];
		id=new JTextArea(1,10);
		id.setForeground(Color.BLUE);
		id.setBorder(iD);
		textQ=new JTextArea(1,10);
		textQ.setBorder(t);
		textQ.setForeground(Color.BLUE);
		product=new JTextArea(1,10);
		product.setForeground(Color.BLUE);
		product.setBorder(p);
		addQuestion=new JButton("aggiungi domanda");
		addQuestion.setBackground(Color.CYAN);
		question=new JPanel();
		question.setBackground(Color.white);
		buttons=new JPanel();
		buttons.setBackground(Color.WHITE);
		for(int i=0;i<5;i++)
			answers[i]=new AnswerPanel();
		addQuestion.addActionListener(new AddQuestion(answers, id, textQ, product));
		add();
	}
	private void add() {
		question.add(id);
		question.add(textQ);
		question.add(product);
		buttons.add(addQuestion);
		add(question);
		for(AnswerPanel a:answers)
			add(a);
		add(buttons);
	}
}
