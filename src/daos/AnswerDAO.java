package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import solver.Answer;
// CLASSE DA ELIMINARE L'ACCESSO AL DB VIENE INTERAMENTE GESTITO DA QuetionDAO
public class AnswerDAO {
	private Connection conn;
	public AnswerDAO() {
		conn=DBConnection.startConnection(conn); // si potrebbe eliminaree dato che non ci serve più 
	}
	
	public ArrayList<Answer> getAnswer(String codQ) { //metodo per restituire testo dato codice
		PreparedStatement st1=null;
		ResultSet rs1 = null;
		ArrayList<Answer> out=new ArrayList<>();	
		try {					
			String query="SELECT text,num FROM answer WHERE id_question = '"+codQ+"'";
			st1=conn.prepareStatement(query);
			rs1=st1.executeQuery(query);
			while(rs1.next()) {
				out.add(new Answer(rs1.getString(2),rs1.getString(1)));
			}
			st1.close();
			rs1.close();
		}
		catch (Exception e) {e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return out;
	}
}
