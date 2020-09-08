package solver.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SolutionDAO {
	private Connection conn;

	public SolutionDAO() {
		conn=DBConnection.startConnection(conn);
	}
	
	public String getText(String cod) { //metodo per restituire testo dato codice
		PreparedStatement st1=null;
		ResultSet rs1 = null;
		String text=null;		
		try {					
			String query="SELECT solution FROM solution WHERE ID = '"+cod+"'";
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
}

