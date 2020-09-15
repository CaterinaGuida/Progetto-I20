package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import solver.Question;

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
	public String getAllProduct() {
		PreparedStatement st1;
		ResultSet rs1;
		String result="";
		try {					
			String query="SELECT * FROM product";
			st1=conn.prepareStatement(query);
			rs1=st1.executeQuery(query);
			while(rs1.next())
				result+= rs1.getString(1)+" \t "+rs1.getString(2)+"\n";
		}
		catch (Exception e) {e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return result;
	}
}
