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
	public HashMap<String,Question> getQuestions(String code) {
		PreparedStatement st1=null;
		ResultSet rs1 = null;
		Question q=new Question("0");
		HashMap<String,Question> questions=new HashMap<>();
		try {					
			String query="SELECT text FROM question WHERE ID = '0'";
			st1=conn.prepareStatement(query);
			rs1=st1.executeQuery(query);
			if(rs1.next()) {
				q.setText(rs1.getString(1));
			}
			st1.close();
			rs1.close();
		}
		catch (Exception e) {e.printStackTrace();
		}
		try {					
			String query="SELECT * FROM product";
			st1=conn.prepareStatement(query);
			rs1=st1.executeQuery(query);
			while(rs1.next()) {
				q.AddOption(new Answer(rs1.getString("ID"),rs1.getString("name")));
			}
			st1.close();
			rs1.close();
		}
		catch (Exception e) {e.printStackTrace();
		}
		questions.put("0", q);
		q=null;
		try {	
			String query="SELECT ID,question.text as question,num,answer.text as answer,id_question FROM	question,answer WHERE	ID=id_question and id_product='"+code+"' order by ID";
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
}
