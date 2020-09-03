package solver;

import java.util.HashMap;

import daos.QuestionDAO;

public class QuestionTable {
	
	public HashMap<String, Question> questionTable;
	
	
	public QuestionTable() {
		setQuestionTable();
	}
	
	private void setQuestionTable() {
		QuestionDAO qstDAO = new QuestionDAO();
		this.questionTable = qstDAO.getQuestions();
	}
	
}
