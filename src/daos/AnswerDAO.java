package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AnswerDAO {
	private Connection conn;
	public AnswerDAO() {
		conn=DBConnection.startConnection(conn);
	}
	public void addAnswer(String text,String idQuestion,String num) {
		PreparedStatement st1=null;                                                           
		Connection conn=null;												 
		try {													
			conn=DBConnection.startConnection(conn);
			String query="INSERT INTO `sql9359791`.`answer` (`text`, `id_question`, `num`) VALUES (?, ?,?)";
			st1=conn.prepareStatement(query);
			st1.setString(1, text);
			st1.setString(2, idQuestion);
			st1.setString(3, num);

			st1.executeUpdate();
			st1.close();
		}
		catch (Exception e) {e.printStackTrace();
		}
	}
	public void closeConnection() {
		DBConnection.closeConnection(conn);
	}
}
