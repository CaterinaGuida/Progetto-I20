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
	public static Object[][] getAllFeedback() {
		PreparedStatement st1;
		Connection conn=null;												 

		ResultSet rs1;
		Object result[][]=null;
		try {				
			conn=DBConnection.startConnection(conn);
			String query="SELECT * FROM feedback";
			st1=conn.prepareStatement(query);
			rs1=st1.executeQuery(query);
			int rowCount = 0;
		    while (rs1.next()) {
		    	rowCount++;
		    }
		    rs1.first();
		    result = new Object[rowCount][4];
			for(int j=0; rs1.next(); j++) {
				result[j][0]= rs1.getString(1);
				result[j][1]= rs1.getString(2);
				result[j][2]= rs1.getString(3);
				if(rs1.getString(4).equals("true"))
					result[j][3]= "Soddisfatto";
				else
					result[j][3]= "Insoddisfatto";
			}
		}
		catch (Exception e) {e.printStackTrace();
		}
		return result;		
	}
}
