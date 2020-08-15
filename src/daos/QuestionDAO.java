package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class QuestionDAO {
	
	private String schema;
	private Connection conn;

	public QuestionDAO() {
		this.schema="sql9359791";
	}
	
	public String getText(String cod) { //metodo per restituire testo dato codice
		conn=DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1 = null;
		String text=null;		
		try {
			st1=conn.createStatement();						//!!!!!NON FUNZIONA!!!!!!
			String query="SELECT text WHERE ID="+cod; 		//!!!!!NON FUNZIONA!!!!!!
			rs1=st1.executeQuery(query);					//!!!!!NON FUNZIONA!!!!!!
			text=rs1.getString(2);
		}
		catch (Exception e) {e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return text;
	}
}
