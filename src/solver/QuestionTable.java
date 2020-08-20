package solver;

import java.util.HashMap;

import daos.QuestionDAO;

public class QuestionTable {
	
	public HashMap<String, Question> questionTable;
	
	
	public QuestionTable() {
		//Costruttore vuoto for Nocera's sake.
	}
	
	public QuestionTable(String code) {
		setQuestionTable(code);
	}
	
	private void setQuestionTable(String code) {
		QuestionDAO qstDAO = new QuestionDAO();
		this.questionTable = qstDAO.getQuestions(code);
	}
	
}
