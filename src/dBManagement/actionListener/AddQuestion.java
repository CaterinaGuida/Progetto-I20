package dBManagement.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import dBManagement.AnswerPanel;
import daos.AnswerDAO;
import daos.QuestionDAO;

public class AddQuestion implements ActionListener {
	AnswerPanel[] answers;
	JTextArea id,textQ,product;
	QuestionDAO dbQuestion;
	AnswerDAO dbAnswer;
	public AddQuestion(AnswerPanel[] answers,JTextArea id,JTextArea textQ,JTextArea product) {
		this.answers=answers;
		this.id=id;
		this.product=product;
		this.textQ=textQ;
		dbQuestion=new QuestionDAO();
		dbAnswer=new AnswerDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dbQuestion.addQuestion(id.getText(), textQ.getText(), product.getText());
		for(AnswerPanel a:answers) {
			if(a.getText().length()>1)
			dbAnswer.addAnswer(a.getText(), id.getText(), a.getNumber());
		}
	}

}
