package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FeedbackDAO {
	public static void saveFeedback(String description,String contatto,String id_prodotto,Boolean satisfied) { 
		PreparedStatement st1=null;                                                           
		Connection conn=null;												 
		try {													
			conn=DBConnection.startConnection(conn);
			String query="insert into feedback (`contatto`, `description`, `id_product`,`satisfied`)values (?,?,?,?)";
			st1=conn.prepareStatement(query);
			st1.setString(1, contatto);
			st1.setString(2, description);
			st1.setString(3, id_prodotto);
			st1.setString(4, satisfied.toString());
			st1.executeUpdate();
			st1.close();
		}
		catch (Exception e) {e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
	}
	public static String getAllFeedback() {
		PreparedStatement st1;
		Connection conn=null;												 

		ResultSet rs1;
		String result="";
		try {				
			conn=DBConnection.startConnection(conn);
			String query="SELECT * FROM feedback";
			st1=conn.prepareStatement(query);
			rs1=st1.executeQuery(query);
			while(rs1.next()) {
				result+= rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t";
				if(rs1.getString(4).equals("true"))
					result+="soddisfatto \n";
				else
					result+="insoddisfatto \n";
			}
		}
		catch (Exception e) {e.printStackTrace();
		}
		return result;		
	}
}
