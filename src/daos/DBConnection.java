package daos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection startConnection(Connection conn, String schema) {
		String DbDriver="com.mysq.jdbc.Driver";
		String DbURL="sql9.freemysqlhosting.net";
		String username="sql9359791";
		String password="eRBKt5y1Z7";
		if(isOpen(conn))
			closeConnection(conn);
		try {
			Class.forName("com.mysq.cj.jdbc.Driver");
			conn=DriverManager.getConnection(DbURL, username, password); //apre connesione
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	public static boolean isOpen(Connection conn) {
		if(conn==null)
			return false;
		else 
			return true;
	}
	public static Connection closeConnection(Connection conn) {
		if(!isOpen(conn))
			return null;
		try {
			conn.close();
			conn=null;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
}

