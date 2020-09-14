package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductDAO {
	private Connection conn;
	public ProductDAO() {
		conn=DBConnection.startConnection(conn);
	}
	public void addProduct(String name,String code) {
		PreparedStatement st1=null;                                                           
		Connection conn=null;												 
		try {													
			conn=DBConnection.startConnection(conn);
			String query="insert into product (`ID`, `name`)values (?,?)";
			st1=conn.prepareStatement(query);
			st1.setString(1, code);
			st1.setString(2, name);
			st1.executeUpdate();
			st1.close();
		}
		catch (Exception e) {e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
	}
}
