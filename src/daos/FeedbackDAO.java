package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
