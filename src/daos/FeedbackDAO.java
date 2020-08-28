package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FeedbackDAO {
	public static void saveFeedback(String description,String contatto,String id_prodotto,Boolean satisfied) { //metodo usato per caricare nel DB il feedback
		PreparedStatement st1=null;                                                          // attenzione che si inseriscono due feedback 
		Connection conn=null;																// con la stessa descrizione e contatto genera un eccezione 
		try {																				// dato che questi due valori sono chiave primaria
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