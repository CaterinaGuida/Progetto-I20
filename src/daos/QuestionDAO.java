package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import solver.Answer;
import solver.Question;

public class QuestionDAO {
	
	private Connection conn;

	public QuestionDAO() {
		conn=DBConnection.startConnection(conn);
	}
	public HashMap<String,Question> getQuestions() {
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
				q.addOption(new Answer(rs1.getString("ID"),rs1.getString("name")));
			}
			st1.close();
			rs1.close();
		}
		catch (Exception e) {e.printStackTrace();
		}
		questions.put("0", q);
		q=null;
		try {	
			String query="SELECT ID,question.text as question,num,answer.text as answer,id_question,id_product FROM	question,answer WHERE	ID=id_question order by ID";
			st1=conn.prepareStatement(query);
			rs1=st1.executeQuery(query);
			rs1.next();
			q=new Question(rs1.getString("ID"));
			q.setText(rs1.getString("question"));
			q.addOption(new Answer(rs1.getString("num"),rs1.getString("answer")));
			q.setProduct(rs1.getString("id_product"));
			questions.put(rs1.getString("ID"), q);
			while(rs1.next()) {
				if(questions.containsKey(rs1.getString("ID"))) {
					questions.get(rs1.getString("ID")).addOption(new Answer(rs1.getString("num"),rs1.getString("answer")));
				}
				else {
					q=new Question(rs1.getString("ID"));
					q.setText(rs1.getString("question"));
					q.setProduct(rs1.getString("id_product"));
					q.addOption(new Answer(rs1.getString("num"),rs1.getString("answer")));
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
