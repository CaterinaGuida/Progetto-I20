package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import solver.Answer;
import solver.Question;

public class QuestionDAO {
	
	private Connection conn;

	public QuestionDAO() {
		conn=DBConnection.startConnection(conn);
	}
	public String getProduct(String cod) {
		PreparedStatement st1=null;
		ResultSet rs1 = null;
		String product=null;		
		try {					
			String query="SELECT id_product FROM question WHERE ID = '"+cod+"'";
			st1=conn.prepareStatement(query);
			rs1=st1.executeQuery(query);
			if(rs1.next())
				product=rs1.getString(1);
			st1.close();
			rs1.close();
		}
		catch (Exception e) {e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return product;
	}
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
	public Question getFirstQuestion() {
		PreparedStatement st1=null;
		ResultSet rs1 = null;
		Question q=new Question("0");
		String text=null;
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
		DBConnection.closeConnection(conn);
		return q;
	}
}
