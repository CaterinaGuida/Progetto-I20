package solver;
import java.util.ArrayList;

import daos.AnswerDAO;
import daos.QuestionDAO;

public class Question {
	private String code;
	private String text;
	private ArrayList<Answer> options;
	
	public Question(String code) {
		this.code=code;
		//QUI CI SARA' IL METODO CHE PRENDE LE INFO DAL DB
		//STESSA COSA CON LE OPTIONS
	}

	public String getCode() {
		return code;
		//mi da il codice della domanda
	}

	public String getText() {
		return text;
		//mi da il testo della domada

	}

	public ArrayList<Answer> getOptions() {
		return options;
		//mi da l'array delle opzioni

	}

	public void setCode(String code) {
		this.code = code;
		//mi rende possibile modificare il codice
	}

	public void setText() {
		QuestionDAO qstDAO = new QuestionDAO();
		String text = qstDAO.getText(this.code);
		this.text=text;
	}

	public void setOptions() {
		AnswerDAO answer=new AnswerDAO();
		this.options=answer.getAnswer(this.code);
	}

}
