package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuestionDAO {
	
	private String schema;
	private Connection conn;

	public QuestionDAO() {
		this.schema="sql9359791";
	}
	
	public String getText(String cod) { //metodo per restituire testo dato codice
		conn=DBConnection.startConnection(conn);
		try {
			conn.setSchema(schema);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement st1;
		ResultSet rs1 = null;
		String text=null;		
		try {					
			String query="SELECT text FROM question WHERE ID = '"+cod+"'";
			st1=conn.prepareStatement(query);
			rs1=st1.executeQuery(query);
			while(rs1.next())
				text=rs1.getString(1);
		}
		catch (Exception e) {e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return text;
	}
}
