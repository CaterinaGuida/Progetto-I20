package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import solver.Answer;
import solver.Question;

public class QuestionDAO {
	
	private Connection conn;

	public QuestionDAO() {
		conn=DBConnection.startConnection(conn);
	}
	// metodo da ELIMINARE!!!!! ,per ora lo lascio per poter utilizzare entrambe le tecniche per ottenere le question
	public String getText(String cod) { //metodo per restituire testo dato codice 
		PreparedStatement st1=null;
		ResultSet rs1 = null;
		String text=null;		
		try {					
			String query="SELECT text FROM question WHERE ID = '"+cod+"'";
			st1=conn.prepareStatement(query);
			rs1=st1.executeQuery(query);
			if(rs1.next())
				text=rs1.getString(1);
			st1.close();
			rs1.close();
		}
		catch (Exception e) {e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return text;
	}
	public HashMap<String,Question> getQuestions() { //metodo per restituire testo dato codice 
		PreparedStatement st1=null;
		ResultSet rs1 = null;
		Question q=null;
		HashMap<String,Question> questions=new HashMap<>();
		try {					
			String query="SELECT ID,question.text as question,num,answer.text as answer,id_question FROM	question,answer WHERE	ID=id_question order by ID";
			st1=conn.prepareStatement(query);
			rs1=st1.executeQuery(query);
			rs1.next();
			q=new Question(rs1.getString("ID"));
			q.setText(rs1.getString("question"));
			q.AddOption(new Answer(rs1.getString("num"),rs1.getString("answer")));
			questions.put(rs1.getString("ID"), q);
			while(rs1.next()) {
				if(questions.containsKey(rs1.getString("ID"))) {
					questions.get(rs1.getString("ID")).AddOption(new Answer(rs1.getString("num"),rs1.getString("answer")));
				}
				else {
					q=new Question(rs1.getString("ID"));
					q.setText(rs1.getString("question"));
					q.AddOption(new Answer(rs1.getString("num"),rs1.getString("answer")));
					questions.put(rs1.getString("ID"), q);
				}
			}
			st1.close();
			rs1.close();
		}
		catch (Exception e) {e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return questions;
	}
	public static void main(String[] args) {
		QuestionDAO q=new QuestionDAO();
		HashMap<String,Question> prova=q.getQuestions();
		System.out.println(prova.get("F22122").toString());
	}
}
